package assets.scripts.epoque;

import assets.scripts.map.Map;
import assets.scripts.map.Position;

import java.util.ArrayList;
import java.util.Random;

public class MoyenAge extends Epoque {

    public MoyenAge() {

        super(true);
    }

    public MoyenAge(boolean init) {

        super(init);
    }

    @Override
    protected void battleshipInit(int idPlayer) {

        Random r = new Random();
        battleships[idPlayer]= new ArrayList<>(4);

        int x= r.nextInt(Map.NBCASES-1);
        int y= r.nextInt(Map.NBCASES-1);
        Position p = new Position(x,y);
        boolean v =r.nextBoolean();

        for (int i =2; i<=5; i++) {

            while (isThereAShipOnTheWay(idPlayer, x, y, i, v)) {
                x = r.nextInt(Map.NBCASES-1);
                y = r.nextInt(Map.NBCASES-1);
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
