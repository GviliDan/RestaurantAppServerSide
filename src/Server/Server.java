package Server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.Executor;
import java.util.concurrent.Executors;

public class Server {
    private static boolean serverUp=true;

    public static void main(String[] args) {
        ServerSocket server = null;
        Executor executor= Executors.newFixedThreadPool(100);
        try {
            server = new ServerSocket(12345);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        while (serverUp) {
            Socket someClient = null;
            try {
                someClient = server.accept();
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
            Thread thread= new Thread(new Route(someClient));
            executor.execute(thread);
        }
    }

    public void killServer(){
        serverUp=false;
    }
}
