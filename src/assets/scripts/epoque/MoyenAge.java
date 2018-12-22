package assets.scripts.epoque;

import assets.scripts.Model;

import java.rmi.RemoteException;
import java.util.HashMap;

public class MoyenAge extends Epoque {

    public static final String NAME = "MoyenAge";

    private static final HashMap<Integer, Integer> vieBateaux;

    static {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        tmp.put(2, 1);
        tmp.put(3, 1);
        tmp.put(4, 2);
        tmp.put(5, 2);
        vieBateaux = tmp;
    }

    public MoyenAge(boolean initShips, Model model, int gameID) throws RemoteException {

        super(initShips, model, gameID);
    }

    @Override
    protected HashMap<Integer, Integer> getVieBattleships() {

        return vieBateaux;
    }
}
