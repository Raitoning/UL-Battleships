package assets.scripts.epoque;

import java.rmi.RemoteException;

import assets.scripts.Model;


public class Space extends Epoque {

    public static final String NAME = "Espace";

    public Space(boolean b, Model m, int gameID) throws RemoteException {
        super(b,m, gameID);
    }
}
