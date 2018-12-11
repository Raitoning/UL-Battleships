package assets.scripts.player;

import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;
import engine.Vector2;

public class Human extends Player {


    public Human(int idJoueur) {

        super(idJoueur);

        transform.position().setX(4.5f);
        transform.position().setY(4.5f);

        camera.setMaxRenderArea(new Vector2(0.5f, 1f));
    }

    @Override
    public void play(Case c, Epoque e, int numMap) {
        System.out.println("oui");
    }

    @Override
    public String toString() {
        return "Human";
    }
}
