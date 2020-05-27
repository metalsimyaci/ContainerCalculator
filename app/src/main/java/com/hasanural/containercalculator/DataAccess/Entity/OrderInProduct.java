package com.hasanural.containercalculator.DataAccess.Entity;

public class OrderInProduct {
    public int id;
    public String definition;
    public int length;
    public int width;
    public int height;
    public int weight;
    public int quantity;
    public String color;

    public OrderInProduct(){}
    public OrderInProduct(int id, String definition, int length, int width, int height,
                          int weight, int quantity, String color) {
        this.id = id;
        this.definition = definition;
        this.length = length;
        this.width = width;
        this.height = height;
        this.weight = weight;
        this.quantity = quantity;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDefinition() {
        return definition;
    }

    public void setDefinition(String definition) {
        this.definition = definition;
    }

    public int getLength() {
        return length;
    }

    public void setLength(int length) {
        this.length = length;
    }

    public int getWidth() {
        return width;
    }

    public void setWidth(int width) {
        this.width = width;
    }

    public int getHeight() {
        return height;
    }

    public void setHeight(int height) {
        this.height = height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
