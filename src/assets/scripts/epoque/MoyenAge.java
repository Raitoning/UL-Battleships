package assets.scripts.epoque;

import assets.scripts.Model;

import java.rmi.RemoteException;



public class MoyenAge extends Epoque {

    public MoyenAge(boolean init, Model m, int gameID) throws RemoteException {
        super(init,m, gameID);
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
