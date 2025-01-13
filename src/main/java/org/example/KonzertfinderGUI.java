package org.example;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDateTime;
import java.util.ArrayList;

public class KonzertfinderGUI extends JFrame {
    private JLabel lblEingabe;
    private JLabel lblKuenstlername;
    private JLabel lblDatum;
    private JLabel lblGenre;
    private JLabel lblKartenpreis;
    private JLabel lblBarrierefreiEingabe;
    private JTextField tfKuenstlername;
    private JTextField tfTag;
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
    private JTextField tfJahr;
    private JTextField tfMonat;

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

    private void addNewKonzert(){
        String kuenstlerEingabe = tfKuenstlername.getText().toString();

        String genreEingabe = comboGenre.getSelectedItem().toString();
        double kartenpreisEingabe = Double.parseDouble(tfPreis.getText().toString());
        boolean barrierefreiEingabe = checkBarrierefreiEingabe.isSelected();

        String tagEingabe = tfTag.getText().toString();
        String monatEingabe = tfMonat.getText().toString();
        String jahrEingabe = tfJahr.getText().toString();
        int tag = Integer.parseInt(tagEingabe);
        int monat = Integer.parseInt(monatEingabe);
        int jahr = Integer.parseInt(jahrEingabe);

        String uhrzeitEingabe = tfUhrzeit.getText().toString();
                                        //falls länger als 5 Zeichen Exception werfen!!

        String stundeEingabe = uhrzeitEingabe.substring(0,2);
        String minuteEingabe = uhrzeitEingabe.substring(3);
        int stunde = Integer.parseInt(stundeEingabe);
        int minute = Integer.parseInt(minuteEingabe);

        LocalDateTime ldtNeu = LocalDateTime.of(jahr,monat,tag,stunde,minute);

        Konzert kNeu = new Konzert(kuenstlerEingabe,ldtNeu,genreEingabe,kartenpreisEingabe,barrierefreiEingabe);
        konzertliste.add(kNeu);
    }

    private void alleAusgeben(){
        taAusgabe.setText(" ");
        for (Konzert ele : konzertliste){
            taAusgabe.append("\n" + ele.getKuenstlername() +  "\t" + ele.getGenre() +  "\t" + ele.getKartenpreis() +  "\t" + ele.getDatum() +  "\t" + ele.isBarrierefrei());
        }
    }
}
