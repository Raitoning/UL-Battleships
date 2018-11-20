package battleship.player;

import battleship.map.Map;
import battleship.map.Position;

public class IARandom extends Player {
    public IARandom(int idJoueur) {
        super(idJoueur);
    }

    @Override
    public Position play(Map m) {
        return null;
    }

    @Override
    public String toString() {
        return "IARandom";
    }
}
