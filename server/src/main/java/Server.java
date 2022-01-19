import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.server.UnicastRemoteObject;

public class Server extends UnicastRemoteObject {

  public Server() throws RemoteException {
    super();
  }

  private static void startRmiRegistry(int port) {
    try {
      System.out.println("> Loading RMI registry ...");
			LocateRegistry.createRegistry(1099);
			System.out.println("> RMI Server ready! | listening on port " + port);
		} catch(RemoteException e) {
      System.err.println("> Error loading RMI registry!\n---\n" + e);
		}
  }

  public static void main (String args[]) throws Exception {
    String host = "localhost", port = "1099";
		if (args.length == 2) {
			host = args[0];
      try {
        Integer.parseInt(port);
        port = args[1];
      } catch (NumberFormatException ignored) {
        System.out.println("> Invalid port. Using " + host + ":1099 as default ...");
      }
		} else if (args.length == 1) {
      host = args[0];
			System.out.println("> No port specified. Using " + host + ":1099 as default ...");
		} else {
			System.out.println("> No host or port specified. Using 'localhost:1099' as default ...");
		}

    // # not required in Java 14
    // System.setSecurityManager(new RMISecurityManager());
    startRmiRegistry(Integer.parseInt(port)); 

    FileManagement fileManagement = new FileManagement();
    FileServer fileServer = new FileServer(fileManagement);
    Naming.bind("rmi://" + host + "/FileServer", fileServer);
    System.out.println("> Successfully registered RMI interface - rmi://" + host + "/FileServer");
  }
}
