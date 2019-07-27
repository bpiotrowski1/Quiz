package pl.bpiotrowski;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Zadanie {
    @Getter @Setter String pytanie;
    @Getter List<String> odpowiedzi;

    Zadanie() {
        this.odpowiedzi = new ArrayList<String>();
    }

    void dodajOdpowiedz(String odpowiedz) {
        odpowiedzi.add(odpowiedz);
    }
}
