package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends Case {

    @Override
    public String nomSprite() {

        if(estToucher())
            return "Break";
        else return "Boat";
    }

    public Bateau(int x, int y, Game m) {
        super(x,y,m);
    }

    @Override
    public String toString() {
        return BATEAU;
    }
}
