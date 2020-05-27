package com.hasanural.containercalculator.DataAccess.Model;

public class ContainerPostModel {
    public int ID;
    public int Length;
    public int Width;
    public int Height;

    public ContainerPostModel() {
    }

    public ContainerPostModel(int ID, int length, int width, int height) {
        this.ID = ID;
        Length = length;
        Width = width;
        Height = height;
    }
}
