package assets.scripts.map;

import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends GameObject implements Case {

    private SpriteRenderer spriteRenderer;

    public Bateau() {}

    public Bateau(int x, int y, int idJoueur) {

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
