import java.rmi.*;
import java.util.HashMap;

public interface FileServerInterface extends Remote {

	public HashMap<byte[], byte[]> downloadFileFromServer() throws RemoteException;

}