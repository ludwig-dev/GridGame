package com.example.game.model;

public class Score {
    private int score;

    public Score() {
        this.score = 0;
    }

    public int getScore() {
        return score;
    }

    public void incrementScore(int points) {
        this.score += points;
    }

    public void resetScore() {
        this.score = 0;
    }
}
