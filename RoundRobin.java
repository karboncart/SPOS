package pra;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Scanner;

class RRProcess {
    int id, burstTime, remainingTime;

    RRProcess(int id, int burstTime) {
        this.id = id;
        this.burstTime = burstTime;
        this.remainingTime = burstTime;
    }
}

public class RoundRobin {
    public static void main(String[] args) {
        Scanner s = new Scanner(System.in);
        System.out.print("Enter the number of processes: ");
        int num = s.nextInt();
        System.out.print("Enter quantum time: ");
        int quantum = s.nextInt();

        Queue<RRProcess> queue = new LinkedList<>();
        for (int i = 1; i <= num; i++) {
            System.out.print("Burst time for Process[" + i + "]: ");
            int burstTime = s.nextInt();
            queue.add(new RRProcess(i, burstTime));
        }

        int time = 0;
        System.out.print("0");
        while (!queue.isEmpty()) {
            RRProcess current = queue.poll();
            if (current.remainingTime > quantum) {
                time += quantum;
                current.remainingTime -= quantum;
                queue.add(current); // Re-add to the end of the queue
                System.out.print(" | P[" + current.id + "] | " + time);
            } else {
                time += current.remainingTime;
                System.out.print(" | P[" + current.id + "] | " + time);
            }
        }
        System.out.println();
    }
}