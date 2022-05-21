package com.example.book_shop.exceptions;

public class CouldNotWriteOrdersException extends RuntimeException{
    public CouldNotWriteOrdersException() {
        super("Order couldn't be written!");
    }
}
