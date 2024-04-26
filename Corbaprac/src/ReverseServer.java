import ReverseModule.*;
import org.omg.CosNaming.*;
import org.omg.CosNaming.NamingContextPackage.*;
import org.omg.CORBA.*;
import org.omg.PortableServer.*;

public class ReverseServer {
	public static void main(String args[]) {
		try {
			org.omg.CORBA.ORB orb = org.omg.CORBA.ORB.init(args,null);
			POA rootPOA = POAHelper.narrow(orb.resolve_initial_references("RootPOA"));
			rootPOA.the_POAManager().activate();
			ReverseImpl rvr = new ReverseImpl();
			org.omg.CORBA.Object ref = rootPOA.servant_to_reference(rvr);
			System.out.println("Step1");
			Reverse href = ReverseModule.ReverseHelper.narrow(ref);
			System.out.println("Step2");
			org.omg.CORBA.Object objref = orb.resolve_initial_references("NameService");
			System.out.println("Step3");
			NamingContextExt ncref = NamingContextExtHelper.narrow(objref);
			System.out.println("Step4");
			String name = "Reverse";
			NameComponent path[] = ncref.to_name(name);
			ncref.rebind(path,href);
			System.out.println("Reverse server reading and waiting");
			orb.run();
		}catch(Exception e) {
			System.out.println("Exception" + e);
		}
	}
}

