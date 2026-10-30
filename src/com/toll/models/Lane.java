package com.toll.models;

public class Lane {
    private int number;
    private String type, status;
    private boolean processingVehicle;

    public Lane(int number, String type) {
        this.number = number;
         this.type = type; 
         this.status = "Open";
    }
    public int getNumber() { 
        return number; 
    }
    public String getType() { 
        return type;
     }
    public String getStatus() {
         return status;
         }
    public boolean isProcessingVehicle() {
         return processingVehicle; 
        }
    public void setProcessingVehicle(boolean value) {
         processingVehicle = value; 
        }
    public boolean close() {
        if (processingVehicle) return false;
        status = "Closed"; return true;
    }
    public void open() { 
        status = "Open"; 
    }
    @Override public String toString() {
         return "Lane " + number + " | " + type + " | " + status; 
        }
}
