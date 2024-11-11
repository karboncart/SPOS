package pra;
import java.util.*;

class PriorityProcess {
    int id, burstTime, priority, waitingTime, turnaroundTime;

    PriorityProcess(int id, int burstTime, int priority) {
        this.id = id;
        this.burstTime = burstTime;
        this.priority = priority;
    }
}

public class CPU {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int n = s.nextInt();

        PriorityProcess[] processes = new PriorityProcess[n];
        System.out.println("Enter burst time and priority for each process:");
        for (int i = 0; i < n; i++) {
            System.out.print("Process[" + (i + 1) + "]: ");
            int burstTime = s.nextInt();
            int priority = s.nextInt();
            processes[i] = new PriorityProcess(i + 1, burstTime, priority);
        }

        // Sort by priority (highest priority first)
        Arrays.sort(processes, (p1, p2) -> Integer.compare(p2.priority, p1.priority));

        processes[0].waitingTime = 0; // First process waiting time is 0
        int totalWT = 0, totalTAT = 0;

        for (int i = 1; i < n; i++) {
            processes[i].waitingTime = processes[i - 1].waitingTime + processes[i - 1].burstTime;
            totalWT += processes[i].waitingTime;
        }

        System.out.println("\nProcess | Burst Time | Priority | Waiting Time | Turnaround Time");
        for (PriorityProcess p : processes) {
            p.turnaroundTime = p.waitingTime + p.burstTime;
            totalTAT += p.turnaroundTime;
            System.out.println("P[" + p.id + "]\t|\t" + p.burstTime + "\t|\t" + p.priority + "\t|\t" + p.waitingTime + "\t|\t" + p.turnaroundTime);
        }

        System.out.println("\nAverage Waiting Time: " + (float) totalWT / n);
        System.out.println("Average Turnaround Time: " + (float) totalTAT / n);
    }
}