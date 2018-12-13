package assets.scripts.map;

import assets.scripts.Model;

public class CaseVide extends Case {


    public CaseVide(int x, int y, Model m, int gameID){

        super(x,y,m, gameID);
        transform.position().setZ(2f);
    }

    public String toString(){

        return VIDE;
    }

    @Override
    public String nomSprite() {
        if(estToucher()) {

            return "Miss";
        } else {

            return "Water";
        }
    }
}
