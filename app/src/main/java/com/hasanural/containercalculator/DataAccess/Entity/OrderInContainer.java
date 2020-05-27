package com.hasanural.containercalculator.DataAccess.Entity;

public class OrderInContainer {
    public int id;
    public int orderId;
    public String definition;
    public int length;
    public int width;
    public int height;
    public int tolerance_length;
    public int tolerance_width;
    public int tolerance_height;
    public int weight;
    public int weight_Empty;
    public double volume;
    public String color;
    public OrderInContainer(){}
    public OrderInContainer(int id, int orderId, String definition, int length, int width, int height,
                            int tolerance_length, int tolerance_width, int tolerance_height,
                            int weight, int weight_Empty, double volume, String color) {
        this.id = id;
        this.orderId = orderId;
        this.definition = definition;
        this.length = length;
        this.width = width;
        this.height = height;
        this.tolerance_length = tolerance_length;
        this.tolerance_width = tolerance_width;
        this.tolerance_height = tolerance_height;
        this.weight = weight;
        this.weight_Empty = weight_Empty;
        this.volume = volume;
        this.color = color;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderId() {
        return orderId;
    }

    public void setOrderId(int orderId) {
        this.orderId = orderId;
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

    public int getTolerance_length() {
        return tolerance_length;
    }

    public void setTolerance_length(int tolerance_length) {
        this.tolerance_length = tolerance_length;
    }

    public int getTolerance_width() {
        return tolerance_width;
    }

    public void setTolerance_width(int tolerance_width) {
        this.tolerance_width = tolerance_width;
    }

    public int getTolerance_height() {
        return tolerance_height;
    }

    public void setTolerance_height(int tolerance_height) {
        this.tolerance_height = tolerance_height;
    }

    public int getWeight() {
        return weight;
    }

    public void setWeight(int weight) {
        this.weight = weight;
    }

    public int getWeight_Empty() {
        return weight_Empty;
    }

    public void setWeight_Empty(int weight_Empty) {
        this.weight_Empty = weight_Empty;
    }

    public double getVolume() {
        return volume;
    }

    public void setVolume(double volume) {
        this.volume = volume;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }
}
