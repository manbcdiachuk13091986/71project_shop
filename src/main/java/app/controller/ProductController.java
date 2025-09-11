package app.controller;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repository.ProductRepository;
import app.service.ProductService;

import java.io.IOException;
import java.util.List;

public class ProductController {
    private final ProductService service;

    public ProductController() throws IOException {
        service = new ProductService();
    }

    public Product save(String title, double price) throws ProductSaveException, IOException {
        Product product = new Product(title, price);
        return service.save(product);
    }

    public List<Product> getAllActiveProducts() throws IOException {
        return service.getAllActiveProducts();

    }

    public Product getActiveProductById(int id) throws IOException, ProductNotFoundException {
        return service.getActiveProductById(id);

    }

    public void update(int id, double price) throws ProductUpdateException, IOException {
        Product product = new Product(id, price);
        service.update(product);
    }

    public void deleteById(int id) throws IOException, ProductNotFoundException {
        service.deleteById(id);
    }

    public void deleteByTitle(String title) throws IOException {
        service.deleteByTitle(title);

    }

    public void restoreById(int id) throws IOException, ProductNotFoundException {
        service.restoreById(id);

    }

    public int getActiveProductsCount() throws IOException {
       return service.getActiveProductsCount();

    }

    public double getActiveProductsTotalCost() throws IOException {
        return service.getActiveProductTotalCost();


    }

    public double getActiveProductsAveragePrice() throws IOException {
        return service.getActiveProductAveragePrice();

    }

}

