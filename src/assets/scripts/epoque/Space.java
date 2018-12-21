package assets.scripts.epoque;

import java.rmi.RemoteException;
import java.util.HashMap;

import assets.scripts.Model;


public class Space extends Epoque {

    public static final String NAME = "Espace";

    private static final HashMap<Integer, Integer> vieBateaux;
    static {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        tmp.put(2,2);
        tmp.put(3,3);
        tmp.put(4,4);
        tmp.put(5,4);
        vieBateaux = tmp;
    }


    public Space(boolean b, Model m, int gameID) throws RemoteException {
        super(b,m, gameID);
    }

    @Override
    protected HashMap<Integer, Integer> getVieBattleships() {
        return vieBateaux;
    }
}
