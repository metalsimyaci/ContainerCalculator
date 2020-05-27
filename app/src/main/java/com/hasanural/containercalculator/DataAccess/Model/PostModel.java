package com.hasanural.containercalculator.DataAccess.Model;
import java.util.ArrayList;
import java.util.List;

public class PostModel {
    public ContainerPostModel Container;
    public ArrayList<ProductPostModel> ItemsToPack;

    public PostModel() {
    }

    public PostModel(ContainerPostModel container, ArrayList<ProductPostModel> itemsToPack) {
        Container = container;
        ItemsToPack = itemsToPack;
    }
}
