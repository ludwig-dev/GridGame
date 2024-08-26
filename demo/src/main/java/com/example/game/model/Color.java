package com.example.game.model;

public enum Color {
    BLUE(java.awt.Color.BLUE),
    GREEN(java.awt.Color.GREEN),
    RED(java.awt.Color.RED),
    YELLOW(java.awt.Color.YELLOW),
    PURPLE(java.awt.Color.MAGENTA);

    private final java.awt.Color awtColor;

    Color(java.awt.Color awtColor) {
        this.awtColor = awtColor;
    }

    public java.awt.Color getAwtColor() {
        return awtColor;
    }
}
