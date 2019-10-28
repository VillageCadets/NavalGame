package org.academiadecodigo.vimdiesels.shipwreck;

import org.academiadecodigo.vimdiesels.shipwreck.game.Game;
import org.academiadecodigo.vimdiesels.shipwreck.game.Lobby;

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

    public void run(int port) throws IOException {

        init(port);
        threadPool = Executors.newCachedThreadPool();
        connectionNumber = 0;

        while (true) {
            listening();
        }
    }

    private void init(int port) throws IOException {

        System.out.println("====== Village Cadets Server Running ======");
        serverSocket = new ServerSocket(port);
    }

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
