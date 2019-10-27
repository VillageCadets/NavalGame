package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.vimdiesels.shipwreck.game.Game;
import org.academiadecodigo.vimdiesels.shipwreck.game.Lobby;
import org.academiadecodigo.vimdiesels.shipwreck.game.Player;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class Server {

    private ServerSocket serverSocket;
    private ExecutorService threadPool;
    private final List<Lobby> lobbyList = Collections.synchronizedList(new ArrayList<>());
    private final List<Game> gamesList = Collections.synchronizedList(new ArrayList<>());

    private int connectionNumber;

    //1st - start the threads and call the listening method
    public void run(int port) throws IOException {

        init(port);
        threadPool = Executors.newCachedThreadPool();
        connectionNumber = 0;

        while (true) {
            listening();
        }
    }
    //2nd - start the server with a welcome message
    private void init(int port) throws IOException {

        System.out.println("====== Village Cadets Server Running ======");
        serverSocket = new ServerSocket(port);
    }
    //3rd - Server starts listening for players to join in
    private void listening() throws IOException {

        ++connectionNumber;

        Socket playerSocket = serverSocket.accept();

        Lobby lobby = new Lobby(this, playerSocket, "Player_" + connectionNumber);
        lobbyList.add(lobby);
        threadPool.submit(lobby);

    }

    public List<Lobby> getLobbyList() {
        return lobbyList;
    }

    public List<Game> getGamesList() {
        return gamesList;
    }

}
