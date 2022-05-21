package com.example.book_shop.exceptions;

public class StatusAlreadyModifiedException extends Exception{
    public StatusAlreadyModifiedException() {
        super("You have already modified the status for this order!");
    }
}
