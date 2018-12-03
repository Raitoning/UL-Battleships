package assets.scripts.player;

import assets.scripts.map.Map;
import assets.scripts.map.Position;
import engine.Vector2;

public class IARandom extends Player {

    public IARandom(int idJoueur) {

        super(idJoueur);

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));
    }

    @Override
    public Position play(Map m) {

        return null;
    }

    @Override
    public String toString() {

        return "IARandom";
    }
}
