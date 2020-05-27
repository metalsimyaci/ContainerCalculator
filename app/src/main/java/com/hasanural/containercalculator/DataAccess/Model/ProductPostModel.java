package com.hasanural.containercalculator.DataAccess.Model;

public class ProductPostModel {
    public int ID;
    public int Dim1;
    public int Dim2;
    public int Dim3;
    public int Quantity;

    public ProductPostModel() {
    }

    public ProductPostModel(int ID, int dim1, int dim2, int dim3, int quantity) {
        this.ID = ID;
        Dim1 = dim1;
        Dim2 = dim2;
        Dim3 = dim3;
        Quantity = quantity;
    }
}
