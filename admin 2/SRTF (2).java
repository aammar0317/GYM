import java.util.*;

public class SRTF {
    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        int n, time = 0, total = 0;
        float waitingTime = 0, turnaroundTime = 0;

        System.out.print("Enter the number of processes: ");
        n = sc.nextInt();

        int[] burstTime = new int[n];
        int[] remainingTime = new int[n];
        int[] arrivalTime = new int[n];
        int[] completionTime = new int[n];
        boolean[] finished = new boolean[n];

        for (int i = 0; i < n; i++) {
            System.out.print("Enter burst time for process " + (i+1) + ": ");
            burstTime[i] = sc.nextInt();
            remainingTime[i] = burstTime[i];
            System.out.print("Enter arrival time for process " + (i+1) + ": ");
            arrivalTime[i] = sc.nextInt();
        }

        while (total != n) {
            int shortest = -1;
            for (int i = 0; i < n; i++) {
                if (!finished[i] && (shortest == -1 || remainingTime[i] < remainingTime[shortest])) {
                    shortest = i;
                }
            }

            if (arrivalTime[shortest] <= time) {
                remainingTime[shortest]--;

                if (remainingTime[shortest] == 0) {
                    finished[shortest] = true;
                    total++;
                    completionTime[shortest] = time+1;
                    waitingTime += (completionTime[shortest] - burstTime[shortest] - arrivalTime[shortest]);
                    turnaroundTime += (completionTime[shortest] - arrivalTime[shortest]);
                }
            }

            time++;
        }

        System.out.println("Process\tBurst Time\tArrival Time\tWaiting Time\tTurnaround Time");

        for (int i = 0; i < n; i++) {
            System.out.println((i+1) + "\t\t" + burstTime[i] + "\t\t" + arrivalTime[i] + "\t\t" + (completionTime[i] - burstTime[i] - arrivalTime[i]) + "\t\t" + (completionTime[i] - arrivalTime[i]));
        }

        System.out.println("Average Waiting Time: " + (waitingTime/n));
        System.out.println("Average Turnaround Time: " + (turnaroundTime/n));
    }
}
