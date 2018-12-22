package assets.scripts.epoque;

import assets.scripts.Model;

import java.rmi.RemoteException;
import java.util.HashMap;


public class Space extends Epoque {

    public static final String NAME = "Espace";

    private static final HashMap<Integer, Integer> vieBateaux;

    static {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        tmp.put(2, 2);
        tmp.put(3, 3);
        tmp.put(4, 4);
        tmp.put(5, 4);
        vieBateaux = tmp;
    }


    public Space(boolean initShips, Model model, int gameID) throws RemoteException {

        super(initShips, model, gameID);
    }

    @Override
    protected HashMap<Integer, Integer> getVieBattleships() {

        return vieBateaux;
    }
}
