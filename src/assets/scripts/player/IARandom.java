package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import engine.Vector2;

public class IARandom extends Player implements IA{

    public IARandom(int idJoueur, Game m) {

        super(idJoueur, m);

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));
    }

    @Override
    public void play(Case c) {
        super.play(c);
        //TODO
    }

    @Override
    public String toString() {

        return "IARandom";
    }

    @Override
    public Case jeuxIA() {

        int x,y;
        x = (int)(Math.random()*(Map.NBCASES));
        y = (int)(Math.random()*(Map.NBCASES));

        return model.getEpoque().getCaseAt(super.opponentID(),x,y);
    }
}
