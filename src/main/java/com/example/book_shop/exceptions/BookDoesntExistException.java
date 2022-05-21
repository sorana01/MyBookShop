package com.example.book_shop.exceptions;

public class BookDoesntExistException extends Exception{
    public BookDoesntExistException() {
        super("The book doesn't exist!");
    }
}
