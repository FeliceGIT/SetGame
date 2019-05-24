package com.example.SetGame;

public class Cards {

    private int number;
    private int symbol;
    private int shading;
    private int color;
    private int picture;

    public Cards(int nu, int sh, int co, int sy, int pic) {

        number = nu;
        shading = sh;
        color = co;
        symbol = sy;
        picture = pic;
    }

    public int getNumber() {
        return number;
    }

    public int getSymbol() {
        return symbol;
    }

    public int getShading() {
        return shading;
    }

    public int getColor() {
        return color;
    }

    public int getPicture() {
        return picture;
    }
}