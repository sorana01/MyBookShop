package com.example.book_shop.exceptions;

public class PhoneNumberAlreadyUsedException extends Exception{
    public PhoneNumberAlreadyUsedException() {
        super("The phone number you entered is already used!");
    }
}
