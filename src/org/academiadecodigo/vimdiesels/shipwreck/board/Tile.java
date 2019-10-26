package org.academiadecodigo.vimdiesels.shipwreck.board;

public class Tile {

    private int x;
    private int y;
    private TileType type;
    String representation;

    public Tile(int x, int y, TileType type) {
        this.x = x;
        this.y = y;
        this.type = type;
    }

    public void draw() {

        switch (this.type) {
            case HIT:
                representation = "\u001B[31m"+" [X]"+ "\u001B[31m";
                break;
            case SEA:
                representation = "\u001B[36m"+" [~]" + "\u001B[36m";
                break;
            case MISS:
                representation = "\u001B[31m"+" [O]"+ "\u001B[31m";
                break;
            case SHIP:
                representation = "\u001B[31m"+" [S]"+ "\u001B[31m";
            default:
                break;
        }

        System.out.print(toString());
    }

    @Override
    public String toString() {
        return representation ;
    }


    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public void setType(TileType type) {
        this.type = type;
    }

    public TileType getType() {
        return type;
    }
}
