class Process {
    int pid;
    int burstTime;
    int priority;
    int arrivalTime;
    int remainingBurstTime;

    public Process(int pid, int burstTime, int priority, int arrivalTime) {
        this.pid = pid;
        this.burstTime = burstTime;
        this.priority = priority;
        this.arrivalTime = arrivalTime;
        this.remainingBurstTime = burstTime;
    }
}

