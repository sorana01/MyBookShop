package com.example.book_shop.exceptions;

public class EmptyTextFieldsException extends Exception{
    public EmptyTextFieldsException() {
        super("Please complete all the fields!");
    }
}
