package model;

public class ResourceManager {
    private int[] available;
    private int[][] maximum;
    private int[][] allocation;
    private int[][] need;
    private int numProcesses;
    private int numResources;

    public ResourceManager(int numProcesses, int numResources) {
        this.numProcesses = numProcesses;
        this.numResources = numResources;
        available = new int[numResources];
        maximum = new int[numProcesses][numResources];
        allocation = new int[numProcesses][numResources];
        need = new int[numProcesses][numResources];
    }

    public boolean isSafeState() {
        int[] work = available.clone();
        boolean[] finish = new boolean[numProcesses];
        while (true) {
            boolean found = false;
            for (int i = 0; i < numProcesses; i++) {
                if (!finish[i] && canFinish(i, work)) {
                    for (int j = 0; j < numResources; j++) {
                        work[j] += allocation[i][j];
                    }
                    finish[i] = true;
                    found = true;
                }
            }
            if (!found) break;
        }
        for (boolean f : finish) {
            if (!f) return false;
        }
        return true;
    }

    private boolean canFinish(int i, int[] work) {
        for (int j = 0; j < numResources; j++) {
            if (need[i][j] > work[j]) return false;
        }
        return true;
    }

    public boolean requestResources(int processId, int[] request) {
        for (int i = 0; i < numResources; i++) {
            if (request[i] > need[processId][i]) return false;
        }
        for (int i = 0; i < numResources; i++) {
            available[i] -= request[i];
            allocation[processId][i] += request[i];
            need[processId][i] -= request[i];
        }
        if (!isSafeState()) {
            for (int i = 0; i < numResources; i++) {
                available[i] += request[i];
                allocation[processId][i] -= request[i];
                need[processId][i] += request[i];
            }
            return false;
        }
        return true;
    }

    // Other resource management methods
}
