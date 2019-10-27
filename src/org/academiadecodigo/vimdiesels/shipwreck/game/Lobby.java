package org.academiadecodigo.vimdiesels.shipwreck.game;

import org.academiadecodigo.bootcamp.Prompt;
import org.academiadecodigo.bootcamp.scanners.menu.MenuInputScanner;
import org.academiadecodigo.vimdiesels.shipwreck.Server;
import org.academiadecodigo.vimdiesels.shipwreck.utility.Colors;
import org.academiadecodigo.vimdiesels.shipwreck.utility.TermImages;

import java.io.Closeable;
import java.io.IOException;
import java.io.PrintWriter;
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
    private Prompt prompt;
    MenuInputScanner mainMenu;
    MenuInputScanner subMenu;

    public Lobby(Server serverSocket, Socket playerSocket, String nickName) {
        this.player = new Player(playerSocket);
        this.lobbyList = serverSocket.getLobbyList();
        this.nickName = nickName;
        gamesList = new LinkedList<>();
        this.serverSocket = serverSocket;
        prompt = player.getPrompt();
    }

    @Override
    public void run() {
        createMenu();
    }

    private void createMenu() {

        String[] menuOptions = {"Play ", "Exit"};
        mainMenu = new MenuInputScanner(menuOptions);
        mainMenu.setMessage(TermImages.logo() + "\nSelect an Option" );
        int answerIndex = prompt.getUserInput(mainMenu);

        if (answerIndex == 1) {
            createSubMenu();

        } else {
            try {
                player.getPlayerSocket().close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

    private void createSubMenu() {

        String[] playOptions = {"Create Game", "Join Game", "Back"};
        subMenu = new MenuInputScanner(playOptions);
        subMenu.setMessage(TermImages.logo() + "\nSelect an Option" );
        int answerIndexPlay = prompt.getUserInput(subMenu);

            switch (answerIndexPlay) {
                case 1:

                    player.changeAvailability();
                    player.setInGame(true);
                    serverSocket.getGamesList().add(player.createGame());
                    break;

                case 2:

                    if (!(serverSocket.getGamesList().size() > 0)) {
                        try {
                            PrintWriter printWriter = new PrintWriter(player.getPlayerSocket().getOutputStream());
                            printWriter.print(Colors.YELLOW.getColors() +
                                    "\nNo games to join.\nTry again later or create a new one.\n" +
                                    Colors.RESET.getColors());
                            printWriter.flush();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }
                        createSubMenu();
                    }
                    Game game = getGameOnHold();
                    game.addPlayer(player);
                    game.init();
                    player.changeAvailability();
                    break;

                default:
                    createMenu();
                    break;
            }

    }

    private Game getGameOnHold() {
        for (Game g : serverSocket.getGamesList()) {

            if (g.isOnHold()) {
                return g;
            }
        }
        return null;
    }

    public String getNickName() {
        return nickName;
    }

    public Player getPlayer() {
        return player;
    }

}
