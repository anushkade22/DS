import java.util.*;

public class Bully {
	int max_processes;
    int coordinator;
    Boolean processes[];
    
    public Bully(int max) {
    	max_processes = max;
    	coordinator = max;
    	processes = new Boolean[max];
    	for(int i=0;i<max;i++) {
    		processes[i] = true;
    		System.out.println("P"+(i+1)+" is created");
    	}
    	System.out.println("Process P"+coordinator+" is the coordinator");
    }
    
    void display() {
    	for(int i=0;i<max_processes;i++) {
    		if(!processes[i]) {
    			System.out.println("P"+(i+1)+" is down");
    		}
    		else {
    			System.out.println("P"+(i+1)+" is up");
    		}
    	}
    	System.out.println("Process P"+coordinator+" is the coordinator");
    }
    
    void upProcesses(int pid) {
    	if(!processes[pid-1]) {
    		processes[pid-1]=true;
    		System.out.println("Process P"+pid+" is up");
    	}
    	else
    		System.out.println("Process P"+pid+" is already up");
    }
    
    void downProcesses(int pid) {
    	if(!processes[pid-1]) {
    		System.out.println("Process P"+pid+" is already down");
    	}
    	else
    		processes[pid-1]=false;
    		System.out.println("Process P"+pid+" is already down");
    }
    
    void runElection(int pid) {
    	coordinator = pid;
    	boolean keep = true;
    	for(int i = pid;i<max_processes && keep;i++) {
    		System.out.println("Election message sent from "+pid+" to "+ (i+1));
    		if(processes[i]) {
    			keep = false;
    			runElection(i+1);
    		}
    	}
    }
    
    public static void main(String args[]) {
    	Bully bully = null;
    	int max_processes = 0,pid=0;
    	int choice = 0;
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Bully Algorithm");
            System.out.println("1. Create processes");
            System.out.println("2. Display processes");
            System.out.println("3. Up a process");
            System.out.println("4. Down a process");
            System.out.println("5. Run election algorithm");
            System.out.println("6. Exit Program");
            System.out.print("Enter your choice:- ");
            choice = sc.nextInt();
            
            switch(choice) {
            case 1:
            	System.out.println("Enter number of process:");
            	max_processes = sc.nextInt();
            	bully = new Bully(max_processes);
            	break;
            case 2:
            	bully.display();
            	break;
            case 3:
            	System.out.println("Enter process:");
            	pid = sc.nextInt();
            	bully.upProcesses(pid);
            	break;
            case 4:
            	System.out.println("Enter process:");
            	pid = sc.nextInt();
            	bully.downProcesses(pid);
            	break;
            case 5:
            	System.out.println("Enter process to run election:");
            	pid = sc.nextInt();
            	bully.runElection(pid);
            	bully.display();
            	break;
            case 6:
            	System.exit(0);
            	break;
            default:
            	System.out.print("Error");
            	break;
            }
    	}
    }
}
