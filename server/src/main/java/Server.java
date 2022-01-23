import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;

public class Server {
  public final static String DEFAULT_HOST = "localhost";
  public final static Integer DEFAULT_PORT = 1099;
  public final static String VOLUME_DIR = "serverdata";

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
    String host = DEFAULT_HOST;
    Integer port = DEFAULT_PORT;
		if (args.length == 2) {
			host = args[0];
      try {
        port = Integer.parseInt(args[1]);
      } catch (NumberFormatException ignored) {
        System.out.println("> Invalid port. Using '" + host + ":" + DEFAULT_PORT + "' as default ...");
      }
		} else if (args.length == 1) {
      host = args[0];
			System.out.println("> No port specified. Using '" + host + ":" + DEFAULT_PORT + "' as default ...");
		} else {
			System.out.println("> No host or port specified. Using '" + DEFAULT_HOST + ":" + DEFAULT_PORT + "' as default ...");
		}

    // # not required in Java 14
    // System.setSecurityManager(new RMISecurityManager());
    startRmiRegistry(port); 

    FileServer fileServer = new FileServer();
    Naming.bind("rmi://" + host + "/FileServer", fileServer);
    System.out.println("> Successfully registered RMI interface - rmi://" + host + "/FileServer");
  }
}
