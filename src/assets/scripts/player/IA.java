package assets.scripts.player;

import assets.scripts.Model;
import assets.scripts.map.Case;
import assets.scripts.map.Map;
import engine.Vector2;

import java.util.ArrayList;

public abstract class IA extends Player {

    ArrayList<Case> cases;
    ArrayList<Case> priority;

    IA(int idJoueur, Model model, int gameID) {

        super(idJoueur, model, gameID);

        cases = new ArrayList<>();
        priority = new ArrayList<>();

        transform.position().setX(15.5f);
        transform.position().setY(4.5f);

        camera.setMinRenderArea(new Vector2(0.5f, 0f));

    }

    public abstract Case jeuxIA();


    void addVoisins(Case s) {
        int x = s.getPosX();
        int y = s.getPosY();

        if (x > 0) {
            if (!model.getEpoque().getCaseAt(opponentID(), x - 1, y).estToucher()) {
                priority.add(model.getEpoque().getCaseAt(opponentID(), x - 1, y));
            }
        }

        if (x < Map.NBCASES - 1) {
            if (!model.getEpoque().getCaseAt(opponentID(), x + 1, y).estToucher()) {
                priority.add(model.getEpoque().getCaseAt(opponentID(), x + 1, y));
            }
        }

        if (y > 0) {
            if (!model.getEpoque().getCaseAt(opponentID(), x, y - 1).estToucher()) {
                priority.add(model.getEpoque().getCaseAt(opponentID(), x, y - 1));
            }
        }

        if (y < Map.NBCASES - 1) {
            if (!model.getEpoque().getCaseAt(opponentID(), x, y + 1).estToucher()) {
                priority.add(model.getEpoque().getCaseAt(opponentID(), x, y + 1));
            }
        }
    }

    public void clean() {
        ArrayList<Case> tmp = new ArrayList<>();

        for (Case b : cases) {
            if (b.estToucher()) {
                tmp.add(b);
            }
        }

        for (Case b : priority) {
            if (b.estToucher()) {
                tmp.add(b);
            }
        }

        for (Case b : tmp) {
            cases.remove(b);
            priority.remove(b);
        }
    }

    void extreme() {

        for (int i = 0; i < Map.NBCASES; i++) {
            for (int j = 0; j < Map.NBCASES; j++) {
                if (!model.getEpoque().getCaseAt(opponentID(), i, j).estToucher()) {
                    cases.add(model.getEpoque().getCaseAt(opponentID(), i, j));
                }
            }
        }
    }
}
