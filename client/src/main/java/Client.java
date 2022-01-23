import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.time.Instant;

public class Client {
  public final static String DEFAULT_HOST = "localhost";
  public final static String VOLUME_DIR = "clientdata";

  public static void main(String args[]) throws Exception {
    String host = DEFAULT_HOST;
    if (args.length >= 1) {
      host = args[0];
    } else {
      System.out.println("> No host was specified. Using " + DEFAULT_HOST + " as default ...");
    }

    try {
      FileServerInterface filesStub = (FileServerInterface) Naming.lookup("rmi://" + host + "/FileServer");
      System.out.println("> Successfully connected to RMI registry: rmi://" + host + "/FileServer");

      File file = new File(String.format("/%s/%s", VOLUME_DIR, Instant.now().toEpochMilli() + ".txt"));
      FileClient.downloadFileFromServer(filesStub, file.getAbsolutePath());

      System.out.println("Use Ctrl+C to close the application ...");
      while(true);

    } catch (NotBoundException | MalformedURLException err) {
      System.out.println("> Server seems to be unavailable. NOT FOUND | rmi://" + host + "/AuthServer");
      err.printStackTrace();
    }
  }
}
