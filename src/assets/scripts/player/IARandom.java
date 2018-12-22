package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;


public class IARandom extends IA {

    public static final String NAME = "IA Aléatoire";

    public IARandom(int idJoueur, Model model, int gameID) {

        super(idJoueur, model, gameID);

        for (int i = 0; i < Map.NBCASES; i++) {
            for (int j = 0; j < Map.NBCASES; j++) {
                cases.add(this.model.getEpoque().getCaseAt(opponentID(), i, j));
            }
        }

    }

    @Override
    public String toString() {

        return NAME;
    }

    @Override
    public Case jeuxIA() {

        int x = (int) (Math.random() * (cases.size()));
        Case res = cases.get(x);
        cases.remove(x);
        return res;
    }

}
