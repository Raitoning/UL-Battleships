package assets.scripts.map;

import assets.scripts.Model;

public class Bateau extends Case {

    private String spriteName;

    public Bateau(Position position, int x, int y, Model model, String spriteName, int gameID) {

        super(position, x, y, model, gameID);
        this.spriteName = spriteName;
        spriteRenderer.setName(nomSprite());
    }

    @Override
    public String nomSprite() {

        String res = spriteName;
        if (estToucher() && getPosX() < Map.NBCASES) {
            res += "Feu";
        }
        if (getPosX() > Map.NBCASES) {
            res = "Water";
            if (estToucher()) {
                res = "Exploded";
            }
        }

        return getSpriteFolder() + res;
    }

    @Override
    public void subitTir() {

        super.subitTir();

        int x;
        int y;
        int joueur;

        y = getPosY();
        x = getPosX();

        if (x > Map.NBCASES) {
            x -= Map.NBCASES + 1;
            joueur = 1;
        } else {
            joueur = 0;
        }
        model.getEpoque().getBattleshipAt(joueur, x, y).hit();
    }

    @Override
    public String toString() {

        return BATEAU;
    }

}
