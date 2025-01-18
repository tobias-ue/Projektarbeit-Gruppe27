package org.example;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.*;

class KonzertTest {

    @org.junit.jupiter.api.Test
     void pruefeUnterHundert() {
        //Test funktioniert, wenn Kartenpreis >= 100 ist (assertFalse)
        LocalDateTime ldtTest1 = LocalDateTime.of(2025,11,04,16,30);
        Konzert test1 = new Konzert("Taylor Swift",ldtTest1,"Pop",103.0,true);
        boolean pruefe1 = test1.pruefeUnterHundert();
        assertFalse(pruefe1);

        //Test funktioniert, wenn Kartenpreis < 100 ist (assertTrue)
        LocalDateTime ldtTest2 = LocalDateTime.of(2025,12,30,19,30);
        Konzert test2 = new Konzert("David Guetta",ldtTest2,"Dance",99.0,false);
        boolean pruefe2 = test2.pruefeUnterHundert();
        assertTrue(pruefe2);
    }
}