package assets.scripts.player;

import assets.scripts.Model;
import engine.Vector2;

public class Human extends Player {

    public static final String name = "Humain";


    public Human(int idJoueur, Model model, int gameID) {

        super(idJoueur, model, gameID);

        transform.position().setX(4.5f);
        transform.position().setY(4.5f);

        camera.setMaxRenderArea(new Vector2(0.5f, 1f));
    }

    @Override
    public String toString() {
        return name;
    }
}
