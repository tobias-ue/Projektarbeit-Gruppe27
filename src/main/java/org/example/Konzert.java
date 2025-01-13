package org.example;

import java.time.LocalDateTime;

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


}