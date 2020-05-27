package com.hasanural.containercalculator.DataAccess.Entity;

public class Container {
    public int id;
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

    public Container(){}
    public Container(int id, String definition, int length, int width, int height,
                     int tolerance_length, int tolerance_width, int tolerance_height,
                     int weight, int weight_Empty, double volume, String color) {
        this.id = id;
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

    public int getWeightEmpty() {
        return weight_Empty;
    }

    public void setWeightEmpty(int weight_Empty) {
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

    public int getToleranceLength() {
        return tolerance_length;
    }

    public void setToleranceLength(int tolerance_length) {
        this.tolerance_length = tolerance_length;
    }

    public int getToleranceWidth() {
        return tolerance_width;
    }

    public void setToleranceWidth(int tolerance_width) {
        this.tolerance_width = tolerance_width;
    }

    public int getToleranceHeight() {
        return tolerance_height;
    }

    public void setToleramceHeight(int tolerance_height) {
        this.tolerance_height = tolerance_height;
    }
}
