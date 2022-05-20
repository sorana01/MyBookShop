package com.example.book_shop.model;

import java.util.Objects;

public class Book {
    private String title;
    private String price;
    private String category;
    public String author;
    private String quantity;

    public Book(String title, String price, String category, String quantity, String author) {
        this.title = title;
        this.price = price;
        this.category = category;
        this.quantity = quantity;
        this.author = author;
    }

    public Book() {

    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getPrice() {
        return price;
    }

    public void setPrice(String price) {
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getQuantity() {
        return quantity;
    }

    public void setQuantity(String quantity) {
        this.quantity = quantity;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    @Override
    public String toString() {
        return title + " by " + author + "\n " + category + "\n" + price + "\n" + quantity + "\n" ;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Book)) return false;
        Book book = (Book) o;
        return getTitle().equals(book.getTitle()) && getPrice().equals(book.getPrice()) && getCategory().equals(book.getCategory()) && getAuthor().equals(book.getAuthor()) && getQuantity().equals(book.getQuantity());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTitle(), getPrice(), getCategory(), getAuthor(), getQuantity());
    }
}

