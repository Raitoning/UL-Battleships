package battleship.map;

public enum Case {

    Vide,Bateau,TirRate,TirSurBateau,BateauDetruit;


    public static Case fromString(String s){

        if (s.equals(Vide.toString())){
            return Vide;
        } else if(s.equals(Bateau.toString())){
            return Bateau;
        }else if (s.equals(TirRate.toString())){
            return TirRate;
        } else if (s.equals(TirSurBateau.toString())){
            return TirSurBateau;
        } else if (s.equals(BateauDetruit.toString())){
            return BateauDetruit;
        } else{
            return null;
        }
    }

    @Override
    public String toString() {

        if(this == Vide){
            return "[ ]";
        } else if(this == Bateau){
            return "[B]";
        }else if (this == TirRate){
            return "[o]";
        } else if (this == TirSurBateau){
            return "[x]";
        } else if (this == BateauDetruit){
            return "[^]";
        } else{
            return "[R]";//Case D'erreur
        }


    }
}
