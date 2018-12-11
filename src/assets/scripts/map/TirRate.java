package assets.scripts.map;

import assets.scripts.Game;

public class TirRate extends Case {

    public TirRate(int x,int y,Game m){
        super(x,y,m);
    }

    @Override
    public String toString() {
        return TIRRATE;
    }

    @Override
    public String nomSprite() {
        return "Miss";
    }
}
