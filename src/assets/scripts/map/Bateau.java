package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends Case {

    @Override
    public String nomSprite() {
        return "Boat";
    }

    public Bateau(){
        super();
    }

    public Bateau(int x, int y, int idJoueur, Game m) {

        transform.setPosition(x, y);

        if (idJoueur == 0) {

            spriteRenderer = new SpriteRenderer("Boat", this);
            addComponent(spriteRenderer);
        }
    }

    @Override
    public String toString() {
        return BATEAU;
    }
}
