package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import assets.scripts.map.Position;

public class IACroix extends Player implements IA{
    public IACroix(int idJoueur, Game m) {
        super(idJoueur,m);
    }

    @Override
    public void play(Case c) {
        super.play(c);
        //TODO
    }

    @Override
    public String toString() {
        return "IACroix";
    }

    @Override
    public Case jeuxIA() {

        //TODO
        return null;
    }
}
