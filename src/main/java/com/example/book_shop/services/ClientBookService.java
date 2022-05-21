package com.example.book_shop.services;

import com.example.book_shop.controllers.LogInController;
import com.example.book_shop.exceptions.*;
import com.example.book_shop.model.Book;
import com.example.book_shop.model.Order;
import com.example.book_shop.model.User;
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
    private static List<User> user_database = UserService.getUsers();
    private static List<Book> shopping_cart_database;
    private static final Path SHOPPING_CART_PATH = FileSystemService.getPathToFile("shopping_cart.json");
    private static List<Order> order_database;
    private static final Path ORDER_PATH = FileSystemService.getPathToFile("orders.json");
    private static String loggedUser;
    private static Book my_book;


    public static List<Order> getOrder_database() {
        return order_database;
    }

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

    public static void placeOrder(String full_name, String card_type, String card_number, String cvv, String month, String year)
            throws IOException, EmptyTextFieldsException, NotAllCharactersAreDigitsException {
        loggedUser = LogInController.getLoggedUser();
        User client = new User();

        checkEmptyTextFieldsForOrder(full_name, card_type, card_number, cvv, month, year);
        UserService.checkAllDigitsEntered(card_number);
        UserService.checkAllDigitsEntered(cvv);


        for (User user : user_database) {
            if (Objects.equals(loggedUser, user.getUsername()))
                client = user;

        }

        Order last_order = null;
        for (Order order : order_database) {
            last_order = order;
        }
        if (order_database.size() == 0)
            order_database.add(new Order(shopping_cart_database, client, 1));
        else
            order_database.add(new Order(shopping_cart_database, client, last_order.getOrder_number() + 1));
        persistOrders();

        //empty the temporary database
        List<Book> shopping_cart_database_aux = shopping_cart_database;
        int index = 0;
        for (Book book : shopping_cart_database) {
            for (Book data_book : book_database) {
                if (Objects.equals(data_book.getTitle(), book.getTitle()) && Objects.equals(data_book.getAuthor(), book.getAuthor())) {
                    if (Objects.equals(book.getQuantity(), data_book.getQuantity()))
                        book_database.remove(data_book);
                    else {
                        data_book.setQuantity(String.valueOf(Integer.parseInt(data_book.getQuantity()) - Integer.parseInt(book.getQuantity())));
                        book_database.set(index, data_book);
                    }
                    index = 0;
                    break;
                }
                index++;
            }
        }
        shopping_cart_database.removeAll(shopping_cart_database_aux);
        persistShoppingCart();
        ManagerBookService.persistBooks();
    }

    private static void checkEmptyTextFields(String title, String author, String quantity) throws EmptyTextFieldsException {

        if( Objects.equals(title,"") || Objects.equals(author, "") || Objects.equals(quantity,""))
            throw new EmptyTextFieldsException();

    }

    private static void checkEmptyTextFieldsForOrder(String name, String card_type, String card_number, String cvv, String month, String year) throws EmptyTextFieldsException {
        if( Objects.equals(name,"") || Objects.equals(card_number,"")
                || Objects.equals(cvv,"") )
            throw new EmptyTextFieldsException();
        else if( !( Objects.equals(card_type,"MasterCard") || Objects.equals(card_type,"PayPal") || Objects.equals(card_type, "VISA")
        || Objects.equals(card_type, "AmericanExpress")))
            throw new EmptyTextFieldsException();
        else if ( !(Objects.equals(month, "January") || Objects.equals(month, "February") || Objects.equals(month, "March")
        || Objects.equals(month, "April") || Objects.equals(month, "May") || Objects.equals(month, "June") || Objects.equals(month, "July")
        || Objects.equals(month, "August") || Objects.equals(month, "September") || Objects.equals(month, "October")
        || Objects.equals(month, "November") || Objects.equals(month, "December")))
            throw new EmptyTextFieldsException();
        else if ( !(Objects.equals(year, "2022") || Objects.equals(year, "2023") || Objects.equals(year, "2024") || Objects.equals(year, "2025")
        || Objects.equals(year, "2026") || Objects.equals(year, "2027") || Objects.equals(year, "2028") || Objects.equals(year, "2029") || Objects.equals(year, "2030")))
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
