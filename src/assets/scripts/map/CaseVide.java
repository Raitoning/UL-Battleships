package assets.scripts.map;

import assets.scripts.Game;

public class CaseVide extends Case {

    public CaseVide(){
        super();
    }

    public CaseVide(int x, int y, Game m){
        super(x,y,m);
    }

    public String toString(){
        return VIDE;
    }

    @Override
    public String nomSprite() {
        return "Water";
    }
}
