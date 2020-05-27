package com.hasanural.containercalculator.DataAccess.Model;

import com.hasanural.containercalculator.DataAccess.Entity.Container;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInContainer;
import com.hasanural.containercalculator.DataAccess.Entity.OrderInProduct;
import com.hasanural.containercalculator.DataAccess.Entity.Product;

public class SingletonOrderModel {
    private static SingletonOrderModel INSTANCE=null;
    private SingletonOrderModel(){};
    public static SingletonOrderModel getInstance(){
        if(INSTANCE==null)
            INSTANCE=new SingletonOrderModel();
        return INSTANCE;
    }

    public OrderInProduct product;
    public OrderInContainer container;
    public Container containerModel;
    public Product productModel;


    public Product getProductModel() {
        return productModel;
    }

    public void setProductModel(Product productModel) {
        this.productModel = productModel;
    }

    public Container getContainerModel() {
        return containerModel;
    }

    public void setContainerModel(Container containerModel) {
        this.containerModel = containerModel;
    }


    public OrderInContainer getContainer() {
        return container;
    }

    public void setContainer(OrderInContainer container) {
        this.container = container;
    }



    public void setProduct(OrderInProduct product) {
        this.product = product;
    }

    public OrderInProduct getProduct() {
        return product;
    }
}
