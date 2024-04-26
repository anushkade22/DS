import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import java.io.*;

public class ReverseClient {
	public static void main(String args[]) {
		Reverse ReverseImpl = null;
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
			org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
			NamingContextExt ncref = NamingContextExtHelper.narrow(objref);
			String name = "Reverse";
			ReverseImpl = ReverseHelper.narrow(ncref.resolve_str(name));
			System.out.println("Enter string: ");
			BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
			String str= br.readLine();
			String temp = ReverseImpl.reverse_string(str);
			System.out.println(temp);
		}catch(Exception e) {
			System.out.println("Exception" + e);
		}
	}
}
