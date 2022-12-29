package roundRobin;

public class roundRobin {
	static void findWTime (int process[], int n, int bt[], int wt[], int qtum) {
		// burst times
		int rem_bt[] = new int[n];
		
		for (int i = 0; i < n; i++)
			rem_bt[i] = bt[i];
			
		int Ct = 0;
		
		while (true) {
			boolean done = true;
			for (int i = 0; i < n; i++) {
				if (rem_bt[i] > 0) {
					done = false;
					// there is a pending process
					
					if (rem_bt[i] > qtum) {
						Ct += qtum;
						rem_bt[i] -= qtum;
					}
					else {
						Ct += rem_bt[i];
						wt[i] = Ct - bt[i];
						rem_bt[i] = 0;
					}
				}
			}
			if (done == true)
				break;
		}
	}
	
	// method to calc turn around time
	static void findTATime (int process[], int n, int bt[], int wt[], int tat[]) {
		// calc TA time by adding
		for (int i = 0; i < n; i++) 
			tat[i] = bt[i] + wt[i];
		
		
	}
	
	// calc average time
		static void findavgTime (int process[], int n, int bt[], int qtum) {
			int wt[] = new int[n], tat[] = new int[n];
			int total_wt = 0, total_tat = 0;
			
			// func to find waiting time of all processes
			findWTime(process, n, bt, wt, qtum);
			
			// func to find turn around time of all processes
			findTATime(process, n, bt, wt, tat);
			
			// Display processes along with all details
			System.out.println("PROCESS" + "BURST TIME" + "WAITING TIME" + "TURN AROUND TIME");
			
			// calc total waiting time and total turn
			for (int i = 0; i < n; i++) {
				total_wt += wt[i];
				total_tat += tat[i];
				System.out.println("" + (i + 1) + "\t\t" + bt[i] + "\t" + wt[i] + "\t\t" + tat[i]);
			}
			
			System.out.println("Avarage of waiting time = " + (float)total_wt/(float)n);
			System.out.println("Avarage of turn around time = " + (float)total_tat/(float)n);
			
			
			
		}
		
		public static void main(String[] args) {
			// process id's
			int process[] = {1, 2, 3};
			int n = process.length;
			
			// Burst time of all processes
			int burst_time[] = {15, 10, 9};
			
			// time quantum
			int qtum = 4;
			
			findavgTime(process, n, burst_time, qtum);
			
		}
	
}
