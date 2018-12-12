package assets.scripts.epoque;

import assets.scripts.Game;
import assets.scripts.map.Bateau;
import assets.scripts.map.Case;
import assets.scripts.map.Position;

import java.rmi.RemoteException;



public class MoyenAge extends Epoque {

    public MoyenAge(boolean init,Game m, int gameID) throws RemoteException {
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
