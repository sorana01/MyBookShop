package com.example.book_shop.exceptions;

public class CouldNotWriteUsersException extends RuntimeException{
    public CouldNotWriteUsersException() {
        super("User couldn't be written!");
    }
}
