package org.academiadecodigo.vimdiesels.shipwreck.utility;

public class TermImages {

    public static String logo(){

        return Colors.YELLOW.getColors() + "\n" +
                " _____ _     _         _    _               _    \n" +
                "/  ___| |   (_)       | |  | |             | |   \n" +
                "\\ `--.| |__  _ _ __   | |  | |_ __ ___  ___| | __\n" +
                " `--. \\ '_ \\| | '_ \\  | |/\\| | '__/ _ \\/ __| |/ /\n" +
                "/\\__/ / | | | | |_) | \\  /\\  / | |  __/ (__|   < \n" +
                "\\____/|_| |_|_| .__/   \\/  \\/|_|  \\___|\\___|_|\\_\\\n" +
                "              | |                                \n" +
                "              |_|                                \n" +
                "\n " +
                Colors.RESET.getColors();
    }

    public static String gameOver(){

        return Colors.YELLOW.getColors() + "\n" +
                "\n" +
                "\n" +
                " _____                        _____                \n" +
                "|  __ \\                      |  _  |               \n" +
                "| |  \\/ __ _ _ __ ___   ___  | | | |_   _____ _ __ \n" +
                "| | __ / _` | '_ ` _ \\ / _ \\ | | | \\ \\ / / _ \\ '__|\n" +
                "| |_\\ \\ (_| | | | | | |  __/ \\ \\_/ /\\ V /  __/ |   \n" +
                " \\____/\\__,_|_| |_| |_|\\___|  \\___/  \\_/ \\___|_|   \n" +
                "                                                   \n" +
                "                                                   \n" +
                "\n" +
                Colors.RESET.getColors();
    }

    public static String gameWin(){

        return Colors.YELLOW.getColors() + "\n" +
                "\n" +
                "\n" +
                " _____                        _    _ _       \n" +
                "|  __ \\                      | |  | (_)      \n" +
                "| |  \\/ __ _ _ __ ___   ___  | |  | |_ _ __  \n" +
                "| | __ / _` | '_ ` _ \\ / _ \\ | |/\\| | | '_ \\ \n" +
                "| |_\\ \\ (_| | | | | | |  __/ \\  /\\  / | | | |\n" +
                " \\____/\\__,_|_| |_| |_|\\___|  \\/  \\/|_|_| |_|\n" +
                "                                             \n" +
                "                                             \n" +
                "\n " +
                Colors.YELLOW.getColors();
    }
}
