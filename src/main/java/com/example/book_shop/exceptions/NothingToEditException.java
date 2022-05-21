package com.example.book_shop.exceptions;

public class NothingToEditException extends Exception{
    public NothingToEditException() {
        super("You have not completed any fields!");
    }
}
