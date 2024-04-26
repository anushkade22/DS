import java.rmi.*;
import java.rmi.server.*;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class ServerImpl extends UnicastRemoteObject implements ServerIntf{
	LocalTime localTime;
	
	ServerImpl(LocalTime localTime) throws RemoteException{
		this.localTime = localTime;
	}
	
	public LocalTime getTime() throws RemoteException{
		return this.localTime;
	}
	
	public void adjust(LocalTime serverTime,long diff) throws RemoteException{
		long serverNano = serverTime.toNanoOfDay();
		long localNano = this.getTime().toNanoOfDay();
		long adjusted = diff - (localNano-serverNano)+localNano;
		LocalTime adjustedTime = LocalTime.ofNanoOfDay(adjusted);
		this.localTime = adjustedTime;
		System.out.println("updated time: "+ adjustedTime.format(DateTimeFormatter.ofPattern("HH:mm:ss")));
	}

}
