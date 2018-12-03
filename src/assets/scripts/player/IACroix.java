package assets.scripts.player;

import assets.scripts.map.Map;
import assets.scripts.map.Position;

public class IACroix extends Player {
    public IACroix(int idJoueur) {
        super(idJoueur);
    }

    @Override
    public Position play(Map m) {
        return null;
    }

    @Override
    public String toString() {
        return "IACroix";
    }
}
