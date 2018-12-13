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
        if(estToucher()&& getPosX() < Map.NBCASES)
            res +="Feu";
        if(getPosX() > Map.NBCASES){
            res = "Water";
            if(estToucher()){
                res = "Exploded";
            }
        }

        return res;
    }



    @Override
    public String toString() {

        return BATEAU;
    }

}
