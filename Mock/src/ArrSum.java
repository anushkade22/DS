import mpi.*;
import java.util.Scanner;

public class ArrSum {
	public static void main(String args[]) {
		MPI.init(args);
		int rank = MPI.COMM_WORLD.Rank();
		int size = MPI.COMM_WORLD.Size();
		int unitsize = 5;
		int root=0;
		int send_buffer[]=null;
		send_buffer = new int[unitsize*size];
		int receive_buffer[] = new int[unitsize];
		int new_receive_buffer[] = new int[size];
		
		if(root==rank) {
			int total_ele = unitsize*size;
			System.out.println("Total elements: "+total_ele);
			for(int i=0;i<total_ele;i++) {
				System.out.println("Element: "+i +"is"+i);
				send_buffer[i]=i;
			}
		}
		MPI.COMM_WORLD.Scatter(
				send_buffer,
				0,
				unitsize,
				MPI.INT,
				receive_buffer,
				0,
				unitsize,
				MPI.INT,
				root);
		for(int i=1;i<unitsize;i++) {
			receive_buffer[0]+=receive_buffer[i];
		}
		System.out.println("Intermediary sum at Process"+rank +"is"+receive_buffer[0]);
		MPI.COMM_WORLD.Gather(
				receive_buffer,
				0,
				1,
				MPI.INT,
				new_receive_buffer,
				0,
				1,
				MPI.INT,
				root);
		if(root==rank) {
			int sum=0;
			for(int i=0;i<size;i++) {
				sum+=new_receive_buffer[i];
			}
			System.out.println("Final sum: "+sum);
		}
		MPI.finalize();
		
	}
}

//export MPJ_HOME = path
//export PATH = $MPJ_HOME/bin:$PATH
//javac -cp $MPJ_HOME/lib/mpj.jar ArrSum
//$MPJ_HOME/bin/mpjrun.sh -np 4 ArrSumm
