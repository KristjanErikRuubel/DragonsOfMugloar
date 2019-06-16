package com.company;

public class Response {

    public boolean success;
    public Integer lives;
    public Integer gold;
    public Integer score;
    public Integer highScore;
    public Integer turn;
    public String message;

    public Response(boolean success, Integer lives, Integer gold, Integer score, Integer highScore, Integer turn, String message){
        this.success = success;
        this.lives = lives;
        this.gold = gold;
        this.score = score;
        this.highScore = highScore;
        this.turn = turn;
        this.message = message;

    }


}
