package com.hasanural.containercalculator.DataAccess.Entity;

import java.util.ArrayList;

public class OrderResultStep {
    public int id;
    public int orderResultId;
    public int packedCount;
    public double percentContainerVolumePacked;
    public double packedVolume;
    public double percentItemVolumePacked;
    public double percentItemPacked;
    public ArrayList<OrderResultStepDetail> details;

    public OrderResultStep(){}
    public OrderResultStep(int id, int orderResultId, int packedCount, double percentContainerVolumePacked, double packedVolume,
                           double percentItemVolumePacked, double percentItemPacked, ArrayList<OrderResultStepDetail> details) {
        this.id = id;
        this.orderResultId = orderResultId;
        this.packedCount = packedCount;
        this.percentContainerVolumePacked = percentContainerVolumePacked;
        this.packedVolume = packedVolume;
        this.percentItemVolumePacked = percentItemVolumePacked;
        this.percentItemPacked = percentItemPacked;
        this.details = details;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getOrderResultId() {
        return orderResultId;
    }

    public void setOrderResultId(int orderResultId) {
        this.orderResultId = orderResultId;
    }

    public int getPackedCount() {
        return packedCount;
    }

    public void setPackedCount(int packedCount) {
        this.packedCount = packedCount;
    }

    public double getPercentContainerVolumePacked() {
        return percentContainerVolumePacked;
    }

    public void setPercentContainerVolumePacked(double percentContainerVolumePacked) {
        this.percentContainerVolumePacked = percentContainerVolumePacked;
    }

    public double getPackedVolume() {
        return packedVolume;
    }

    public void setPackedVolume(double packedVolume) {
        this.packedVolume = packedVolume;
    }

    public double getPercentItemVolumePacked() {
        return percentItemVolumePacked;
    }

    public void setPercentItemVolumePacked(double percentItemVolumePacked) {
        this.percentItemVolumePacked = percentItemVolumePacked;
    }

    public double getPercentItemPacked() {
        return percentItemPacked;
    }

    public void setPercentItemPacked(double percentItemPacked) {
        this.percentItemPacked = percentItemPacked;
    }

    public ArrayList<OrderResultStepDetail> getDetails() {
        return details;
    }

    public void setDetails(ArrayList<OrderResultStepDetail> details) {
        this.details = details;
    }
}
