package assets.scripts.map;

import assets.scripts.Game;
import engine.gameobject.component.SpriteRenderer;

public class Bateau extends Case {

    private String texture = "Water";

    public Bateau(int x, int y, Game m,String t, int id) {
        super(x,y,m,id);
        texture =t;
        spriteRenderer.setName(nomSprite());
    }

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



    @Override
    public String toString() {

        return BATEAU;
    }

}
