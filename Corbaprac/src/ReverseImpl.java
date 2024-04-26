import java.lang.String;
import ReverseModule.ReversePOA;

public class ReverseImpl extends ReversePOA{
	ReverseImpl(){
		super();
		System.out.println("Reverse Obj Created");
	}
	public String reverse_string(String name) {
		
		return "Server send"+ name.toUpperCase();
	}
	
}
