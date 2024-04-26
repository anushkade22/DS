import java.rmi.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class Clock {
	public static void main(String args[]) {
		try {
			ServerImpl serverImpl = new ServerImpl(LocalTime.parse(args[1],DateTimeFormatter.ofPattern("HH:mm:ss")));
			Naming.rebind(args[0], serverImpl);
		}catch(Exception e) {
			System.out.println("Exception"+args[0]+e);
		}
	}
}
