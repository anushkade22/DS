import java.util.*;

public class Tring {
	public static void main(String args[]) {
		Scanner sc = new Scanner(System.in);
		System.out.print("Enter number of nodes:");
		int n = sc.nextInt();
		
		System.out.println("Ring formed as below:");
		for(int i=0;i<n;i++) {
			System.out.print(i+" ");
		}
		System.out.println("0");
		int ch = 0;
		do {
			System.out.print("Enter sender:");
			int s = sc.nextInt();
			System.out.print("Enter receiver:");
			int r = sc.nextInt();
			System.out.print("Enter message:");
			String msg = sc.next();
			int token = 0;
			System.out.println("Token passing from: ");
			for(int i=0;i<s;i++) {
				System.out.println(i + " -> " + (i+1));
				token = i+1;
			}
			System.out.println("Sender "+s+" sending message "+msg);
			for(int i=0;i!=r;i=(i+1)%n) {
				System.out.println("Data Forwarding from: "+i + " -> " + (i+1)%n);
				token = (i+1)%n;
			}
			System.out.println("Receiver " + r + " Received Data: "+ msg);
			System.out.println("Do you want to send more data? (1/0): ");
            ch = sc.nextInt();
		}while(ch==1);
	}
}
