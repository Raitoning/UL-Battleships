package assets.scripts.map;

import assets.scripts.Game;

public class CaseVide extends Case {


    public CaseVide(int x, int y, Game m){
        super(x,y,m);
        transform.position().setZ(2f);
    }

    public String toString(){
        return VIDE;
    }

    @Override
    public String nomSprite() {
        if(estToucher())
            return "Miss";
        else return "Water";
    }
}
