package assets.scripts.epoque;

public class Renaissance extends Epoque {

    //Todo: Implementer (se baser sur MoyenAge)
    public Renaissance() {
        super(true);
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
