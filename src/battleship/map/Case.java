package battleship.map;

public enum Case {

    Vide,Bateau,TirRate,TirSurBateau,BateauDetruit;


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
