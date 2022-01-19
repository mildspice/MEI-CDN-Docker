import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;

public class Client {

    public static void main (String args[]) throws Exception {
      String host = "localhost";
      if (args.length >= 1) {
        host = args[0];
      } else {
        System.out.println("> No host was specified. Using 'localhost' as default ...");
      }

      try {
        FileServerInterface files = (FileServerInterface) Naming.lookup("rmi://" + host + "/FileServer");
        System.out.println("> Successfully connected to RMI registry: rmi://" + host + "/FileServer");

      } catch (NotBoundException | MalformedURLException err) {
        System.out.println("> Server seems to be unavailable. NOT FOUND | rmi://" + host + "/AuthServer");
        err.printStackTrace();
      }
    }
}
