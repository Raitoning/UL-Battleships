package assets.scripts.data;

import assets.scripts.Model;
import assets.scripts.epoque.Battleship;
import assets.scripts.map.*;

import java.io.*;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import javax.swing.*;
import javax.swing.filechooser.FileNameExtensionFilter;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;

import engine.Engine;
import org.w3c.dom.Document;
import org.w3c.dom.Element;
import org.w3c.dom.Node;
import org.w3c.dom.NodeList;
import org.xml.sax.SAXException;

public class XMLSaving extends GameSaverFactory {

    XMLSaving() {

    }

    @Override
    public void load() {

        try {
            JFileChooser j = new JFileChooser();
            FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
            j.setFileFilter(filter);
            int result = j.showDialog(null,"Charger");
            String dest = j.getSelectedFile()+"";

            if(dest != null && result == JFileChooser.APPROVE_OPTION)
                if(!dest.equals("")){
                    if (dest.endsWith(".xml")){
                        loadFile(new File(dest));
                        JOptionPane.showMessageDialog(null,
                                "Chargement effectué !",
                                "Chargement de partie",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
                }
        } catch (IOException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null,
                    "Erreur : impossible de charger la partie !",
                    "Chargement de partie",
                    JOptionPane.WARNING_MESSAGE);
        }

    }

    @Override
    public void save(Model model) {

        if(model != null) {
            try {
                JFileChooser j = new JFileChooser();
                FileNameExtensionFilter filter = new FileNameExtensionFilter("XML", "xml");
                j.setFileFilter(filter);
                int result = j.showDialog(null,"Sauvegarder");
                String dest = j.getSelectedFile()+"";

                if(dest != null && result == JFileChooser.APPROVE_OPTION)
                    if(!dest.equals("")){
                        if (!dest.endsWith(".xml"))
                            dest += ".xml";
                        saveFile(new File(dest), model);
                        JOptionPane.showMessageDialog(null,
                                "Sauvegarde réussie !",
                                "Sauvegarde",
                                JOptionPane.INFORMATION_MESSAGE);
                    }
            } catch (IOException e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                        "Erreur dans la créatio nde la sauvegarde !",
                        "Sauvegarde",
                        JOptionPane.WARNING_MESSAGE);
            }
        }
    }

    /**Fonction d'ecriture de fichier
     *
     * @param file fichier à ecrire
     * @throws IOException erreure de lecture/ecriture fichier
     */
    public void saveFile(File file,Model model) throws IOException{

        FileWriter flot ;
        PrintWriter flotFiltre ;
        flot = new FileWriter(file, false) ;
        flotFiltre = new PrintWriter(new BufferedWriter(flot)) ;

        flotFiltre.println("<model>");

        // ----------Type de Joueur-------------------
        flotFiltre.println("\t<type>");
        for (int i=0;i<2;i++) {
            flotFiltre.println("\t\t<joueur id='"+i+"'>"+ model.getTypeofPlayer(i)+"</joueur>");
        }
        flotFiltre.println("\t</type>");

        // ----------Epoque-------------------
        flotFiltre.println("\t<epoque>");
        flotFiltre.println("\t\t<name>"+ model.getNameEpoque()+"</name>");

        // ----------Battleships-------------------

        flotFiltre.println("\t\t<battleships>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");



            for (Battleship b : model.getEpoque().getBattleships(i)) {

                flotFiltre.println("\t\t\t\t<battleship x='" + b.getPosition().getX()+
                        "' y='"+ b.getPosition().getY()+"' l='"+b.getLength()+"' v='" +
                        b.isVertical() + "' h='"+ b.getPv()+"'> </battleship>");
            }



            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</battleships>");


        // ----------Map-------------------
        flotFiltre.println("\t\t<map>");
        for (int i=0;i<2;i++) {

            flotFiltre.println("\t\t\t<joueur id='"+i+"'>");

            for(int k=0; k< Map.NBCASES;k++){
                for (int j=0;j<Map.NBCASES;j++){
                    if(model.getEpoque().getCaseAt(i,k,j).estToucher())
                        flotFiltre.println("\t\t\t\t<case x='"+k+"' y='"+j+"'>"+ model.getEpoque().getCaseAt(i,k,j)+"</case>");
                }
            }

            flotFiltre.println("\t\t\t</joueur>");
        }

        flotFiltre.println("\t\t</map>");

        flotFiltre.println("\t</epoque>");

        flotFiltre.println("</model>");

        flotFiltre.close() ;
    }


