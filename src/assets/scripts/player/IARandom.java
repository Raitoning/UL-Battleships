package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;
import engine.Vector2;

public class IARandom extends Player {

    public IARandom(int idJoueur, Game m) {

        super(idJoueur, m);

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));
    }

    @Override
    public void play(Case c, Epoque e, int numMap) {
    }

    @Override
    public String toString() {

        return "IARandom";
    }
}
