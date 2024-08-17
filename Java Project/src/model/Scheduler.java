package model;

import java.util.*;
import model.Process;

public class Scheduler {
    private Queue<Process> processQueue;
    private PriorityQueue<Process> priorityQueue;
    private List<Process> completedProcesses;

    public Scheduler() {
        processQueue = new LinkedList<>();
        priorityQueue = new PriorityQueue<>(Comparator.comparingInt(Process::getPriority));
        completedProcesses = new ArrayList<>();
    }

    public void addProcess(Process process) {
        processQueue.add(process);
        priorityQueue.add(process);
    }

    public void scheduleFCFS() {
        while (!processQueue.isEmpty()) {
            Process process = processQueue.poll();
            executeProcess(process);
        }
    }

    public void scheduleRoundRobin(int timeQuantum) {
        Queue<Process> roundRobinQueue = new LinkedList<>(processQueue);
        while (!roundRobinQueue.isEmpty()) {
            Process process = roundRobinQueue.poll();
            if (process.getRemainingTime() > timeQuantum) {
                process.setRemainingTime(process.getRemainingTime() - timeQuantum);
                roundRobinQueue.add(process);
            } else {
                process.setRemainingTime(0);
                completedProcesses.add(process);
            }
        }
    }

    public void schedulePriority() {
        while (!priorityQueue.isEmpty()) {
            Process process = priorityQueue.poll();
            executeProcess(process);
        }
    }

    public void scheduleSJF() {
        PriorityQueue<Process> minHeap = new PriorityQueue<>(Comparator.comparingInt(Process::getBurstTime));
        minHeap.addAll(processQueue);
        while (!minHeap.isEmpty()) {
            Process process = minHeap.poll();
            executeProcess(process);
        }
    }

    private void executeProcess(Process process) {
        // Simulate process execution
        try {
            Thread.sleep(process.getBurstTime() * 100); // Simulate process execution time
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        completedProcesses.add(process);
    }

    public void displayCompletedProcesses() {
        for (Process process : completedProcesses) {
            System.out.println("Process " + process.getId() + " completed.");
        }
    }
}
