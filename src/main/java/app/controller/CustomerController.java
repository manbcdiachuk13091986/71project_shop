package app.controller;

import app.domain.Customer;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.exceptions.ProductNotFoundException;
import app.service.CustomerService;

import java.io.IOException;
import java.util.List;

public class CustomerController {
    private  CustomerService service;

    public Customer save(String name) throws IOException, CustomerSaveException {
        Customer customer = new Customer(name);
        return service.save(customer);
    }

    public List<Customer> getAllActiveCustomers() throws IOException {
        return service.getAllActiveCustomers();
    }

    public Customer getActiveCustomerById(int id) throws IOException, CustomerNotFoundException {
        return service.getActiveCustomerById(id);
    }

    public void update(int id, String name) throws CustomerUpdateException, IOException {
        Customer customer = new Customer(id, name);
        service.update(customer);
    }
    public void deleteById(int id) throws IOException, CustomerNotFoundException {
        service.deleteById(id);
    }

    public void deleteByName(String name) throws IOException {
        service.deleteByName(name);
    }

    public void restoreById(int id) throws IOException, CustomerNotFoundException {
        service.restoreById(id);
    }

    public int getActiveCustomerCount() throws IOException {
        return service.getActiveCustomerCount();
    }

    public double getCustomerCartTotalPrice(int id) throws IOException, CustomerNotFoundException {
        return service.getCustomerCartTotalPrice(id);
    }

    public double getCustomerCartAveragePrice(int id) throws IOException, CustomerNotFoundException {
        return service.getCustomerCartAveragePrice(id);
    }

    public void addProductToCustomerCart(int customerId, int productId) throws IOException, CustomerNotFoundException, ProductNotFoundException {
        service.addProductToCustomerCart(customerId, productId);
    }

    public void removeProductFromCustomerCart(int customerId, int productId) throws IOException, CustomerNotFoundException, ProductNotFoundException {
        service.removeProductFromCustomerCart(customerId, productId);
    }

    public void clearCustomerCart(int id) throws IOException, CustomerNotFoundException {
        service.clearCustomerCart(id);
    }
}
