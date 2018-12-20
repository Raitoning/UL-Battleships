package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;


public class IARandom extends IA{

    public static final String NAME = "IA Aléatoire";

    public IARandom(int idJoueur, Model m, int gameID) {

        super(idJoueur, m, gameID);

        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                ar.add(model.getEpoque().getCaseAt(opponentID(),i,j));
            }
        }

    }

    @Override
    public void play(Case c) {
        super.play(c);
    }

    @Override
    public String toString() {

        return NAME;
    }

    @Override
    public Case jeuxIA() {
        int x = (int)(Math.random()*(ar.size()));
        Case res = ar.get(x);
        ar.remove(x);
        return res;
    }

}
