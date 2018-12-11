package assets.scripts;

import assets.scripts.epoque.Epoque;
import assets.scripts.epoque.MoyenAge;
import assets.scripts.epoque.Renaissance;
import assets.scripts.player.Human;
import assets.scripts.player.IACroix;
import assets.scripts.player.IARandom;
import assets.scripts.player.Player;

import java.util.Arrays;

public class Game {

    private int score[];
    private Player players[];
    private Epoque epoque;
    private int playerTurn;

    /**
     * Construit une instance du jeu
     */
    public Game() {

        this.score = new int[2];
        Arrays.fill(score,0);

        this.players = new Player[2];

        this.players[0]=new Human(0);

        this.players[1]= new IARandom(1);

        playerTurn = 0;
    }

    public int getScore(int k) {
        return score[k];
    }

    public String getTypeofPlayer(int k){
        return players[k].toString();
    }

    public Epoque getEpoque() {
        return epoque;
    }

    public void setScore(int id, int score) {
        this.score[id] = score;
    }

    public void setTypeofPlayer(int k, String t){

        if ("Human".equals(t)) {

            players[k] = new Human(k);

        } else if ("IACroix".equals(t)) {

            players[k] = new IACroix(k);

        } else if ("IARandom".equals(t)) {

            players[k] = new IARandom(k);
        }
    }

    public void setEpoque(String t) {

        if(t.equals("MoyenAge")) {

            epoque = new MoyenAge(this);
        } else if(t.equals("Renaissance")) {

            epoque = new Renaissance(this);
        }
    }

    public int getPlayerTurn(){
        return playerTurn;
    }

    public Player getPlayer(int k){
        return players[k];
    }
}
