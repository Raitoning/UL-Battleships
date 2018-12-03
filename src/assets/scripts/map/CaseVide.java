package assets.scripts.map;

import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

public class CaseVide extends GameObject implements Case {

    private SpriteRenderer spriteRenderer;

    public CaseVide() {}

    public CaseVide (int x, int y) {

        transform.position().setX(x);
        transform.position().setY(y);
        transform.position().setZ(1f);

        spriteRenderer = new SpriteRenderer("Water", this);
    }

    public String toString(){
        return VIDE;
    }
}
