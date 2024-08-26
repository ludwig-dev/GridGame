package com.example.game.model;

public class Cell {
    private int number;
    private Color color;

    public Cell(int number, Color color) {
        this.number = number;
        this.color = color;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public Color getColor() {
        return color;
    }

    public void setColor(Color color) {
        this.color = color;
    }

    public String getColoredNumber() {
        return color.getAnsiCode() + number + Color.RESET;
    }

    @Override
    public String toString() {
        return getColoredNumber();
    }
}
