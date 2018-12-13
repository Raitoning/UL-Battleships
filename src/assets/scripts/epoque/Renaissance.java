package assets.scripts.epoque;

import java.rmi.RemoteException;

import assets.scripts.Model;


public class Renaissance extends Epoque {

    public Renaissance(boolean b, Model m, int gameID) throws RemoteException {
        super(b,m, gameID);
    }

    @Override
    public String getCheminTexture() {
        return null;
    }

    @Override
    public String name() {
        return "Renaissance";
    }

}
