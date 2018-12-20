package assets.scripts.map;

import assets.scripts.Model;

public class Bateau extends Case {

    private String texture = "Water";

    public Bateau(int x, int y, Model m, String t, int id) {
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

        return getSpriteFolder() + res;
    }



    @Override
    public String toString() {

        return BATEAU;
    }

}
