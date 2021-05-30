package ru.gb.spring_demo.component;

public class Product {
    private int id;
    private String name;
    private int price;// Для денежных расчетов лучше использовать тип BigDecimal или создавать отдельный класс.

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }
}
