package app.service;

import app.domain.Product;
import app.exceptions.ProductNotFoundException;
import app.exceptions.ProductSaveException;
import app.exceptions.ProductUpdateException;
import app.repository.ProductRepository;

import java.io.IOException;
import java.util.List;

public class ProductService {
    private final ProductRepository repository;

    public ProductService() throws IOException {
        repository = new ProductRepository();
    }

    public Product save(Product product) throws ProductSaveException {
        if (product == null) {
            throw new ProductSaveException("Продукт не может быть null");
        }
        String title = product.getTitle();
        if (title == null || title.trim().isEmpty()) {
            throw new ProductSaveException("Наименование продукта не может быть пустым");
        }
        if (product.getPrice() <= 0) {
            throw new ProductSaveException("Цена продукта не должна быть отрицательной");
        }
        product.setActive(true);
        return repository.save(product);
    }

    public List<Product> getAllActiveProducts() throws IOException {
        return repository.findAll()
                .stream()
                .filter(Product::isActive)
                .filter(x -> x.isActive())
                .toList();
    }

    public Product getActiveProductById(int id) throws IOException, ProductNotFoundException {
        Product product = repository.findById(id);
        if (product == null || !product.isActive()) {
            throw new ProductNotFoundException(id);
        }
        return product;
    }
    public void update(Product product) throws ProductUpdateException {
        if (product == null){
            throw new ProductUpdateException("Продукт не может быть null");
        }
        if (product.getPrice() <= 0){
            throw new ProductUpdateException("Цена продукта должна быть положительной");
        }
        repository.update(product);
    }
    public void deleteById(int id) throws IOException, ProductNotFoundException {
        getActiveProductById(id).setActive(false);
    }
    public void deleteByTitle(String title) throws IOException {
        getAllActiveProducts()
                .stream()
                .filter(x -> x.getTitle().equals(title))
                .forEach(x -> x.setActive(false));
    }
    public void restoreById(int id) throws IOException, ProductNotFoundException {
        Product product = repository.findById(id);
        if (product != null){
            product.setActive(true);
        }else {
            throw new ProductNotFoundException(id);
        }

    }
    public int getActiveProductsCount() throws IOException {
        return getAllActiveProducts().size();
    }
    public double getActiveProductTotalCost(){
        return getAllActiveProducts()
                .stream()
                .mapToDouble(Product::getPrice)
                .sum();
    }
    public double getActiveProductAveragePrice() throws IOException {
        int productCount = getActiveProductsCount();
        if (productCount == 0){
            return 0.0;
        }
        return getActiveProductTotalCost()/productCount;
    }

}

