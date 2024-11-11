package practicals;

import java.util.*;

class Process{
	int pid;
	int burstTime;
	int arrivalTime;
	int priority;
	int remainingTime;
	int waitingTime;
	int turnaroundTime;
	int completionTime;
	int startTime;
	
	public Process(int pid,int arrivalTime,int burstTime, int priority) {
		this.pid=pid;
		this.arrivalTime=arrivalTime;
		this.burstTime=burstTime;
		this.remainingTime=burstTime;
	}
}

public class CPUScheduling {
	
	public static void fcfs(List<Process> processes) {
		System.out.println("\n FCFS Scheduling");
		int currentTime=0;
		for(Process p :processes) {
		p.waitingTime=Math.max(0, currentTime - p.arrivalTime);
		currentTime += p.burstTime;
		p.turnaroundTime=p.waitingTime + p.burstTime;
		System.out.println(" Process: " + p.pid + " Waiting Time: " + p.waitingTime +" Turnaround Time: " + p.turnaroundTime) ;
	
		}
	}
	public static void sjfPreemptive(List<Process> processes) {
		System.out.println("\n SNJ (Preemptive) Scheduling: ");
		int currentTime =0;
		int completed =0;
		int n= processes.size();
		Process currentProcess = null;
		
		while(completed < n) {
			Process nextProcess =null;
			for(Process p : processes) {
				if (p.arrivalTime <=currentTime && p.remainingTime > 0) {
					if(nextProcess == null || p.remainingTime < nextProcess.remainingTime) {
						nextProcess=p;
					}
				}
			}
			
			if(nextProcess !=null) {
				currentProcess = nextProcess;
				currentProcess.remainingTime--;
				currentTime++;
				if(currentProcess.remainingTime ==0) {
					completed++;
					currentProcess.turnaroundTime=currentTime - currentProcess.arrivalTime;
					currentProcess.waitingTime = currentProcess.turnaroundTime - currentProcess.burstTime;
					System.out.println("Process: " + currentProcess.pid + " Waiting Time: " + currentProcess.waitingTime + " Turnaround Time " + currentProcess.turnaroundTime);
					
				}
			}
			
			}
		}
	
	public static void priorityScheduling(List<Process> processes) {
        processes.sort(Comparator.comparingInt(p -> p.arrivalTime)); // Sort by arrival time
        int currentTime = 0;
        Queue<Process> queue = new PriorityQueue<>(Comparator.comparingInt(p -> p.priority)); // Priority queue by priority
        int index = 0;

        System.out.println("Priority Scheduling (Non-Preemptive):");

        while (!queue.isEmpty() || index < processes.size()) {
            // Add all processes that have arrived by current time
            while (index < processes.size() && processes.get(index).arrivalTime <= currentTime) {
                queue.add(processes.get(index));
                index++;
            }

            if (!queue.isEmpty()) {
                Process p = queue.poll(); // Process with highest priority
                p.startTime = currentTime;
                p.completionTime = currentTime + p.burstTime;
                p.turnaroundTime = p.completionTime - p.arrivalTime;
                p.waitingTime = p.turnaroundTime - p.burstTime;

                currentTime += p.burstTime;

                System.out.println("Process " + p.pid + ": Waiting Time = " + p.waitingTime +
                        ", Turnaround Time = " + p.turnaroundTime);
            } else {
                currentTime++; // Increment time if CPU is idle
            }
        }
    }	
	


    public static void roundRobin(ArrayList<Process> processes, int timeSlice) {
        int currentTime = 0;

        while (!processes.isEmpty()) {
            for (Process process : processes) {
                if (process.remainingTime > 0) {
                    int executedTime = Math.min(timeSlice, process.remainingTime);
                    currentTime += executedTime;
                    process.remainingTime -= executedTime;

                    if (process.remainingTime == 0) {
                        int waitingTime = currentTime - process.arrivalTime - process.burstTime;
                        int turnaroundTime = waitingTime + process.burstTime;
                        System.out.println("Round Robin: Process " + process.pid + ": Waiting Time = " + waitingTime + ", Turnaround Time = " + turnaroundTime);
             
                    }
                }
            }
        }
    }
	    										
	public static void main(String[] args) {
		List<Process> processes =Arrays.asList(
				new Process(1, 0, 5,3),
	            new Process(2, 1, 3,1),
	            new Process(3, 2, 8,2),
	            new Process(4, 3, 6,4)
				);
		processes.sort(Comparator.comparingInt(p ->p.arrivalTime));
		
		fcfs(new ArrayList<>(processes));
		sjfPreemptive(new ArrayList<>(processes));
		priorityScheduling(new ArrayList<>(processes));
		roundRobin(new ArrayList<>(processes),2);
		
	}

}
