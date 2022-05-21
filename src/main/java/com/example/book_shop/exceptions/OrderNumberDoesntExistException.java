package com.example.book_shop.exceptions;

public class OrderNumberDoesntExistException extends Exception{
    public OrderNumberDoesntExistException() {
        super("The order number you entered doesn't exist!");
    }
}
