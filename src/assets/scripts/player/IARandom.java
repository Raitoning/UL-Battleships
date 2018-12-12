package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import engine.Vector2;

import java.util.ArrayList;

public class IARandom extends Player implements IA{

    private ArrayList<Case> ar;

    public IARandom(int idJoueur, Game m, int gameID) {

        super(idJoueur, m, gameID);

        ar =new ArrayList<>();


        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));

        for(int i =0; i < Map.NBCASES;i++){
            for (int j =0;j < Map.NBCASES;j++){
                ar.add(model.getEpoque().getCaseAt(opponentID(),i,j));
            }
        }
        System.out.println(ar.size());
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
        int x = (int)(Math.random()*(ar.size()));
        Case res = ar.get(x);
        ar.remove(x);
        return res;
    }
}
