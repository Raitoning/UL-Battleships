package assets.scripts.player;

import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.Vector2;

public class Human extends Player {


    public Human(int idJoueur) {

        super(idJoueur);

        transform.position().setX(4.5f);
        transform.position().setY(4.5f);

        camera.setMaxRenderArea(new Vector2(0.5f, 1f));
    }

    @Override
    public Position play(Map m) {
        //Todo: to be Written Graphically
        return null;
    }

    @Override
    public String toString() {
        return "Human";
    }
}
