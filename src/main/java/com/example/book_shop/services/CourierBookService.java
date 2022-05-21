package com.example.book_shop.services;

import com.example.book_shop.model.Order;

import java.util.Objects;

public class CourierBookService {
    public static void modifyOrderStatus(int number, String new_status) {

        for (Order order : ClientBookService.getOrder_database()) {
            if (Objects.equals(order.getOrder_number(), number)) {
                for (Order order_acc : ManagerBookService.getAccepted_order_database()) {
                    if (Objects.equals(order_acc.getOrder_number(), number)) {
                        order_acc.setStatus(new_status);
                        order.setStatus(new_status);
                        ClientBookService.persistOrders();
                        ManagerBookService.persistAcceptedOrders();
                        break;
                    }
                }
            }
        }
    }

}
