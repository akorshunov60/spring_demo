package ru.gb.spring_demo;

import org.springframework.context.annotation.AnnotationConfigApplicationContext;
import org.springframework.stereotype.Component;
import ru.gb.spring_demo.component.Cart;
import ru.gb.spring_demo.component.CartFactory;
import ru.gb.spring_demo.component.Product;
import ru.gb.spring_demo.component.ProductRepository;
import ru.gb.spring_demo.config.ApplicationConfiguration;

import javax.annotation.PostConstruct;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.util.ArrayList;
import java.util.List;

@Component
public class Main {
    private final ProductRepository productRepository;
    private final CartFactory cartFactory;

    public Main(ProductRepository productRepository, CartFactory cartFactory) {
        this.productRepository = productRepository;
        this.cartFactory = cartFactory;
    }

    public static void main(String[] args) {
        new AnnotationConfigApplicationContext(ApplicationConfiguration.class);
    }

    @PostConstruct
    public void start() throws IOException {

        List<Product> productsAddToCart = new ArrayList<>();
        Cart cartAdd;

        try (BufferedReader reader = new BufferedReader(new InputStreamReader(System.in))) {

            while (true) {

                System.out.println(
                        "Введите list - если хотите получить список товаров" + "\n" +
                        "Введите add - если хотите добавить товар в корзину" + "\n" +
                        "Введите remove - если хотите удалить товар из корзины" + "\n" +
                        "Введите quit - если хотите покинуть приложение");

                switch (reader.readLine()) {

                    case "list" -> {
                        final List<Product> products = productRepository.findAll();
                        products.forEach(item -> System.out.println(
                                item.getId() + " " + item.getName() + " " + item.getPrice()));
                        final Cart cart = cartFactory.createCart();
                        System.out.println("Instance of cart" + cart);
                    }

                    case "add" -> {

                        System.out.println("Введите номер товара, который вы хотите добавить в корзину");
                        productsAddToCart.add(productRepository.findById(Integer.parseInt(reader.readLine())));

                        System.out.println("Введите номер товара, который вы хотите добавить в корзину");
                        productsAddToCart.add(productRepository.findById(Integer.parseInt(reader.readLine())));

                        System.out.println("Введите номер товара, который вы хотите добавить в корзину");
                        productsAddToCart.add(productRepository.findById(Integer.parseInt(reader.readLine())));

                        productsAddToCart.forEach(item -> System.out.println(
                                    item.getId() + " " + item.getName() + " " + item.getPrice()));
                        cartAdd = cartFactory.createCart();
                        System.out.println("Instance of cart" + cartAdd);
                    }

                    case "remove" -> {

                        productsAddToCart.forEach(item -> System.out.println(
                                item.getId() + " " + item.getName() + " " + item.getPrice()));
                        System.out.println("Введите номер товара, который вы хотите удалить из корзины");
                        productsAddToCart.remove(productRepository.findById(Integer.parseInt(reader.readLine())));

                        System.out.println("Введите номер товара, который вы хотите удалить из корзины");
                        productsAddToCart.remove(productRepository.findById(Integer.parseInt(reader.readLine())));

                        System.out.println("Введите номер товара, который вы хотите удалить из корзины");
                        productsAddToCart.remove(productRepository.findById(Integer.parseInt(reader.readLine())));

                        productsAddToCart.forEach(item -> System.out.println(
                                item.getId() + " " + item.getName() + " " + item.getPrice()));
                        Cart cartRemove = cartFactory.createCart();
                        System.out.println("Instance of cart" + cartRemove);
                    }

                    case "quit" -> {
                        System.exit(0);
                    }
                }
            }
        }
    }
}
