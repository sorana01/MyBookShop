package com.example.book_shop.exceptions;

public class InvalidQuantityException extends Exception{
    public InvalidQuantityException() {
        super("Invalid quantity!");
    }
}
