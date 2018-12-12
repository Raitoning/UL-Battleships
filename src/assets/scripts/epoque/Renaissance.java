package assets.scripts.epoque;

import java.rmi.RemoteException;

import assets.scripts.Game;


public class Renaissance extends Epoque {

    public Renaissance(boolean b, Game m, int gameID) throws RemoteException {
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
