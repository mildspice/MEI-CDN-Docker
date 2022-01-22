import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.rmi.RemoteException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;

public class FileClient {

    protected FileClient() throws RemoteException {
        super();
    }

    public File downloadFileFromServer(FileServerInterface fServer, String clientFileName)
            throws RemoteException {
        try {
            HashMap<byte[], byte[]> bytes =  fServer.downloadFileFromServer();
            System.out.print("Data received from server, proceeding to verify");
            byte[] checksum = (byte[]) bytes.keySet().toArray()[0];
            byte[] data = bytes.get(checksum);
            MessageDigest md = MessageDigest.getInstance("MD5");
            byte[] checksumLocal = md.digest(data);
            if(!MessageDigest.isEqual(checksum, checksumLocal)){
                System.out.println("NOT SAME! CHECKSUM FAILED ばか!");
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

    public boolean uploadFileToServer(FileServerInterface fServer, String serverPath, File clientpathfile)
            throws RemoteException {
        serverPath = fileNameValidator(serverPath, clientpathfile.getName());
        byte[] data = new byte[(int) clientpathfile.length()];
        try {
            FileInputStream in = new FileInputStream(clientpathfile);
            in.read(data, 0, data.length);
            fServer.uploadFileToServer(data, serverPath, (int) clientpathfile.length());
            in.close();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            return false;
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public boolean uploadFileToServer(FileServerInterface fServer, String serverPath, String clientPath)
            throws RemoteException {
        File clientpathfile = new File(clientPath);
        return uploadFileToServer(fServer, serverPath, clientpathfile);
    }

    public List<String> listAllFiles(FileServerInterface fServer) throws RemoteException{
        return fServer.listAllFiles();
    }

    private String fileNameValidator(String serverfPath, String fileName) {
        if (serverfPath.isBlank() || serverfPath.isEmpty()) {
            return fileName;
        } else {
            return serverfPath;
        }
    }

}
