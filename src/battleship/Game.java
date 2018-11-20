package battleship;

import battleship.epoque.Epoque;
import battleship.epoque.MoyenAge;
import battleship.epoque.Renaissance;
import battleship.player.Human;
import battleship.player.IACroix;
import battleship.player.IARandom;
import battleship.player.Player;

import java.lang.reflect.Array;
import java.util.Arrays;

public class Game {

    int score[];

    Player players[];

    Epoque epoque;

    /**
     * Construit une instance du jeu à partir d'une Epoque donnée en parametre
     * @param epoque
     */
    public Game(Epoque epoque) {
        this.score = new int[2];
        Arrays.fill(score,0);

        this.players = new Player[2];

        this.players[0]=new Human(0);

        this.players[1]= new IARandom(1);

        this.epoque = epoque;

        System.out.println(epoque.toString());

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

        if(t.equals("Human")){
            players[k]=new Human(k);
        }else if(t.equals("IACroix")){
            players[k]=new IACroix(k);
        }else if(t.equals("IARandom")){
            players[k]=new IARandom(k);
        }
    }

    public void setEpoque(String t){

        if(t.equals("MoyenAge")){
            epoque = new MoyenAge();
        }else if(t.equals("Renaissance")){
            epoque = new Renaissance();
        }
    }




}
