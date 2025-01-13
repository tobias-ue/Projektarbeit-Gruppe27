package org.example;

import javax.swing.*;
import java.awt.*;
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

    private ArrayList<Konzert> konzertliste = new ArrayList<Konzert>();

    public KonzertfinderGUI() throws HeadlessException {
        setTitle("Konzertsuche");
        setContentPane(jpPanel);
        setSize(1200,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

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

}
