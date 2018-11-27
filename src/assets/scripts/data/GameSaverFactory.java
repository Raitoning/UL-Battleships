package assets.scripts.data;

import assets.scripts.Game;

public abstract class GameSaverFactory {

    private static GameSaverFactory instance;

    public abstract void load (Game game);

    public abstract void save (Game game);

    public static GameSaverFactory getInstance() {

        if (instance==null){
            instance = new XMLSaving();
        }

        return instance;
    }
}
