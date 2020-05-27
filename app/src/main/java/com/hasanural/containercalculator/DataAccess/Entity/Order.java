package com.hasanural.containercalculator.DataAccess.Entity;

import java.util.ArrayList;
import java.util.List;

public class Order {
    public int id;
    public String orderNo;
    public String description;
    public OrderInContainer container;
    public ArrayList<OrderInProduct> products;
    public OrderResult result;

    public Order(){
        this.container=new OrderInContainer();
        this.products=new ArrayList<>();
        this.result=new OrderResult();
    }
    public Order(int id, String orderNo, String description,
                 OrderInContainer container,ArrayList<OrderInProduct> products,
                 OrderResult result) {
        this.id = id;
        this.orderNo = orderNo;
        this.description = description;
        this.container=container;
        this.products=products;
        this.result=result;
    }
    public Order(int id, String orderNo, String description) {
        this.id = id;
        this.orderNo = orderNo;
        this.description = description;
        this.container=new OrderInContainer();
        this.products=new ArrayList<OrderInProduct>();
        this.result=new OrderResult();
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getOrderNo() {
        return orderNo;
    }

    public void setOrderNo(String orderNo) {
        this.orderNo = orderNo;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public OrderInContainer getContainer() {
        return container;
    }

    public void setContainer(OrderInContainer container) {
        this.container = container;
    }

    public ArrayList<OrderInProduct> getProducts() {
        return products;
    }

    public void setProducts(ArrayList<OrderInProduct> products) {
        this.products = products;
    }

    public OrderResult getResult() {
        return result;
    }

    public void setResult(OrderResult result) {
        this.result = result;
    }
}
