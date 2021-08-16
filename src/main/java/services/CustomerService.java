package services;

import dao.CustomerDao;
import entities.Customer;
import entities.Order;

import java.util.List;

public class CustomerService {

    CustomerDao customDao = new CustomerDao();

    public Customer customerInit(Customer customer) {

        List<Customer> customers = customDao.getAllInstance();

        if (customers.isEmpty()) {
            customDao.createInstance(customer);
        }
        for (Customer currentCustomer : customers) {
            if (currentCustomer.getEmail().equals(customer.getEmail())) {
                customer = currentCustomer;
            } else {
                customDao.createInstance(customer);
            }
        }
        return customer;
    }

    public void readOrdersOfCustomer(Customer customer) {

        customer = customDao.getInstanceById(customer.getId());
        List<Order> orders = customer.getOrders();
        if (orders.isEmpty()){
            System.out.println("\nYou have no orders yet");
        } else {

            System.out.println("\nYour orders:\n");
            orders.forEach(order -> {
                System.out.println(order);
                order.getAlbums().forEach(System.out::println);
                order.getTracks().forEach(System.out::println);
            });
        }
    }
}
