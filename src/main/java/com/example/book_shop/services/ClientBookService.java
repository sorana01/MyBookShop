package com.example.book_shop.services;

import com.example.book_shop.exceptions.CouldNotWriteBooksException;
import com.example.book_shop.model.Book;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.io.FileUtils;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.List;
import java.util.Objects;

public class ClientBookService {
    private static List<Book> shopping_cart_database;
    private static final Path SHOPPING_CART_PATH = FileSystemService.getPathToFile("shopping_cart.json");




    public static void initializeShoppingCart() throws IOException {
        if (!Files.exists(SHOPPING_CART_PATH)) {
            FileUtils.copyURLToFile(Objects.requireNonNull(UserService.class.getClassLoader().getResource("shopping_cart.json")), SHOPPING_CART_PATH.toFile());
        }

        ObjectMapper objectMapper = new ObjectMapper();
        shopping_cart_database = objectMapper.readValue(SHOPPING_CART_PATH.toFile(), new TypeReference<>() {
        });
    }

    private static void persistShoppingCart() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(SHOPPING_CART_PATH.toFile(), shopping_cart_database);
        } catch (IOException e) {
            throw new CouldNotWriteBooksException();
        }
    }


}
