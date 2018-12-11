package assets.scripts.map;

import assets.scripts.Game;

public class BateauDetruit extends Case {

    public BateauDetruit(int x, int y,Game m){
        super(x,y,m);
    }

    @Override
    public String toString() {
        return BATEAUDETRUIT;
    }

    @Override
    public String nomSprite() {
        return "detruit";
    }
}
