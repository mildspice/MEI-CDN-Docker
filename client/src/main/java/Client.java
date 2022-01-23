import java.io.File;
import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.NotBoundException;
import java.rmi.RemoteException;
import java.time.Instant;
import java.util.Scanner;

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

      System.out.println("Executing automated transfer from server ...");
      FileRequestFromServer(filesStub);

      Scanner inputScanner = new Scanner(System.in);
      RequestPrints();

      while (inputScanner.hasNextLine()) {
        String userInput = inputScanner.nextLine();

        if (userInput.isEmpty() || userInput.isBlank()) {
          System.out.println("Closing the application, see you next time ...");
          inputScanner.close();
          break;
        } 

        FileRequestFromServer(filesStub);
        RequestPrints();
      }

    } catch (NotBoundException | MalformedURLException err) {
      System.out.println("> Server seems to be unavailable ...");
      err.printStackTrace();
    }
  }

  private static void FileRequestFromServer(FileServerInterface filesStub) throws RemoteException {
    File file = new File(String.format("/%s/%s", VOLUME_DIR, Instant.now().toEpochMilli() + ".txt"));
    FileClient.downloadFileFromServer(filesStub, file.getAbsolutePath());
  }

  private static void RequestPrints(){
    System.out.println("\nWrite anything to request a new file from server ...");
    System.out.println("Use Ctrl+C or Press Enter to close the application ... \n");
  }

}
