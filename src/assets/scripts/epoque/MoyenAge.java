package assets.scripts.epoque;

import assets.scripts.Model;

import java.rmi.RemoteException;



public class MoyenAge extends Epoque {

    public static final String NAME = "MoyenAge";

    public MoyenAge(boolean init, Model m, int gameID) throws RemoteException {
        super(init,m, gameID);
    }
}
