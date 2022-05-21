package com.example.book_shop.services;

import com.example.book_shop.exceptions.*;
import com.example.book_shop.model.Book;
import com.example.book_shop.model.Order;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ManagerBookService {
    private static List<Book> book_database;
    private static List<Order> accepted_order_database;
    private static final Path ACCEPTED_ORDERS_PATH = FileSystemService.getPathToFile("accepted_orders.json");
    private static final Path BOOKS_PATH = FileSystemService.getPathToFile("books.json");

    public static List<Book> getBooks() {
        return book_database;
    }


    public static void loadBooksFromFile() throws IOException {
        if (!Files.exists(BOOKS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("books.json")), BOOKS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        book_database = objectMapper.readValue(BOOKS_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void loadAcceptedOrdersFromFile() throws IOException {
        if (!Files.exists(ACCEPTED_ORDERS_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("accepted_orders.json")), ACCEPTED_ORDERS_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        accepted_order_database = objectMapper.readValue(ACCEPTED_ORDERS_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void addBook(String title, String author, String price, String category, String quantity) throws EmptyTextFieldsException, TitleAndAuthorUsedException, NotAllCharactersAreDigitsException {
        checkEmptyTextFields(title, author, price, category, quantity);
        checkTitleAndAuthorExist(title, author);
        UserService.checkAllDigitsEntered(price);
        UserService.checkAllDigitsEntered(quantity);
        book_database.add(new Book(title, price, category, quantity, author));
        persistBooks();
    }

    public static void editBook(String title, String author, String title_new, String author_new, String price_new, String category_new, String quantity_new) throws BookDoesntExistException, EmptyTextFieldsException, NothingToEditException {
        checkEmptyTextFieldsForEdit(title, author);
        checkBookDoesntExist(title, author);


        Book book_aux = new Book();
        int index = 0;
        boolean change = false;

        for (Book book : book_database) {
            if (Objects.equals(title, book.getTitle()) && Objects.equals(author, book.getAuthor())) {
                book_aux = book;
                break;
            }
            index++;
        }

        if (!Objects.equals(title_new, "")) {
            book_aux.setTitle(title_new);
            change = true;
        }
        if (!Objects.equals(author_new, "")) {
            book_aux.setAuthor(author_new);
            change = true;
        }
        if (!Objects.equals(price_new, "")) {
            book_aux.setPrice(price_new);
            change = true;
        }
        if (Objects.equals(category_new, "Romance") || Objects.equals(category_new, "Horror")
                || Objects.equals(category_new, "Thriller") || Objects.equals(category_new, "Science Fiction")
        || Objects.equals(category_new, "Others")) {
            book_aux.setCategory(category_new);
            change = true;
        }
        if (!Objects.equals(quantity_new, "")) {
            book_aux.setQuantity(quantity_new);
            change = true;
        }

        if (change == true) {
            book_database.set(index, book_aux);
            persistBooks();
        }
        else
            throw new NothingToEditException();

    }

    public static void deleteBook(String title, String author) throws EmptyTextFieldsException, BookDoesntExistException {
        checkEmptyTextFieldsForEdit(title, author);
        checkBookDoesntExist(title, author);

        for (Book book : book_database) {
            if (Objects.equals(title, book.getTitle()) && Objects.equals(author, book.getAuthor())) {
                book_database.remove(book);
                persistBooks();
                break;
            }
        }
    }

    public static void modifyOrderStatus(int number, String new_status) throws EmptyTextFieldsException, StatusAlreadyModifiedException, OrderNumberDoesntExistException {
        checkEmptyTextFieldsForModifyStatus(number, new_status);
        checkStatusAlreadyModified(number);
        checkOrderNumberDoesntExist(number);

        for (Order order : ClientBookService.getOrder_database()) {
            if (Objects.equals(order.getOrder_number(), number)) {
                order.setStatus(new_status);
                ClientBookService.persistOrders();
                if (Objects.equals(new_status, "ACCEPTED")) {
                    accepted_order_database.add(order);
                    persistAcceptedOrders();
                }
                break;
            }
        }
    }

    public static void checkOrderNumberDoesntExist(int number) throws OrderNumberDoesntExistException {
        int exists = 0;

        for (Order order : ClientBookService.getOrder_database()) {
            if (Objects.equals(number, order.getOrder_number()))
                exists = 1;
        }
        if (exists == 0) {
            throw new OrderNumberDoesntExistException();
        }
    }

    public static void checkBookDoesntExist(String title, String author) throws BookDoesntExistException {
        int book_exists = 0;

        for (Book book : book_database) {
            if (Objects.equals(author, book.getAuthor()) && Objects.equals(title, book.getTitle()))
                book_exists = 1;
        }

        if (book_exists == 0)
            throw new BookDoesntExistException();
    }

    public static void checkEmptyTextFieldsForModifyStatus(int number, String status) throws EmptyTextFieldsException{
        if( Objects.equals(number,"") || Objects.equals(status, ""))
            throw new EmptyTextFieldsException();
    }

    public static void checkEmptyTextFieldsForEdit(String title, String author) throws EmptyTextFieldsException{
        if( Objects.equals(title,"") || Objects.equals(author, ""))
            throw new EmptyTextFieldsException();
    }

    public static void checkEmptyTextFields(String title, String author, String price, String category, String quantity) throws EmptyTextFieldsException {
        if( Objects.equals(title,"") || Objects.equals(author, "") || Objects.equals(price,"")
                || Objects.equals(category,"") || Objects.equals(quantity,""))
            throw new EmptyTextFieldsException();
    }

    public static void checkTitleAndAuthorExist(String title, String author) throws TitleAndAuthorUsedException {
        for (Book book : book_database)
            if (Objects.equals(title, book.getTitle()) && Objects.equals(author, book.getAuthor()))
                throw new TitleAndAuthorUsedException();
    }

    public static void checkStatusAlreadyModified(int number) throws StatusAlreadyModifiedException{
        for (Order order : ClientBookService.getOrder_database()) {
            if (Objects.equals(number, order.getOrder_number())) {
                if (!Objects.equals(order.getStatus(), "PENDING"))
                    throw new StatusAlreadyModifiedException();
            }
        }
    }

    public static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), book_database);
        } catch (IOException e) {
            throw new CouldNotWriteBooksException();
        }
    }

    public static void persistAcceptedOrders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(ACCEPTED_ORDERS_PATH.toFile(), accepted_order_database);
        } catch (IOException e) {
            throw new CouldNotWriteOrdersException();
        }
    }
}
