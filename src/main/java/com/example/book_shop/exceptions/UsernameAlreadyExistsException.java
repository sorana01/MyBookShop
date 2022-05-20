package com.example.book_shop.exceptions;

public class UsernameAlreadyExistsException extends Exception {


    public UsernameAlreadyExistsException() {
        super("An account with this username already exists!");
    }

}

