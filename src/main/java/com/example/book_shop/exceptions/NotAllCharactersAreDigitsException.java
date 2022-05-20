package com.example.book_shop.exceptions;

public class NotAllCharactersAreDigitsException extends Exception{
    public NotAllCharactersAreDigitsException() {
        super("Please enter only digits!");
    }
}
