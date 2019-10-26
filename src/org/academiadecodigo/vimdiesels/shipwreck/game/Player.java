package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;

import java.io.*;
import java.net.Socket;

public class Player implements Runnable {

    private String username;
    private Socket playerSocket;
    private Prompt prompt;
    private boolean inGame;
    private boolean availableToPlay;

    public Player(Socket playerSocket) {

        this.inGame = false;
        this.availableToPlay = false;

        try {
            this.playerSocket = playerSocket;
            prompt = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void run() {

    }

    public Prompt getPrompt() {
        return prompt;
    }

    public boolean isInGame() {
        return inGame;
    }

    public boolean isAvailableToPlay() {
        return availableToPlay;
    }

    public void changeAvailability() {
        availableToPlay = !availableToPlay;
    }


}
