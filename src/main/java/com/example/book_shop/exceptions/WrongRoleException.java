package com.example.book_shop.exceptions;

public class WrongRoleException extends Exception{
    public WrongRoleException() {
        super("Incorrect role!");
    }
}
