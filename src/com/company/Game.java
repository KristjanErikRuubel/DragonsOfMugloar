package com.company;

public class Game {

    public String gameId;
    public Integer lives;
    public Integer level;
    public Integer score;
    public Integer highScore;
    public Integer turn;

    public Game(String gameId, Integer lives, Integer level, Integer score, Integer turn, Integer highScore){
        this.gameId = gameId;
        this.lives = lives;
        this.level = level;
        this.score = score;
        this.turn = turn;
        this.highScore = highScore;
    }
}
