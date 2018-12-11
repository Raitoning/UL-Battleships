package assets.scripts.epoque;

import assets.scripts.Game;
import java.rmi.RemoteException;



public class MoyenAge extends Epoque {

    public MoyenAge(boolean init,Game m) throws RemoteException {
        super(init,m);
    }


    @Override
    public String getCheminTexture() {
        return null;
    }

    @Override
    public String name() {
        return "MoyenAge";
    }
}
