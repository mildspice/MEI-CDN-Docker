import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.time.Instant;

public class Client {
  public static void main(String args[]) throws Exception {
    String host = "localhost";
    if (args.length >= 1) {
      host = args[0];
    } else {
      System.out.println("> No host was specified. Using 'localhost' as default ...");
    }

    try {
      FileServerInterface files = (FileServerInterface) Naming.lookup("rmi://" + host + "/FileServer");
      System.out.println("> Successfully connected to RMI registry: rmi://" + host + "/FileServer");
      FileClient fc = new FileClient();
      File file = new File("/clientdata/"+Instant.now().toEpochMilli()+".txt");
      fc.downloadFileFromServer(files, file.getAbsolutePath());   
      System.out.println("Use Ctrl+C to close the application container");
      while(true);

    } catch (NotBoundException | MalformedURLException err) {
      System.out.println("> Server seems to be unavailable. NOT FOUND | rmi://" + host + "/AuthServer");
      err.printStackTrace();
    }
  }
}