    public void loadFile(File file) throws IOException{
        String ep = "";
        String adv = "";
        ArrayList<Position> casesTouche = new ArrayList<>();

        ArrayList<Battleship> liste1 = new ArrayList<>();
        ArrayList<Battleship> liste2 = new ArrayList<>();

        // Etape 1 : récupération d'une instance de la classe "DocumentBuilderFactory"
        final DocumentBuilderFactory factory = DocumentBuilderFactory.newInstance();
            boolean v = true; //Passer à true pour afficher les contenus du fichierXML
        try {
            // Etape 2 : création d'un parseur
            final DocumentBuilder builder = factory.newDocumentBuilder();

            //Etape 3 : création d'un Document
            final Document document = builder.parse(file);


            final Element racine = document.getDocumentElement();

            //Affichage de l'élément racine
            println(v,racine.getNodeName());

            if (!racine.getNodeName().equals("model")){
                println(v,"Erreur pas racine =/= model");
            }

	        // Etape 5 : récupération des personnes
            final NodeList racineNoeuds = racine.getChildNodes();
            final int nbRacineNoeuds = racineNoeuds.getLength();

            for (int i = 0; i<nbRacineNoeuds; i++) {
                if(racineNoeuds.item(i).getNodeType() == Node.ELEMENT_NODE) {
                    final Element node = (Element) racineNoeuds.item(i);

                    final String name = node.getNodeName();
                    if(name.equals("type")){
                        println(v,"\n*************Type Of Players************");
                        final Element joueur = (Element) node.getElementsByTagName("joueur").item(0);
                        println(v,"type: "+joueur.getTextContent()+" ");
                        println(v,"id : " + joueur.getAttribute("id"));
                        final Element joueur2 = (Element) node.getElementsByTagName("joueur").item(1);
                        println(v,"Type: "+joueur2.getTextContent()+" ");
                        println(v,"id : " + joueur2.getAttribute("id"));
                        //on recupère le type d'adversaire
                        adv = joueur2.getTextContent();
                    } else if(name.equals("epoque")){
                        println(v,"\n*************Epoque************");
                        final Element ename = (Element) node.getElementsByTagName("name").item(0);
                        println(v,"type: "+ename.getTextContent());
                        ep = ename.getTextContent();

                        println(v,"\n*************Map************");
                        final Element map = (Element) node.getElementsByTagName("map").item(0);

                        final Element joueur = (Element) map.getElementsByTagName("joueur").item(0);
                        println(v,"id : " + joueur.getAttribute("id"));
                        final NodeList casesj1 = joueur.getChildNodes();
                        final int nbCasesj1 = casesj1.getLength();
                        for (int j = 0; j<nbCasesj1; j++) {
                            if(casesj1.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element case1 = (Element) casesj1.item(j);
                                println(v,"case: "+case1.getTextContent()+" x:"
                                        + case1.getAttribute("x")+" y:"
                                        + case1.getAttribute("y"));
                                int x = Integer.parseInt(case1.getAttribute("x"));
                                int y = Integer.parseInt(case1.getAttribute("y"));
                                casesTouche.add(new Position(x,y));
                            }
                        }

                        final Element joueur2 = (Element) map.getElementsByTagName("joueur").item(1);
                        println(v,"id : " + joueur2.getAttribute("id"));
                        final NodeList casesj2 = joueur2.getChildNodes();
                        final int nbCasesj2 = casesj2.getLength();
                        for (int j = 0; j<nbCasesj2; j++) {
                            if(casesj2.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element case2 = (Element) casesj2.item(j);
                                println(v,"case: "+case2.getTextContent()+" x:"
                                        + case2.getAttribute("x")+" y:"
                                        + case2.getAttribute("y"));
                                int x = Integer.parseInt(case2.getAttribute("x"));
                                int y = Integer.parseInt(case2.getAttribute("y"));
                                casesTouche.add(new Position(x+Map.NBCASES+1,y));
                            }
                        }


                        println(v,"\n*************Battleships************");
                        final Element battleships = (Element) node.getElementsByTagName("battleships").item(0);
                        final Element player = (Element) battleships.getElementsByTagName("joueur").item(0);
                        println(v,"id : " + player.getAttribute("id"));
                        final NodeList ships1 = player.getChildNodes();
                        final int nbshipsj1 = ships1.getLength();
                        for (int j = 0; j<nbshipsj1; j++) {
                            if(ships1.item(j).getNodeType() == Node.ELEMENT_NODE) {
                                final Element ship1 = (Element) ships1.item(j);
                                println(v,"case: "+ship1.getTextContent()
                                        +" x:" + ship1.getAttribute("x")
                                        +" y:" + ship1.getAttribute("y")
                                        +" l:" + ship1.getAttribute("l")
                                        +" v:" + ship1.getAttribute("v")
                                        +" h:" + ship1.getAttribute("h"));
                                int longeur = Integer.parseInt(ship1.getAttribute("l"));
                                int posx = Integer.parseInt(ship1.getAttribute("x"));
                                int posy = Integer.parseInt(ship1.getAttribute("y"));
                                int hp = Integer.parseInt(ship1.getAttribute("h"));
                                boolean verti = Boolean.parseBoolean(ship1.getAttribute("v"));
                                liste1.add(new Battleship(new Position(posx,posy),hp,longeur,verti,null));
                            }
                        }

                        final Element player2 = (Element) battleships.getElementsByTagName("joueur").item(1);
                        println(v,"id : " + player2.getAttribute("id"));
                        final NodeList ships2 = player2.getChildNodes();
                        final int nbshipsj2 = ships2.getLength();
                        for (int k = 0; k<nbshipsj2; k++) {
                            if(ships2.item(k).getNodeType() == Node.ELEMENT_NODE) {
                                final Element ship2 = (Element) ships2.item(k);
                                println(v,"case: "+ship2.getTextContent()
                                        +" x:" + ship2.getAttribute("x")
                                        +" y:" + ship2.getAttribute("y")
                                        +" l:" + ship2.getAttribute("l")
                                        +" v:" + ship2.getAttribute("v")
                                        +" h:" + ship2.getAttribute("h"));
                                int longeur = Integer.parseInt(ship2.getAttribute("l"));
                                int posx = Integer.parseInt(ship2.getAttribute("x"));
                                int posy = Integer.parseInt(ship2.getAttribute("y"));
                                int hp = Integer.parseInt(ship2.getAttribute("h"));
                                boolean verti = Boolean.parseBoolean(ship2.getAttribute("v"));
                                liste2.add(new Battleship(new Position(posx,posy),hp,longeur,verti,null));
                            }
                        }
                    }
                }
            }
            Engine.getInstance().getGame().loadModel(ep,adv,liste1,liste2,casesTouche);

        } catch (ParserConfigurationException | SAXException | IOException e) {

            e.printStackTrace();
        }
    }

    private void println(boolean v, String s) {

        if(v) {

            System.out.println(s);
        }
    }
}
