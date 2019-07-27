package pl.bpiotrowski.quiz;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

public class Zadanie {
    @Getter @Setter String pytanie;
    @Getter List<String> odpowiedzi;

    Zadanie(int initialCapacity) {
        this.odpowiedzi = new ArrayList<String>(initialCapacity);
    }

    void dodajOdpowiedz(String odpowiedz) {
        odpowiedzi.add(odpowiedz);
    }
}
