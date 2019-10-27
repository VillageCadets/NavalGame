package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;
import org.academiadecodigo.vimdiesels.shipwreck.utility.TermImages;

import java.net.Socket;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.function.ToDoubleBiFunction;

public class Lobby implements Runnable {

    private Player player;
    private List<Lobby> lobbyList;
    private String nickName;
    private List<Game> gamesList;
    private Server serverSocket;

    public Lobby(Server serverSocket, Socket playerSocket, String nickName) {
        this.player = new Player(playerSocket);
        this.lobbyList = serverSocket.getLobbyList();
        this.nickName = nickName;
        gamesList = new LinkedList<>();
        this.serverSocket = serverSocket;
    }

    @Override
    public void run() {
        createMenu();
    }

    private void createMenu() {

        while (!player.isInGame()) {

            String[] menuOptions = {"Play ", "Exit"};
            Prompt prompt = player.getPrompt();
            MenuInputScanner opt = new MenuInputScanner(menuOptions);

            opt.setMessage(TermImages.logo());

            int answerIndex = prompt.getUserInput(opt);

            if (answerIndex == 1) {

                String[] playOptions = {"Create Game", "Join Game", "Back"};
                Prompt promptGame = player.getPrompt();
                MenuInputScanner gameOpt = new MenuInputScanner(playOptions);

                int answerIndexPlay = promptGame.getUserInput(gameOpt);

                switch (answerIndexPlay) {
                    case 1:
                        player.changeAvailability();
                        player.setInGame(true);
                        serverSocket.getGamesList().add(player.createGame());
                        break;

                    case 2:
                        System.out.println("JOINING A GAME");
                        Game game = getGameOnHold();
                        game.addPlayer(player);
                        game.init();
                        player.changeAvailability();
                        // TODO: 26/10/2019 Talvez um sync aqui faça sentido...

                        break;

                    default:
                        // TODO: 26/10/2019 go to previous menu
                        break;

                }

            }
        }
    }

    private Game getGameOnHold() {
        System.out.println(serverSocket.getGamesList().size());

        for (Game g : serverSocket.getGamesList()) {

            if (g.isOnHold()) {
            return g;
            }
        }
        // TODO: 27/10/2019 tell the player to create a new game because theres no games available.
        return null;
    }

    public String getNickName() {
        return nickName;
    }

    public Player getPlayer() {
        return player;
    }
}
