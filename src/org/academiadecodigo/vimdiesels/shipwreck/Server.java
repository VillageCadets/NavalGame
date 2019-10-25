package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService threadPool;

    //1st - start the threads and call the listening method
    public void run(int port) throws IOException {

        init(port);
        threadPool = Executors.newCachedThreadPool();

        while (true) {
            listening();
        }
    }
    //2nd - start the server with a welcome message
    public void init(int port) throws IOException {

        System.out.println("====== Village Cadets ======");
        serverSocket = new ServerSocket(port);
    }
    //3rd - Server starts listening for players to join in
    public void listening() throws IOException {

        Socket playerSocket = serverSocket.accept();
        System.out.println(Thread.activeCount() + "\n");
        threadPool.submit(new Player(this, playerSocket));
    }
}
