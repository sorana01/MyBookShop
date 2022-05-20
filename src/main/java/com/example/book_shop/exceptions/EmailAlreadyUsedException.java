package com.example.book_shop.exceptions;

public class EmailAlreadyUsedException extends Exception{
    public EmailAlreadyUsedException() {
        super("The email you entered is already used!");
    }
}
