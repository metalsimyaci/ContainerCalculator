package com.hasanural.containercalculator.DataAccess.Entity;

import java.util.ArrayList;
import java.util.List;

public class OrderResult {
    public int id;
    public int orderId;
    public int totalContainerCount;
    public boolean isComplatePack;
    public double packTimeInMillisecond;
    public double percentContainerVolumePacked;
    public double percentItemValumePacked;
    public int totalPacked;
    public int packedItemsCount;
    public double packedItemsVolume;
    public boolean isError;
    public String message;
    public ArrayList<OrderResultStep> steps;

    public OrderResult(){}
    public OrderResult(int id, int orderId, int totalContainerCount, boolean isComplatePack,
                       double packTimeInMillisecond, double percentContainerVolumePacked,
                       double percentItemValumePacked, int totalPacked, int packedItemsCount,
                       double packedItemsVolume, boolean isError, String message,
                       ArrayList<OrderResultStep> steps) {
        this.id = id;
        this.orderId = orderId;
        this.totalContainerCount = totalContainerCount;
        this.isComplatePack = isComplatePack;
        this.packTimeInMillisecond = packTimeInMillisecond;
        this.percentContainerVolumePacked = percentContainerVolumePacked;
        this.percentItemValumePacked = percentItemValumePacked;
        this.totalPacked = totalPacked;
        this.packedItemsCount = packedItemsCount;
        this.packedItemsVolume = packedItemsVolume;
        this.isError = isError;
        this.message = message;
        this.steps=steps;
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

    public int getTotalContainerCount() {
        return totalContainerCount;
    }

    public void setTotalContainerCount(int totalContainerCount) {
        this.totalContainerCount = totalContainerCount;
    }

    public boolean getIsComplatePack() {
        return isComplatePack;
    }

    public void setIsComplatePack(boolean isComplatePack) {
        this.isComplatePack = isComplatePack;
    }

    public double getPackTimeInMillisecond() {
        return packTimeInMillisecond;
    }

    public void setPackTimeInMillisecond(double packTimeInMillisecond) {
        this.packTimeInMillisecond = packTimeInMillisecond;
    }

    public double getPercentContainerVolumePacked() {
        return percentContainerVolumePacked;
    }

    public void setPercentContainerVolumePacked(double percentContainerVolumePacked) {
        this.percentContainerVolumePacked = percentContainerVolumePacked;
    }

    public double getPercentItemValumePacked() {
        return percentItemValumePacked;
    }

    public void setPercentItemValumePacked(double percentItemValumePacked) {
        this.percentItemValumePacked = percentItemValumePacked;
    }

    public int getTotalPacked() {
        return totalPacked;
    }

    public void setTotalPacked(int totalPacked) {
        this.totalPacked = totalPacked;
    }

    public int getPackedItemsCount() {
        return packedItemsCount;
    }

    public void setPackedItemsCount(int packedItemsCount) {
        this.packedItemsCount = packedItemsCount;
    }

    public double getPackedItemsVolume() {
        return packedItemsVolume;
    }

    public void setPackedItemsVolume(double packedItemsVolume) {
        this.packedItemsVolume = packedItemsVolume;
    }

    public boolean isError() {
        return isError;
    }

    public void setError(boolean error) {
        isError = error;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }
    public ArrayList<OrderResultStep> getSteps() {
        return steps;
    }

    public void setSteps(ArrayList<OrderResultStep> steps) {
        this.steps = steps;
    }
}
