package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.GameObject;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends Case {

    private String texture = "Water";

    @Override
    public String nomSprite() {
        String res = texture;
        if(getPosX() > 10){
            res = "Water";
            if(estToucher()){
                res = texture;
            }
        }

        return res;
    }

    public Bateau(int x, int y, Game m,String t) {
        super(x,y,m);
        texture =t;
        spriteRenderer.setName(nomSprite());
    }

    @Override
    public String toString() {
        return BATEAU;
    }

}
