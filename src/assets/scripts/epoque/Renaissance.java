package assets.scripts.epoque;

import java.rmi.RemoteException;

import assets.scripts.Game;
import assets.scripts.map.Case;
import assets.scripts.map.Position;


public class Renaissance extends Epoque {

    public Renaissance(boolean b, Game m) throws RemoteException {
        super(b,m);
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
