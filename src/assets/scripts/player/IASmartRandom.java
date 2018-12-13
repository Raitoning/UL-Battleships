package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;

import java.util.ArrayList;

public class IASmartRandom extends IA{

    public IASmartRandom(int idJoueur, Model m, int gameID) {

        super(idJoueur, m, gameID);

        priority =new ArrayList<>();

        for(int i = 0; i < Map.NBCASES; i++){
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

        return "IARandom";
    }

    @Override
    public Case jeuxIA() {

        int x ; Case res;

        clean();

        if(!priority.isEmpty()){
            x= (int)(Math.random()*(priority.size()));
            res = priority.get(x);
            priority.remove(x);

        }else {
            x= (int)(Math.random()*(ar.size()));
            res = ar.get(x);
        }

        ar.remove(x);

        if (ar.isEmpty()){
            extreme();
        }

        if (res.toString().equals(Case.BATEAU)){
            addVoisins(res);
        }

        return res;
    }

}
