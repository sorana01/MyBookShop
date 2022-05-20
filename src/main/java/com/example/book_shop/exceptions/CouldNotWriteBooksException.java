package com.example.book_shop.exceptions;

public class CouldNotWriteBooksException extends RuntimeException{
    public CouldNotWriteBooksException() {
        super("Book couldn't be written!");
    }
}
