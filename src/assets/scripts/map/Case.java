package assets.scripts.map;

public interface Case {

    static String VIDE = "[ ]";
    static String BATEAU = "[B]";
    static String TIRRATE = "[o]";
    static String TIRSURBATEAU = "[x]";
    static String BATEAUDETRUIT = "[^]";

    public static Case fromString(String s){

        if (s.equals(VIDE)){
            return new CaseVide();
        } else if(s.equals(BATEAU)){
            return new Bateau();
        }else if (s.equals(TIRRATE)){
            return new TirRate();
        } else if (s.equals(TIRSURBATEAU)){
            return new TirSurBateau();
        } else if (s.equals(BATEAUDETRUIT)){
            return new BateauDetruit();
        } else{
            return null;
        }
    }

    @Override
    public String toString();
}
