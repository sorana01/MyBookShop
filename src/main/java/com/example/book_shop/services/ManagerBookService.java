package com.example.book_shop.services;

import com.example.book_shop.exceptions.CouldNotWriteBooksException;
import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.exceptions.TitleAndAuthorUsedException;
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

    public static void addBook(String title, String author, String price, String category, String quantity) throws EmptyTextFieldsException, TitleAndAuthorUsedException, NotAllCharactersAreDigitsException {
        checkEmptyTextFields(title, author, price, category, quantity);
        checkTitleAndAuthorExist(title, author);
        UserService.checkAllDigitsEntered(price);
        UserService.checkAllDigitsEntered(quantity);
        book_database.add(new Book(title, price, category, quantity, author));
        persistBooks();
    }

    public static void editBook(String title, String author, String title_new, String author_new, String price_new, String category_new, String quantity_new) {
        String category = "";

        Book book_aux = new Book();
        int index = 0;

        for (Book book : book_database) {
            if (Objects.equals(title, book.getTitle()) && Objects.equals(author, book.getAuthor())) {
                book_aux = book;
                category = book.getCategory();
                break;
            }
            index++;
        }

        if (!Objects.equals(title_new, "")) {
            book_aux.setTitle(title_new);
            book_aux.setCategory(category);
        }
        if (!Objects.equals(author_new, "")) {
            book_aux.setAuthor(author_new);
            book_aux.setCategory(category);
        }
        if (!Objects.equals(price_new, "")) {
            book_aux.setPrice(price_new);
            book_aux.setCategory(category);
        }
        if (!Objects.equals(category_new, "")) {
            book_aux.setCategory(category_new);
        }
        if (!Objects.equals(quantity_new, "")) {
            book_aux.setQuantity(quantity_new);
            book_aux.setCategory(category);
        }

        book_database.set(index, book_aux);
        persistBooks();
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

    public static void persistBooks() {
        try {
            ObjectMapper objectMapper = new ObjectMapper();
            objectMapper.writerWithDefaultPrettyPrinter().writeValue(BOOKS_PATH.toFile(), book_database);
        } catch (IOException e) {
            throw new CouldNotWriteBooksException();
        }
    }
}
