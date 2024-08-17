package view;

import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import model.Process;
import model.Scheduler;

public class ProcessSchedulerController {
    private Scheduler scheduler;

    @FXML
    private TextArea processOutput;

    public ProcessSchedulerController() {
        scheduler = new Scheduler();
    }

    @FXML
    private void initialize() {
        // Initialize if necessary
    }

    @FXML
    private void handleAddProcess() {
        // Add processes manually or via UI input fields
        scheduler.addProcess(new Process(1, 10, 1, 0));
        scheduler.addProcess(new Process(2, 5, 2, 2));
        scheduler.addProcess(new Process(3, 8, 1, 4));
    }

    @FXML
    private void handleScheduleFCFS() {
        scheduler.scheduleFCFS();
        displayCompletedProcesses();
    }

    @FXML
    private void handleScheduleRoundRobin() {
        scheduler.scheduleRoundRobin(2);
        displayCompletedProcesses();
    }

    @FXML
    private void handleSchedulePriority() {
        scheduler.schedulePriority();
        displayCompletedProcesses();
    }

    @FXML
    private void handleScheduleSJF() {
        scheduler.scheduleSJF();
        displayCompletedProcesses();
    }

    private void displayCompletedProcesses() {
        StringBuilder output = new StringBuilder();
        for (Process process : scheduler.getCompletedProcesses()) {
            output.append("Process ").append(process.getId()).append(" completed.\n");
        }
        processOutput.setText(output.toString());
    }
}
