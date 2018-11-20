package battleship.player;

import battleship.map.Case;
import battleship.map.Map;
import battleship.map.Position;

public class IARandom extends Player {
    private static final int MAXTRY = 50;

    public IARandom(int idJoueur) {
        super(idJoueur);
    }

    @Override
    public Position play(Map m) {
        int x = 0,y =0;
        Position p = null;
        //au cas on a pas de chance sur le random on limite le nombre d'essai a MAXTRY
        int tmp = 0;
        while((m.at(x,y) == Case.TirRate || m.at(x,y) == Case.BateauDetruit || m.at(x,y) == Case.TirSurBateau) && tmp < MAXTRY) {
            x = (int) Math.random() * (Map.NBCASES - 1);
            y = (int) Math.random() * (Map.NBCASES - 1);
            tmp++;
        }
        if(tmp >= MAXTRY){
            p = m.nextGood();
        }
        else p = new Position(x,y);
        return p;
    }
}
