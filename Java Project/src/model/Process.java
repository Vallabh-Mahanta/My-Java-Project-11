package model;

import java.util.ArrayList;
import java.util.List;

public class Process {
    private int id;
    private int burstTime;
    private int priority;
    private int arrivalTime;
    private int remainingTime;
    private List<Integer> allocatedResources;
    private List<Integer> requestedResources;

    public Process(int id, int burstTime, int priority, int arrivalTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.remainingTime = burstTime;
        this.allocatedResources = new ArrayList<>();
        this.requestedResources = new ArrayList<>();
    }

    // Getters and setters
    public int getId() {
        return id;
    }

    public int getBurstTime() {
        return burstTime;
    }

    public int getPriority() {
        return priority;
    }

    public int getArrivalTime() {
        return arrivalTime;
    }

    public int getRemainingTime() {
        return remainingTime;
    }

    public void setRemainingTime(int remainingTime) {
        this.remainingTime = remainingTime;
    }

    public List<Integer> getAllocatedResources() {
        return allocatedResources;
    }

    public void setAllocatedResources(List<Integer> allocatedResources) {
        this.allocatedResources = allocatedResources;
    }

    public List<Integer> getRequestedResources() {
        return requestedResources;
    }

    public void setRequestedResources(List<Integer> requestedResources) {
        this.requestedResources = requestedResources;
    }

    // Other utility methods
}
