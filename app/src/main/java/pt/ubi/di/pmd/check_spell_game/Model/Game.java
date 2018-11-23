package pt.ubi.di.pmd.check_spell_game.Model;

import android.util.Log;

import pt.ubi.di.pmd.check_spell_game.DataProvider.PlayerProvider;
import pt.ubi.di.pmd.check_spell_game.DataProvider.WordProvider;

public class Game {

    private Player currentPlayer;
    private SingleRound currentRound;
    private int points;
    private WordProvider wordProvider;


    public Player getCurrentPlayer() {
        return currentPlayer;
    }

    public void setCurrentPlayer(Player currentPlayer) {
        this.currentPlayer = currentPlayer;
    }

    public SingleRound getCurrentRoud() {
        return currentRound;
    }

    public void setCurrentRound(SingleRound currentRound) {
        this.currentRound = currentRound;
    }

    public int getPoints() {
        return points;
    }

    public void setPoints(int points) {
        this.points = points;
    }

    public int getLevel() {
        return level;
    }

    public void setLevel(int level) {
        this.level = level;
    }

    private int level;

public Game(){
    this.currentPlayer=PlayerProvider.readJson();
    points=0;
    level=0;
    this.wordProvider=new WordProvider();
}


public void loadRound(){
    currentRound=new SingleRound(this.wordProvider);
}

public void checkRound(String answer){

    currentRound.setPlayerAnswer(answer);
    currentRound.incrementTryNumber();

    if(currentRound.isCompleted()){
        Log.d("WYNIK", "completed");
        points+=100/currentRound.getTryNumber();
        if(points%1000==0)
        {
            level++;
        }
    }


}

}