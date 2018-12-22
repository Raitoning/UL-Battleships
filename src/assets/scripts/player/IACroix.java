package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;

import java.util.ArrayList;

public class IACroix extends IA {

    public static final String NAME = "IA Croix Aléatoire";

    public IACroix(int idJoueur, Model model, int gameID) {

        super(idJoueur, model, gameID);

        priority = new ArrayList<>();

        for (int i = 0; i < Map.NBCASES; i++) {
            for (int j = 0; j < Map.NBCASES; j++) {
                if (i % 2 == 0 || j % 2 == 0) {
                    cases.add(this.model.getEpoque().getCaseAt(opponentID(), i, j));
                }
            }
        }
    }

    @Override
    public String toString() {

        return NAME;
    }

    @Override
    public Case jeuxIA() {

        int x;
        Case res;

        clean();

        if (!priority.isEmpty()) {
            x = (int) (Math.random() * (priority.size()));
            res = priority.get(x);
            priority.remove(x);

        } else {
            x = (int) (Math.random() * (cases.size()));
            res = cases.get(x);
        }

        cases.remove(res);

        if (cases.isEmpty()) {
            extreme();
        }

        if (res.toString().equals(Case.BATEAU)) {
            addVoisins(res);
        }

        return res;
    }
}
