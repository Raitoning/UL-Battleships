package assets.scripts.epoque;

import java.rmi.RemoteException;

import assets.scripts.Game;

public class Renaissance extends Epoque {

    //Todo: Implementer (se baser sur MoyenAge)
    public Renaissance(Game m) throws RemoteException {
        super(true, m);
    }

    @Override
    protected void battleshipInit(int idPlayer) {

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
