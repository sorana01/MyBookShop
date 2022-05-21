package com.example.book_shop.model;

import java.util.List;

public class Order {
    private List<Book> books;
    private User user;
    private String status;
    private int order_number;

    public Order(List<Book> books, User client, int number) {
        this.books = books;
        this.user = client;
        this.order_number = number;
        this.status = "PENDING";
    }

    public Order() {

    }

    public List<Book> getBooks() {
        return books;
    }

    public void setBooks(List<Book> books) {
        this.books = books;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Order{" +
                "books=" + books +
                ", user=" + user +
                ", status='" + status + '\'' +
                ", order_number=" + order_number +
                '}';
    }

    public int getOrder_number() {
        return order_number;
    }

    public void setOrder_number(int order_number) {
        this.order_number = order_number;
    }

}
