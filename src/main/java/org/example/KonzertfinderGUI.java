package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Collections;

public class KonzertfinderGUI extends JFrame {
    private JLabel lblEingabe;
    private JLabel lblKuenstlername;
    private JLabel lblDatum;
    private JLabel lblGenre;
    private JLabel lblKartenpreis;
    private JLabel lblBarrierefreiEingabe;
    private JTextField tfKuenstlername;
    private JTextField tfDatum;
    private JTextField tfPreis;
    private JComboBox comboGenre;
    private JCheckBox checkBarrierefreiEingabe;
    private JScrollPane scrollPane;
    private JTextArea taAusgabe;
    private JComboBox comboSortieren;
    private JLabel lblSortieren;
    private JLabel lblVerfügbar;
    private JLabel lblFilter;
    private JRadioButton rbPop;
    private JRadioButton rbRock;
    private JRadioButton rbSchlager;
    private JRadioButton rbDance;
    private JRadioButton rbHipHop;
    private JCheckBox checkBarrierefreiFilter;
    private JButton btFiltern;
    private JLabel lblEuro;
    private JButton btSpeichern;
    private JButton btAlleAnzeigen;
    private JPanel jpPanel;
    private JLabel lblUhrzeit;
    private JTextField tfUhrzeit;


    private ArrayList<Konzert> konzertliste = new ArrayList<Konzert>();

