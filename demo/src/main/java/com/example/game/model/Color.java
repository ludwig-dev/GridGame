package com.example.game.model;

public enum Color {
    BLUE("\u001B[34m"),
    GREEN("\u001B[32m"),
    RED("\u001B[31m"),
    YELLOW("\u001B[33m"),
    PURPLE("\u001B[35m");

    private final String ansiCode;

    Color(String ansiCode) {
        this.ansiCode = ansiCode;
    }

    public String getAnsiCode() {
        return ansiCode;
    }

    // Method to reset the color to default
    public static final String RESET = "\u001B[0m";
}
