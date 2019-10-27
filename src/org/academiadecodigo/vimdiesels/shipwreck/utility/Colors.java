package org.academiadecodigo.vimdiesels.shipwreck.utility;

public enum Colors {

    BLUE("\u001B[34m"),
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    RESET("\u001B[0m");

    private String colors;


    Colors(String colors) {
        this.colors = colors;
    }

    public String getColors() {
        return colors;
    }
}
