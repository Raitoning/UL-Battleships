package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends Case {

    @Override
    public String nomSprite() {

        if(estToucher()) {

            return "Break";
        }
        else if(posX > 10) { //la posX correspont forcement a la map de droite

            return "Water";
        } else {

            return "Boat";
        }
    }

    public Bateau(int x, int y, Game m, int gameID) {

        super(x,y,m, gameID);
    }

    @Override
    public String toString() {

        return BATEAU;
    }
}
