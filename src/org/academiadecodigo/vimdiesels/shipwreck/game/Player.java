package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;

import java.io.*;
import java.net.Socket;

public class Player {

    private String username;
    private Socket playerSocket;
    BufferedReader in = null;
    private Prompt prompt;
    private boolean inGame;
    private boolean availableToPlay;

    Player(Socket playerSocket) {

        this.inGame = false;
        this.availableToPlay = false;

        try {
            this.playerSocket = playerSocket;
            in = new BufferedReader(new InputStreamReader(playerSocket.getInputStream()));
            prompt = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void createGame(Player p1, Player p2){
        Game game = new Game(p1, p2);
    }

    Prompt getPrompt() {
        return prompt;
    }

    boolean isInGame() {
        return inGame;
    }

    boolean isAvailableToPlay() {
        return availableToPlay;
    }

    void changeAvailability() {
        availableToPlay = !availableToPlay;
    }

    public void setInGame(boolean inGame) {
        this.inGame = inGame;
    }
}
