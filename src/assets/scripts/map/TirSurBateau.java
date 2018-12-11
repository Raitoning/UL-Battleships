package assets.scripts.map;

import assets.scripts.Game;

public class TirSurBateau extends Case {

    public TirSurBateau(){
        super();
    }

    public TirSurBateau(int x, int y, Game m){
        super(x,y,m);
    }

    @Override
    public String toString() {
        return TIRSURBATEAU;
    }

    @Override
    public String nomSprite() {
        return "tirBateau";
    }
}
