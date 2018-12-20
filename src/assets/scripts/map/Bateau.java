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
    public void subitTir() {
        super.subitTir();
        int x,y,joueur;
        y = getPosY();
        x = getPosX();
        if(x > Map.NBCASES) {
            x -= Map.NBCASES+1;
            joueur = 1;
        }
        else joueur = 0;
        model.getEpoque().getBattleshipAt(joueur,x,y).hit();
    }

    @Override
    public String toString() {

        return BATEAU;
    }

}
