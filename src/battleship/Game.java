package battleship;

import battleship.epoque.Epoque;
import battleship.player.Human;
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
}
