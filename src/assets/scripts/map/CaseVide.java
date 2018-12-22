package assets.scripts.map;

import assets.scripts.Model;

public class CaseVide extends Case {


    public CaseVide(Position position, int x, int y, Model model, int gameID) {

        super(position, x, y, model, gameID);
        transform.position().setZ(2f);
    }

    public String toString() {

        return VIDE;
    }

    @Override
    public String nomSprite() {

        String res = getSpriteFolder();
        if (estToucher()) {

            res += "Miss";
        } else {

            res += "Water";
        }
        return res;
    }
}
