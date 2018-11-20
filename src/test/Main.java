package test;

import battleship.Game;
import battleship.data.GameSaverFactory;
import battleship.epoque.MoyenAge;

public class Main {

    public static void main (String[] args){

        Game g = new Game(new MoyenAge());

        GameSaverFactory.getInstance().load(g);

        System.out.println(g.getEpoque().toString());

        System.out.println("score "+g.getScore(0)+" "+g.getScore(1));
        System.out.println("type "+g.getTypeofPlayer(0)+" "+g.getTypeofPlayer(1));
    }
}
