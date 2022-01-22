import java.rmi.*;
import java.util.HashMap;
import java.util.List;

public interface FileServerInterface extends Remote {
	
	public boolean uploadFileToServer(byte[] data, String serverPath, int length) throws RemoteException;
	public HashMap<byte[], byte[]> downloadFileFromServer() throws RemoteException;
	public List<String> listAllFiles() throws RemoteException;
}