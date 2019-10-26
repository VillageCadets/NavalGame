package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.vimdiesels.shipwreck.game.Lobby;
import org.academiadecodigo.vimdiesels.shipwreck.game.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private Player[] players;
    private Lobby lobby;

    //1st - start the threads and call the listening method
    public void run(int port) throws IOException {

        init(port);
        threadPool = Executors.newCachedThreadPool();

        lobby = new Lobby();
        threadPool.submit(lobby);

        while (true) {
            listening();
        }
    }
    //2nd - start the server with a welcome message
    private void init(int port) throws IOException {

        System.out.println("====== Village Cadets ======");
        serverSocket = new ServerSocket(port);
    }
    //3rd - Server starts listening for players to join in
    private void listening() throws IOException {

        Socket playerSocket = serverSocket.accept();
        threadPool.submit(new Player(playerSocket));
        lobby.addPlayerToLobby(playerSocket);

    }



}
