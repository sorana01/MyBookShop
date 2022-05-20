package com.example.book_shop.exceptions;

public class TitleAndAuthorUsedException extends Exception {
    public TitleAndAuthorUsedException() {
        super("A book with this title and author already exists!");
    }
}
