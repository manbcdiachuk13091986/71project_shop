package app.service;

import app.domain.Customer;
import app.domain.Product;
import app.exceptions.CustomerNotFoundException;
import app.exceptions.CustomerSaveException;
import app.exceptions.CustomerUpdateException;
import app.exceptions.ProductNotFoundException;
import app.repository.CustomerRepository;

import java.io.IOException;
import java.util.List;

public class CustomerService {
    private final CustomerRepository repository;
    private final ProductService productService;

    public CustomerService() throws IOException {
        repository = new CustomerRepository();
        productService = new ProductService();
    }

    public Customer save(Customer customer) throws IOException, CustomerSaveException {
        if (customer == null) {
            throw new CustomerSaveException("Покупатель не может быть null");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerSaveException("Имя покупателя не может быть пустым");
        }
        customer.setActive(true);
        return repository.save(customer);
    }

    public List<Customer> getAllActiveCustomers() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .filter(Customer::isActive)
                .toList();
    }

    public Customer getActiveCustomerById(int id) throws IOException, CustomerNotFoundException {
        Customer customer = repository.findById(id);
        if (customer == null || !customer.isActive()) {
            throw new CustomerNotFoundException(id);

        }
        return customer;

    }

    public void update(Customer customer) throws CustomerUpdateException, IOException {
        if (customer == null) {
            throw new CustomerUpdateException("Покупатель не может быть null");
        }
        String name = customer.getName();
        if (name == null || name.trim().isEmpty()) {
            throw new CustomerUpdateException("Имя покупателя не может быть пустым");
        }
        repository.update(customer);
    }

    public void deleteById(int id) throws IOException, CustomerNotFoundException {
        getActiveCustomerById(id).setActive(false);
    }

    public void deleteByName(String name) throws IOException {
        getAllActiveCustomers()
                .stream()
                .filter(x -> x.getName().equals(name))
                .forEach(x -> x.setActive(false));
    }

    public void restoreById(int id) throws IOException, CustomerNotFoundException {
        Customer customer = repository.findById(id);
        if (customer != null) {
            customer.setActive(true);
        } else {
            throw new CustomerNotFoundException(id);
        }
    }

    public int getActiveCustomerCount() throws IOException {
        return getAllActiveCustomers().size();
    }

    public double getCustomerCardPrice(int id) throws IOException, CustomerNotFoundException {
        return getActiveCustomerById(id)
                .getProducts()
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .sum();
    }
    public double getCustomerCardAveragePrice(int id) throws IOException, CustomerNotFoundException {
        return getActiveCustomerById(id)
                .getProducts()
                .stream()
                .filter(Product::isActive)
                .mapToDouble(Product::getPrice)
                .average()
                .orElse(0.0);
    }
    public void addProductToCustomerCart(int customerId, int productId) throws IOException, CustomerNotFoundException, ProductNotFoundException {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.getProducts().add(product);
    }
    public void removeProductFromCustomerCart(int customerId, int productId) throws IOException, CustomerNotFoundException, ProductNotFoundException {
        Customer customer = getActiveCustomerById(customerId);
        Product product = productService.getActiveProductById(productId);
        customer.getProducts().remove(product);
    }
    public void clearCustomerCart(int id) throws IOException, CustomerNotFoundException {
        getActiveCustomerById(id).getProducts().clear();
    }
}
