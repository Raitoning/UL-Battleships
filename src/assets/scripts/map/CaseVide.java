package assets.scripts.map;

import assets.scripts.Model;

public class CaseVide extends Case {


    public CaseVide(Position p , int x, int y, Model m, int gameID){

        super(p,x,y,m, gameID);
        transform.position().setZ(2f);
    }

    public String toString(){

        return VIDE;
    }

    @Override
    public String nomSprite() {
        String res = getSpriteFolder();
        if(estToucher()) {

            res +="Miss";
        } else {

            res+= "Water";
        }
        return res;
    }
}
