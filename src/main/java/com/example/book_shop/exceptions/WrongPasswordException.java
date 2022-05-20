package com.example.book_shop.exceptions;

public class WrongPasswordException extends Exception{
    public WrongPasswordException() {
        super("Incorrect password!");
    }
}
