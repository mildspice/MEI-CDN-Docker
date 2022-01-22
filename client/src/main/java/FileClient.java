import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class FileClient {

    protected FileClient() throws RemoteException {
        super();
    }

    public File downloadFileFromServer(FileServerInterface fServer, String clientFileName)
            throws RemoteException {
        try {
            HashMap<byte[], byte[]> bytes =  fServer.downloadFileFromServer();
            System.out.println("Data received from server, proceeding to verify with Checksum");
            byte[] checksum = (byte[]) bytes.keySet().toArray()[0];
            byte[] data = bytes.get(checksum);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] checksumLocal = md.digest(data);

            if(!MessageDigest.isEqual(checksum, checksumLocal)){
                System.out.println("NOT SAME! CHECKSUM FAILED ばか! \nAborting download of file");
                return null;
            }

            System.out.println("Checksum validated, data is intact");

            File clientpathfile = new File(clientFileName);
            FileOutputStream out;
            out = new FileOutputStream(clientpathfile);
            out.write(data);
            out.flush();
            out.close();

            System.out.println("Data writen to file "+ clientpathfile.getAbsolutePath());

            return clientpathfile;
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.println("Error with file");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.println("Error with bytes");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.println("Error with Checksum");
        }
        return null;
    }

}
