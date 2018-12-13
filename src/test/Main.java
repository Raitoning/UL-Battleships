package test;

import engine.Engine;

import javax.swing.*;

public class Main {

    public static void main (String[] args){


        boolean solo;
        JOptionPane d = new JOptionPane();
        // les textes figurant // sur les boutons
        String lesTextes[]={ "Solo", "Reseau"};
        int retour = d.showOptionDialog(null, "le message", "le titre", JOptionPane.INFORMATION_MESSAGE, JOptionPane.INFORMATION_MESSAGE, null, lesTextes,  lesTextes[0]);
        if( retour!=JOptionPane.CLOSED_OPTION){
            if(lesTextes[retour].equals("Solo")){
                Engine.getInstance();
            }
            if(lesTextes[retour].equals("Reseau")){
                Engine.getInstance();
            }
        } else{
            Engine.getInstance().exit();
        }



    }
}
