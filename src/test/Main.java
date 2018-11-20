package test;

import battleship.Game;
import battleship.data.GameSaverFactory;
import battleship.epoque.MoyenAge;

public class Main {

    public static void main (String[] args){

        Game g = new Game(new MoyenAge());

        GameSaverFactory.getInstance().save(g);

    }
}
