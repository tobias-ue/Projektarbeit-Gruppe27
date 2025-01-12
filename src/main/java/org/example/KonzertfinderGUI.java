package org.example;

import javax.swing.*;
import java.awt.*;

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

    public KonzertfinderGUI() throws HeadlessException {
        setTitle("Konzertsuche");
        setContentPane(jpPanel);
        setSize(1200,500);
        setVisible(true);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
    }

}
