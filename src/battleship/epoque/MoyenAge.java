package battleship.epoque;

import battleship.map.Map;
import battleship.map.Position;

import java.util.ArrayList;
import java.util.Random;

public class MoyenAge extends Epoque {

    public MoyenAge() {
        super();

        maps[0] = new Map();
        maps[1] = new Map();

        battleshipInit(0);
        battleshipInit(1);

    }

    @Override
    protected void battleshipInit(int idPlayer) {

        Random r = new Random();
        battleships[idPlayer]= new ArrayList<>(4);

        int x= r.nextInt(Map.NBCASES);
        int y= r.nextInt(Map.NBCASES);
        Position p = new Position(x,y);
        boolean v =r.nextBoolean();

        for (int i =2; i<=5; i++) {

            while (isThereAShipOnTheWay(idPlayer, x, y, i, v)) {
                x = r.nextInt(Map.NBCASES);
                y = r.nextInt(Map.NBCASES);
                v = r.nextBoolean();
                p = new Position(x,y);
                p = repositionIfOutOfBounds(p,v,i);
            }

            getBattleships(idPlayer).add(
                    new Battleship(p, i/2, i, v,maps[idPlayer]));

        }

    }

    @Override
    public String getCheminTexture() {
        return null;
    }

    @Override
    public String name() {
        return "MoyenAge";
    }
}
