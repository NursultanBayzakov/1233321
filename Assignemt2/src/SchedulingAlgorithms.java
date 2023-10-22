import java.util.*;

public class SchedulingAlgorithms {
    public static void main(String[] args) {
        List<Process> processes = new ArrayList<>();
        processes.add(new Process(1, 10, 3, 0));
        processes.add(new Process(2, 6, 5, 2));
        processes.add(new Process(3, 8, 4, 1));
        processes.add(new Process(4, 7, 2, 3));

//         FCFS(processes);
//         SJN(processes);
//         PriorityScheduling(processes);
//         ShortestRemainingTime(processes);
//         MultipleLevelQueues(processes);

    }

    public static void FCFS(List<Process> processes) {
        Queue<Process> queue = new LinkedList<>(processes);
        while (!queue.isEmpty()) {
            Process process = queue.poll();
            System.out.println("Executing process " + process.pid);
        }
    }

    public static void SJN(List<Process> processes) {
        List<Process> sortedProcesses = new ArrayList<>(processes);
        sortedProcesses.sort(Comparator.comparingInt(p -> p.burstTime));
        FCFS(sortedProcesses);
    }

    public static void PriorityScheduling(List<Process> processes) {
        List<Process> sortedProcesses = new ArrayList<>(processes);
        sortedProcesses.sort(Comparator.comparingInt(p -> p.priority));
        FCFS(sortedProcesses);
    }

    public static void ShortestRemainingTime(List<Process> processes) {
        List<Process> sortedProcesses = new ArrayList<>(processes);
        sortedProcesses.sort(Comparator.comparingInt(p -> p.arrivalTime));

        Queue<Process> queue = new LinkedList<>();
        int currentTime = 0;

        while (!sortedProcesses.isEmpty() || !queue.isEmpty()) {
            while (!sortedProcesses.isEmpty() && sortedProcesses.get(0).arrivalTime <= currentTime) {
                queue.add(sortedProcesses.remove(0));
            }

            if (queue.isEmpty()) {
                currentTime++;
            } else {
                Process process = queue.poll();
                System.out.println("Executing process " + process.pid);
                process.remainingBurstTime--;
                currentTime++;
                if (process.remainingBurstTime > 0) {
                    queue.add(process);
                }
            }
        }
    }

    public static void MultipleLevelQueues(List<Process> processes) {
        List<Process> bestFitQueue = new ArrayList<>();
        List<Process> firstFitQueue = new ArrayList<>();

        for (Process process : processes) {
            if (process.burstTime <= 5) {
                bestFitQueue.add(process);
            } else {
                firstFitQueue.add(process);
            }
        }
        FCFS(bestFitQueue);
        FCFS(firstFitQueue);
    }
}
