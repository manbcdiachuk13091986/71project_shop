package client;

import app.controller.CustomerController;
import app.controller.ProductController;

import java.util.Scanner;

public class Client {
    public static ProductController productController;
    private static CustomerController customerController;
    private static Scanner scanner;

    public static void main(String[] args) {
        try {


            productController = new ProductController();
            customerController = new CustomerController();
            scanner = new Scanner(System.in);
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }
        while (true) {
            System.out.println("Выберите желаемую операцию");
            System.out.println("1 - операции с продуктами");
            System.out.println("2 - операции с покупателями");
            System.out.println("0 - выход");
            String input = scanner.nextLine();

            switch (input) {
                case "1":
                    productOperations();
                    break;
                case "2":
                    customerOperations();
                    break;
                case "0":
                    return;
                default:
                    System.out.println("Некорректный ввод");
                    break;
            }
        }
    }

    public static void customerOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с продуктами:");
                System.out.println("1 - сохранить продукт");
                System.out.println("2 - получить все продукты");
                System.out.println("3 - получить продукт по идентификатору");
                System.out.println("4 - изменить продукт");
                System.out.println("5 - удалить продукт по идентификатору");
                System.out.println("6 - удалить продукт по названию");
                System.out.println("7 - восстановить продукт по идентификатору");
                System.out.println("8 - получить количество продуктов");
                System.out.println("9 - получить суммарную стоимость всех продуктов");
                System.out.println("10 - получить среднюю стоимость продукта");
                System.out.println("0 - выход");
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        System.out.println("Введите название продукта");
                        String title = scanner.nextLine();
                        System.out.println("Введите цену продукта");
                        double price = Double.parseDouble(scanner.nextLine());
                        System.out.println(productController.save(title, price));
                        break;
                    case "2":
                        productController.getAllActiveProducts().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор продукта");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(productController.getActiveProductById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор продукта");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новую цену продукта");
                        price = Double.parseDouble(scanner.nextLine());
                        productController.update(id, price);
                        break;
                    case "5":
                        System.out.println("Введите идентификатор продукта");
                        id = Integer.parseInt(scanner.nextLine());
                        productController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите название продукта");
                         title = scanner.nextLine();
                         productController.deleteByTitle(title);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор продукта");
                        id = Integer.parseInt(scanner.nextLine());
                        productController.restoreById(id);

                        break;
                    case "8":
                        System.out.println("Количество продуктов:" + productController.getActiveProductsCount());
                        break;
                    case "9":
                        System.out.println("Суммарная стоимость продуктов:" + productController.getActiveProductsTotalCost());
                        break;
                    case "10":
                        System.out.println("Средняя стоимость продуктов:" + productController.getAllActiveProducts());
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }

    public static void productOperations() {
        while (true) {
            try {
                System.out.println("Выберите желаемую операцию с покупателями:");
                System.out.println("1 - сохранить покупателя");
                System.out.println("2 - получить всех покупателей");
                System.out.println("3 - получить покупателя по идентификатору");
                System.out.println("4 - изменить покупателя");
                System.out.println("5 - удалить покупателя по идентификатору");
                System.out.println("6 - удалить покупателя по имени");
                System.out.println("7 - восстановить покупателя по идентификатору");
                System.out.println("8 - получить количество покупателей");
                System.out.println("9 - получить стоимость корзины покупателя");
                System.out.println("10 - получить среднюю стоимость продукта в корзине покупателя");
                System.out.println("11 - добавить товар в корзину покупателя");
                System.out.println("12 - удалить товар из корзины покупателя");
                System.out.println("13 - очистить корзину покупателя");
                System.out.println("0 - выход");
                String input = scanner.nextLine();
                switch (input) {
                    case "1":
                        System.out.println("Введите имя покупателя");
                        String name = scanner.nextLine();
                        System.out.println(customerController.save(name));

                    case "2":
                        customerController.getAllActiveCustomers().forEach(System.out::println);
                        break;
                    case "3":
                        System.out.println("Введите идентификатор");
                                int id = Integer.parseInt(scanner.nextLine());
                        System.out.println(customerController.getActiveCustomerById(id));
                        break;
                    case "4":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите новое имя покупателя");
                        name = scanner.nextLine();
                        customerController.update(id, name);

                        break;
                    case "5":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        customerController.deleteById(id);
                        break;
                    case "6":
                        System.out.println("Введите имя покупателя");
                        name = scanner.nextLine();
                        customerController.deleteByName(name);
                        break;
                    case "7":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        customerController.restoreById(id);
                        break;
                    case "8":
                        System.out.println("Количество покупателей: " +
                                customerController.getActiveCustomerCount());
                        break;
                    case "9":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Стоимость корзины покупателя: " +
                                customerController.getCustomerCartTotalPrice(id));
                        break;
                    case "10":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Средняя цена корзины покупателя: " +
                                customerController.getCustomerCartAveragePrice(id));

                        break;
                    case "11":
                        System.out.println("Введите идентификатор покупателя");
                        int customerId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта");
                        int productId = Integer.parseInt(scanner.nextLine());
                        customerController.addProductToCustomerCart(customerId, productId);
                        break;
                    case "12":
                        System.out.println("Введите идентификатор покупателя");
                        customerId = Integer.parseInt(scanner.nextLine());
                        System.out.println("Введите идентификатор продукта");
                        productId = Integer.parseInt(scanner.nextLine());
                        customerController.removeProductFromCustomerCart(customerId, productId);
                        break;
                    case "13":
                        System.out.println("Введите идентификатор");
                        id = Integer.parseInt(scanner.nextLine());
                        customerController.clearCustomerCart(id);
                        break;
                    case "0":
                        return;
                    default:
                        System.out.println("Некорректный ввод!");
                        break;
                }
            } catch (Exception e) {
                System.out.println(e.getMessage());
            }
        }
    }
}
