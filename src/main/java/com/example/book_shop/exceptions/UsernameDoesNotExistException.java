package com.example.book_shop.exceptions;

public class UsernameDoesNotExistException extends Exception{
    public UsernameDoesNotExistException() {
        super("The username you entered doesn't exist!");
    }
}
