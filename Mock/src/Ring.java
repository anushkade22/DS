import java.util.*;

public class Ring {
	int max_processes;
    int coordinator;
    Boolean processes[];
    ArrayList<Integer> proc;
    
    
    public Ring(int max) {
    	max_processes = max;
    	coordinator = max;
    	processes = new Boolean[max];
    	proc = new ArrayList<Integer>();
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
    
    void displayArray(ArrayList<Integer> proc) {
    	for(Integer x : proc) {
    		System.out.print(x+" ");
    	}
    	System.out.println();
    }
    
    void runElection(int pid) {
    	if(processes[pid-1]) {
    		proc.add(pid);
    		int temp = pid;
    		System.out.println("Process P"+pid+" sending following list:");
    		displayArray(proc);
    		while(temp!=pid-1) {
    			if(processes[temp]) {
    				proc.add(temp+1);
    				System.out.println("Process P"+(temp+1)+" sending following list:");
    	    		displayArray(proc);
    			}
    			temp = (temp+1)%max_processes;
    		}
    		coordinator = Collections.max(proc);
    		System.out.println("Process P"+pid+" has declared " + coordinator + " as coordinator");
    		proc.clear();
    	}
    }
    
    public static void main(String args[]) {
    	Ring ring = null;
    	int max_processes = 0,pid=0;
    	int choice = 0;
    	Scanner sc = new Scanner(System.in);
    	while(true) {
    		System.out.println("Ring Algorithm");
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
            	ring = new Ring(max_processes);
            	break;
            case 2:
            	ring.display();
            	break;
            case 3:
            	System.out.println("Enter process:");
            	pid = sc.nextInt();
            	ring.upProcesses(pid);
            	break;
            case 4:
            	System.out.println("Enter process:");
            	pid = sc.nextInt();
            	ring.downProcesses(pid);
            	break;
            case 5:
            	System.out.println("Enter process to run election:");
            	pid = sc.nextInt();
            	ring.runElection(pid);
            	ring.display();
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
