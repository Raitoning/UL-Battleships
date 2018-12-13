package assets.scripts.data;

import assets.scripts.Model;

public abstract class GameSaverFactory {

    private static GameSaverFactory instance;

    public abstract void load (Model model);

    public abstract void save (Model model);

    public static GameSaverFactory getInstance() {

        if (instance==null){
            instance = new XMLSaving();
        }

        return instance;
    }
}