    public KonzertfinderGUI() throws HeadlessException {
        setTitle("Konzertsuche");
        setContentPane(jpPanel);
        setSize(1200,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);

        initObjekte(); //vorgegebene Objekte initialisieren

        btAlleAnzeigen.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                alleAusgeben();
            }
        });
        btSpeichern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                addNewKonzert();
            }
        });
        btFiltern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtern();
            }
        });
        comboSortieren.addItemListener(new ItemListener() {
            @Override
            public void itemStateChanged(ItemEvent e) {
                sortieren();
            }
        });
        btFiltern.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                filtern();
            }
        });
    }

    //Methode zum Initialisieren von drei Objekten und Hinzufügen zur ArrayList
    public void initObjekte(){
        LocalDateTime d1 =  LocalDateTime.of(2025,04,23,19,00);
        Konzert init1 = new Konzert("Ed Sheeran", d1, "Pop", 45,true);
        konzertliste.add(init1);

        LocalDateTime d2 =  LocalDateTime.of(2025,07,02,20,30);
        Konzert init2 = new Konzert("Linkin Park", d2, "Rock", 110,true);
        konzertliste.add(init2);

        LocalDateTime d3 = LocalDateTime.of(2025,03,19,21,00);
        Konzert init3 = new Konzert("Eminem",d3,"HipHop",85,false);
        konzertliste.add(init3);
    }

    //Methode zum hinzufügen eines neuen Konzerts zur Array List
    private void addNewKonzert(){

        //Werte aus Eingabefeldern auslesen
        String kuenstlerEingabe = null;
        String genreEingabe = comboGenre.getSelectedItem().toString();
        boolean barrierefreiEingabe = checkBarrierefreiEingabe.isSelected();
        String datumEingabe = tfDatum.getText().toString();
        String uhrzeitEingabe = tfUhrzeit.getText().toString();
        double kartenpreisEingabe = 0; //Wertzuweisung aus Textfeld im nächsten Schritt

        //Sicherstellen, dass Genre ausgewählt wurde
        try {
            if (genreEingabe.equals("Auswählen...")){
                throw new Exception("Fehler!");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Bitte Genre auswählen.","Fehler",JOptionPane.ERROR_MESSAGE);
            return;
        }

        //Werfen einer Exception, wenn tfKuenstlername leer bleibt
        try {
            kuenstlerEingabe = tfKuenstlername.getText().toString();
            kuenstlerEingabe.trim();
            if (kuenstlerEingabe.equals("")){
                throw new Exception("Fehler!");
            }
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Bitte Künstlername eingeben.","Fehlerhafte Eingabe",JOptionPane.ERROR_MESSAGE );
        }

        //Fehlerhafte Eingaben beim Kartenpreis abfangen
        try {
            kartenpreisEingabe = Double.parseDouble(tfPreis.getText().toString());
        } catch (Exception e){
            JOptionPane.showMessageDialog(null,"Fehlerhafte Eingabe im Feld Kartenpreis","Fehler",JOptionPane.ERROR_MESSAGE);
        }


        //Fehlerhafte Eingaben bei Datum und Uhrzeit abfangen

        LocalDateTime ldtNeu = null; //Initialisieren der LocalDateTime ldtNeu außerhalb der try-catch-Anweisung, um später außerhalb darauf zugreifen zu können
        try {
            //String datumEingabe in tag, monat und jahr aufteilen
            int tag = Integer.parseInt(datumEingabe.substring(0,2));
            int monat = Integer.parseInt(datumEingabe.substring(3,5));
            int jahr = Integer.parseInt(datumEingabe.substring(6));

            //String uhrzeitEingabe in stunde und minute aufteilen
            String stundeEingabe = uhrzeitEingabe.substring(0,2);
            String minuteEingabe = uhrzeitEingabe.substring(3);
            int stunde = Integer.parseInt(stundeEingabe);
            int minute = Integer.parseInt(minuteEingabe);

            //einsetzen in neue LocalDateTime
            ldtNeu = LocalDateTime.of(jahr, monat, tag, stunde, minute);

        } catch (Exception e) {
            tfDatum.setText("");
            tfUhrzeit.setText("");
            JOptionPane.showMessageDialog(null,"Fehlerhafte Eingabe von Datum und/oder Uhrzeit!", "Fehler", JOptionPane.ERROR_MESSAGE);
        }

        //Hinzufügen des Objekts zur Array-List
        Konzert kNeu = new Konzert(kuenstlerEingabe,ldtNeu,genreEingabe,kartenpreisEingabe,barrierefreiEingabe);
        konzertliste.add(kNeu);
    }

    //Methode zum Anzeigen aller Objekte der ArrayList in der JTextArea
    private void alleAusgeben(){
        sortieren();    //nach eingestelltem Sortiermodus sortieren

        taAusgabe.setText("");
        for (Konzert ele : konzertliste){
            taAusgabe.append("\n" + ele.getKuenstlername() +  "\t" + ele.getGenre() +  "\t" + ele.getKartenpreis() +  "\t" + ele.getFormatiertesDatum() +  "\t" + ele.isBarrierefrei());
        }
    }


    private void filtern(){
        boolean pop = rbPop.isSelected();
        boolean rock = rbRock.isSelected();
        boolean schlager = rbSchlager.isSelected();
        boolean dance = rbDance.isSelected();
        boolean hiphop = rbHipHop.isSelected();
        
        taAusgabe.setText("");
        boolean eintragGefunden = false;
        boolean barrierefreiFilter = checkBarrierefreiFilter.isSelected();

        for (Konzert ele : konzertliste){
            String objektGenre = ele.getGenre();
            boolean objektBarrierefrei = ele.isBarrierefrei();

            if (pop == true && objektGenre.equals("Pop")){
                if (barrierefreiFilter && objektBarrierefrei || !barrierefreiFilter && !objektBarrierefrei) {
                    taAusgabe.append("\n" + ele.getKuenstlername() + "\t" + ele.getGenre() + "\t" + ele.getKartenpreis() + "\t" + ele.getFormatiertesDatum() + "\t" + ele.isBarrierefrei());
                    eintragGefunden = true;
                }
            }
            if (rock == true && objektGenre.equals("Rock")){
                if (barrierefreiFilter && objektBarrierefrei || !barrierefreiFilter && !objektBarrierefrei) {
                    taAusgabe.append("\n" + ele.getKuenstlername() + "\t" + ele.getGenre() + "\t" + ele.getKartenpreis() + "\t" + ele.getFormatiertesDatum() + "\t" + ele.isBarrierefrei());
                    eintragGefunden = true;
                }
            }
            if (schlager == true && objektGenre.equals("Schlager")){
                if (barrierefreiFilter && objektBarrierefrei || !barrierefreiFilter && !objektBarrierefrei) {
                    taAusgabe.append("\n" + ele.getKuenstlername() + "\t" + ele.getGenre() + "\t" + ele.getKartenpreis() + "\t" + ele.getFormatiertesDatum() + "\t" + ele.isBarrierefrei());
                    eintragGefunden = true;
                }
            }
            if (dance == true && objektGenre.equals("Dance")){
                if (barrierefreiFilter && objektBarrierefrei || !barrierefreiFilter && !objektBarrierefrei) {
                    taAusgabe.append("\n" + ele.getKuenstlername() + "\t" + ele.getGenre() + "\t" + ele.getKartenpreis() + "\t" + ele.getFormatiertesDatum() + "\t" + ele.isBarrierefrei());
                    eintragGefunden = true;
                }
            }
            if (hiphop == true && objektGenre.equals("HipHop")){
                if (barrierefreiFilter && objektBarrierefrei || !barrierefreiFilter && !objektBarrierefrei) {
                    taAusgabe.append("\n" + ele.getKuenstlername() + "\t" + ele.getGenre() + "\t" + ele.getKartenpreis() + "\t" + ele.getFormatiertesDatum() + "\t" + ele.isBarrierefrei());
                    eintragGefunden = true;
                }
            }
        }
        if (eintragGefunden == false){
            taAusgabe.setText("Keine passenden Konzerte gefunden.");
        }
    }

    //Methode zum Sortieren der Objekte in der ArrayList und anschließende Ausgabe in der JTextArea
    private void sortieren(){
        String sortiermodus = comboSortieren.getSelectedItem().toString();      //lieber in eine Zeile???

        //Liste je nach ausgewähltem Sortiermodus ordnen
        if (sortiermodus.equals("Preis aufsteigend")){
            Collections.sort(konzertliste, (sort1,sort2) -> Double.compare(sort1.getKartenpreis(),sort2.getKartenpreis()));
        }
        if (sortiermodus.equals("Preis absteigend")){
            Collections.sort(konzertliste, (sort1,sort2) -> Double.compare(sort1.getKartenpreis(),sort2.getKartenpreis()));
            Collections.reverse(konzertliste);
        }
        if (sortiermodus.equals("Datum aufsteigend")){
            Collections.sort(konzertliste, (sort1,sort2) -> sort1.getDatum().compareTo(sort2.getDatum()));
        }
        if (sortiermodus.equals("Datum absteigend")){
            Collections.sort(konzertliste, (sort1,sort2) -> sort1.getDatum().compareTo(sort2.getDatum()));
            Collections.reverse(konzertliste);
        }

        //Ausgeben der sortierten Liste und filtern über Aufruf der Methode filtern()
        filtern();
    }

}
