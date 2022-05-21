package com.example.book_shop.services;

import com.example.book_shop.exceptions.EmptyTextFieldsException;
import com.example.book_shop.exceptions.NotAllCharactersAreDigitsException;
import com.example.book_shop.exceptions.OrderNumberDoesntExistException;
import com.example.book_shop.exceptions.StatusAlreadyModifiedException;
import com.example.book_shop.model.Order;

import java.util.Objects;

public class CourierBookService {
    public static void modifyOrderStatus(String number, String new_status) throws StatusAlreadyModifiedException,
            EmptyTextFieldsException, OrderNumberDoesntExistException, NotAllCharactersAreDigitsException {
        ManagerBookService.checkEmptyTextFieldsForModifyStatus(number, new_status);
        UserService.checkAllDigitsEntered(number);
        checkStatusAlreadyModified(number);
        checkOrderNumberDoesntExist(number);

        for (Order order : ClientBookService.getOrder_database()) {
            if (Objects.equals(order.getOrder_number(), Integer.parseInt(number))) {
                for (Order order_acc : ManagerBookService.getAccepted_order_database()) {
                    if (Objects.equals(order_acc.getOrder_number(), Integer.parseInt(number))) {
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

    public static void checkStatusAlreadyModified(String number) throws StatusAlreadyModifiedException{
        for (Order order : ManagerBookService.getAccepted_order_database()) {
            if (Objects.equals(Integer.parseInt(number), order.getOrder_number())) {
                if (!Objects.equals(order.getStatus(), "ACCEPTED"))
                    throw new StatusAlreadyModifiedException();
            }
        }
    }

    public static void checkOrderNumberDoesntExist(String number) throws OrderNumberDoesntExistException {
        int exists = 0;

        for (Order order : ManagerBookService.getAccepted_order_database()) {
            if (Objects.equals(Integer.parseInt(number), order.getOrder_number()))
                exists = 1;
        }
        if (exists == 0) {
            throw new OrderNumberDoesntExistException();
        }
    }

}
