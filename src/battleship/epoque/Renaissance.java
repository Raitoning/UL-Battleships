package battleship.epoque;

import battleship.map.Map;

public class Renaissance extends Epoque {

    //Todo: Implementer (se baser sur MoyenAge)
    public Renaissance(Map first, Map second) {
        super();
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
