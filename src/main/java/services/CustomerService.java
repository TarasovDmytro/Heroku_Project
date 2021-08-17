package services;

import dao.CustomerDao;
import entities.Customer;
import entities.Order;
import lombok.val;

import java.util.List;

public class CustomerService {

    CustomerDao customDao = new CustomerDao();

    public Customer customerInit(Customer customer) {

        List<Customer> customers = customDao.getAllInstance();

        if (customers.isEmpty()) {
            customDao.createInstance(customer);
        } else {
            for (Customer curCustomer : customers) {
                if (curCustomer.getEmail().equals(customer.getEmail())) {
                    customer = curCustomer;
                }
            }
            if (customer.getId() == 0) customDao.createInstance(customer);
        }
        return customer;
    }

    public void readOrdersOfCustomer(Customer customer) {

        customer = customDao.getInstanceById(customer.getId());
        List<Order> orders = customer.getOrders();
        if (orders.isEmpty()) {

            System.out.println("\nYou have no orders yet");
        } else {

            System.out.println("\nYour orders:\n");
            orders.forEach(order -> {
                System.out.println(order);
            });
        }
    }
}
