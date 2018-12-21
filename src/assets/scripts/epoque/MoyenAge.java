package assets.scripts.epoque;

import assets.scripts.Model;

import java.rmi.RemoteException;
import java.util.Collections;
import java.util.HashMap;
import java.util.Map;


public class MoyenAge extends Epoque {

    public static final String NAME = "MoyenAge";

    private static final HashMap<Integer, Integer> vieBateaux;
    static {
        HashMap<Integer, Integer> tmp = new HashMap<>();
        tmp.put(2,1);
        tmp.put(3,1);
        tmp.put(4,2);
        tmp.put(5,2);
        vieBateaux = tmp;
    }

    public MoyenAge(boolean init, Model m, int gameID) throws RemoteException {
        super(init,m, gameID);
        vieBateaux.put(2,2);
        vieBateaux.put(3,3);
        vieBateaux.put(4,4);
        vieBateaux.put(5,5);
    }

    @Override
    protected HashMap<Integer, Integer> getVieBattleships(){
        return vieBateaux;
    }
}
