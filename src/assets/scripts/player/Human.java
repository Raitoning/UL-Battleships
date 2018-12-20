package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import engine.Vector2;

public class Human extends Player {

    public static final String name = "Humain";


    public Human(int idJoueur, Model m, int gameID) {

        super(idJoueur,m, gameID);

        transform.position().setX(4.5f);
        transform.position().setY(4.5f);

        camera.setMaxRenderArea(new Vector2(0.5f, 1f));
    }

    @Override
    public void play(Case c) {
        super.play(c);
    }

    @Override
    public String toString() {
        return name;
    }
}
