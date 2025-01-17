package org.example;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class Konzert {
    //Attribute
    private String kuenstlername;
    private LocalDateTime datum;
    private String genre;
    private double kartenpreis;
    private boolean barrierefrei;


    //Konstruktor
    public Konzert(String kuenstlername, LocalDateTime datum, String genre, double kartenpreis, boolean barrierefrei) {
        this.kuenstlername = kuenstlername;
        this.datum = datum;
        this.genre = genre;
        this.kartenpreis = kartenpreis;
        this.barrierefrei = barrierefrei;
    }


    //Getter-Methoden
    public String getKuenstlername() {
        return kuenstlername;
    }

    public LocalDateTime getDatum() {
        return datum;
    }

    public String getGenre() {
        return genre;
    }

    public double getKartenpreis() {
        return kartenpreis;
    }

    public boolean isBarrierefrei() {
        return barrierefrei;
    }

    public String getFormatiertesDatum() {
        //Format des Datums für die Anzeige in der JTextArea anpassen

        DateTimeFormatter formatvorlage = DateTimeFormatter.ofPattern("dd.MM.yyyy, HH:mm"); //Formatvorlage erstellt
        String datumFormatiert = datum.format(formatvorlage); //Umwandeln des Datums des Objekts in Format der Vorlage
        return datumFormatiert;
    }

    public boolean pruefeUnterHundert(){
        boolean unterHundert = false;
        if (kartenpreis < 100){
            unterHundert = true;
        }
        return unterHundert;
    }
}
