package pl.bpiotrowski;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String plikDoWczytania = "src/main/resources/" + "Animals.txt";
        File plik = new File(plikDoWczytania);
        List<Zadanie> zadania = wczytaj(plik);

        System.out.println(zadania.get(0).getPytanie());
        System.out.println(zadania.get(0).getOdpowiedzi().get(0) + " " + zadania.get(0).getOdpowiedzi().get(1));
    }

    private static List<Zadanie> wczytaj(File plik) throws FileNotFoundException {
        List<Zadanie> zadania = new ArrayList<Zadanie>();
        Scanner scanner = new Scanner(plik);

        while (scanner.hasNextLine()) {
            Zadanie zadanie = new Zadanie();
            zadanie.setPytanie(scanner.nextLine());
            int liczbaPytan = Integer.parseInt(scanner.nextLine());
            for (int i = 0; i < liczbaPytan; i++) {
                zadanie.dodajOdpowiedz(scanner.nextLine());
            }
            zadania.add(zadanie);
        }

        return zadania;
    }
}
