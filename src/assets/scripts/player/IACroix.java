package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;

import java.util.ArrayList;

public class IACroix extends IA{

    public IACroix(int idJoueur, Model m, int gameID) {

        super(idJoueur, m, gameID);

        priority =new ArrayList<>();

        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                if(i%2==0||j%2==0)
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

        return "IACroix";
    }

    @Override
    public Case jeuxIA() {
        int x; Case res;

        clean();

        if(!priority.isEmpty()){
            x= (int)(Math.random()*(priority.size()));
            res = priority.get(x);
            priority.remove(x);

        }else {
            x= (int)(Math.random()*(ar.size()));
            res = ar.get(x);
        }

        if (ar.contains(res))
            ar.remove(res);

        if (ar.isEmpty()){
            extreme();
        }

        if (res.toString().equals(Case.BATEAU)){
            addVoisins(res);
        }

        return res;
    }
}
