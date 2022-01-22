import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.time.Instant;
import java.util.HashMap;
import java.util.List;
import java.util.Random;

public class FileServer extends UnicastRemoteObject implements FileServerInterface {

    private FileManagement fileManager;

    protected FileServer(FileManagement fileManagement) throws RemoteException {
        super();
        this.fileManager = fileManagement;
    }

    public boolean uploadFileToServer(byte[] data, String serverPath, int length) throws RemoteException {
        try {
            System.out.println("> Saving File: " + serverPath);
            File serverPathFile = new File(serverPath);
            FileOutputStream out = new FileOutputStream(serverPathFile);
            byte[] bdata = data;
            out.write(bdata);
            out.flush();
            out.close();
            fileManager.addFile(serverPath);
        } catch (IOException e) {
            e.printStackTrace();
            return false;
        }
        return true;
    }

    public HashMap<byte[], byte[]> downloadFileFromServer() throws RemoteException {
            return generateFile();
    }

    private HashMap<byte[], byte[]> generateFile(){
        File file = new File("/serverdata/"+Instant.now().toEpochMilli()+".txt");
        RandomAccessFile raf;
        HashMap<byte[], byte[]> bytes = new HashMap<>();
        byte[] theMD5digest = null;
        try {
            raf = new RandomAccessFile(file, "rw");
            raf.setLength(1024);
            System.out.println("Creating file with name "+file.getName()+" and size of "+file.length() +" bytes");
            byte[] array = new byte[1024];
            new Random().nextBytes(array);
            System.out.println("Generating random stream of data");
            String generatedString = new String(array);
            byte[] bytesOfMessage = generatedString.getBytes();
            MessageDigest md = MessageDigest.getInstance("MD5");
            System.out.println("Generating checksum");
            theMD5digest = md.digest(bytesOfMessage);
            bytes.put(theMD5digest, bytesOfMessage);
            raf.writeBytes(generatedString);
            System.out.println("Writing to file");
            raf.close();
            System.out.println("Closing writing to file");
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            System.out.print("Error! File Creation filed");
        } catch (IOException e) {
            e.printStackTrace();
            System.out.print("Error! Problem with Data stream");
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
            System.out.print("Error! Problem with Checksum");
        }
        return bytes;
    }

    @Override
    public List<String> listAllFiles() throws RemoteException {
        return fileManager.listFiles();
    }

}
