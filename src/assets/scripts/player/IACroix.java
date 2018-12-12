package assets.scripts.player;

import assets.scripts.Game;
import assets.scripts.epoque.Epoque;
import assets.scripts.map.Case;

public class IACroix extends Player implements IA{
    public IACroix(int idJoueur, Game m, int gameID) {
        super(idJoueur, m, gameID);
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
