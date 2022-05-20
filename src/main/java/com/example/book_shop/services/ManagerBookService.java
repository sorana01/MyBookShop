package com.example.book_shop.services;

import com.example.book_shop.exceptions.CouldNotWriteBooksException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.model.Book;
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

    public static void addBook(String title, String author, String price, String category, String quantity) {
        book_database.add(new Book(title, price, category, quantity, author));
        persistBooks();
    }

    public static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), book_database);
        } catch (IOException e) {
            throw new CouldNotWriteBooksException();
        }
    }
}
