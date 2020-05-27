package com.hasanural.containercalculator.DataAccess.Entity;

public class OrderResultStepDetail {
    public int id;
    public int stepId;
    public int itemId;
    public double packedCount;
    public double percentPackedItem;

    public OrderResultStepDetail(){}

    public OrderResultStepDetail(int id, int stepId, int itemId, double packedCount, double percentPackedItem) {
        this.id = id;
        this.stepId = stepId;
        this.itemId = itemId;
        this.packedCount = packedCount;
        this.percentPackedItem = percentPackedItem;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getStepId() {
        return stepId;
    }

    public void setStepId(int stepId) {
        this.stepId = stepId;
    }

    public int getItemId() {
        return itemId;
    }

    public void setItemId(int itemId) {
        this.itemId = itemId;
    }

    public double getPackedCount() {
        return packedCount;
    }

    public void setPackedCount(double packedCount) {
        this.packedCount = packedCount;
    }

    public double getPercentPackedItem() {
        return percentPackedItem;
    }

    public void setPercentPackedItem(double percentPackedItem) {
        this.percentPackedItem = percentPackedItem;
    }
}
