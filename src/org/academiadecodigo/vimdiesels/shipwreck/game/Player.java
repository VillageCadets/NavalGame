package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerInputScanner;
import org.academiadecodigo.bootcamp.scanners.integer.IntegerRangeInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.board.Tile;

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

    public Game createGame() {
        return new Game(this);
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


    public int[] makeMove() {

        try {
            IntegerInputScanner colScanner = new IntegerRangeInputScanner(0, 9);
            IntegerInputScanner rowScanner = new IntegerRangeInputScanner(0, 9);
            colScanner.setMessage("Insert Column \n");
            rowScanner.setMessage("Insert Row \n");

            Prompt promptPosition = new Prompt(playerSocket.getInputStream(), new PrintStream(playerSocket.getOutputStream()));

            int[] move = new int[2];
            move[0] = promptPosition.getUserInput(colScanner);
            move[1] = promptPosition.getUserInput(rowScanner);
            return move;

        } catch (IOException e) {
            e.printStackTrace();
        }

        return null;
    }

    public Socket getPlayerSocket() {
        return playerSocket;
    }
}
