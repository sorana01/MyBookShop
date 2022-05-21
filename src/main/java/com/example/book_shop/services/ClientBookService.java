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

public class ClientBookService {
    private static List<Book> book_database = ManagerBookService.getBooks();
    private static List<Book> shopping_cart_database;
    private static final Path SHOPPING_CART_PATH = FileSystemService.getPathToFile("shopping_cart.json");
    private static List<Order> order_database;
    private static final Path ORDER_PATH = FileSystemService.getPathToFile("orders.json");
    private static Book my_book;


    public static List<Book> getShoppingCart_database() {
        return shopping_cart_database;
    }

    public static void loadOrdersFromFile() throws IOException {
        if (!Files.exists(ORDER_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("orders.json")), ORDER_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        order_database = objectMapper.readValue(ORDER_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void initializeShoppingCart() throws IOException {
        if (!Files.exists(SHOPPING_CART_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("shopping_cart.json")), SHOPPING_CART_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        shopping_cart_database = objectMapper.readValue(SHOPPING_CART_PATH.toFile(), new TypeReference<>() {
        });
    }

    public static void addABookToCart(String title, String author, String quantity) throws EmptyTextFieldsException, InvalidQuantityException, BookDoesntExistException, NotAllCharactersAreDigitsException {
        checkEmptyTextFields(title, author, quantity);
        UserService.checkAllDigitsEntered(quantity);
        checkIfBookAvailable(title, author, quantity);

        //add the product to a temporary database (shopping cart)
        shopping_cart_database.add(new Book(my_book.getTitle(), my_book.getPrice(), my_book.getCategory(), quantity, my_book.getAuthor()));
        persistShoppingCart();
    }

    private static void checkEmptyTextFields(String title, String author, String quantity) throws EmptyTextFieldsException {

        if( Objects.equals(title,"") || Objects.equals(author, "") || Objects.equals(quantity,""))
            throw new EmptyTextFieldsException();

    }

    private static void checkIfBookAvailable(String title, String author, String quantity) throws BookDoesntExistException, InvalidQuantityException {
        int book_exists = 0;

        for (Book book : book_database) {
            if (Objects.equals(author, book.getAuthor()) && Objects.equals(title, book.getTitle())) {
                book_exists = 1;
                if (Integer.parseInt(quantity) < 0 || (Integer.parseInt(quantity) > Integer.parseInt(book.getQuantity()))) {
                    throw new InvalidQuantityException();
                }
                my_book = book;
                break;
            }
        }

        if (book_exists == 0)
            throw new BookDoesntExistException();
    }

    private static void persistShoppingCart() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(SHOPPING_CART_PATH.toFile(), shopping_cart_database);
        } catch (IOException e) {
            throw new CouldNotWriteBooksException();
        }
    }

    public static void persistOrders() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(ORDER_PATH.toFile(), order_database);
        } catch (IOException e) {
            throw new CouldNotWriteOrdersException();
        }
    }


}
