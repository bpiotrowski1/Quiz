package pl.bpiotrowski.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        String plikDoWczytania = "src/main/resources/";
        Scanner scanner = new Scanner(System.in);
        List<Zadanie> zadania = new ArrayList<Zadanie>();
        File plik;
        int liczbaPunktow = 0;

        System.out.print("Podaj kategorie pytań: ");
        String kategoria = scanner.nextLine();
        if(kategoria.equalsIgnoreCase("wszystkie")) {
            File[] files = new File("src/main/resources").listFiles();
            for (File file: files) {
                zadania.addAll(wczytaj(file));
            }
        } else {
            plikDoWczytania += kategoria + ".txt";
            plik = new File(plikDoWczytania);
            zadania = wczytaj(plik);
        }

        for (int i = 0; i < 10; i++) {
            liczbaPunktow += zadajPytanie(zadania);
        }
        System.out.println("Udzieliles " + liczbaPunktow + " poprawne odpowiedzi!");
    }

    private static List<Zadanie> wczytaj(File plik) throws FileNotFoundException {
        List<Zadanie> zadania = new ArrayList<Zadanie>();
        Scanner scanner = new Scanner(plik);

        while (scanner.hasNextLine()) {
            String pytanie = scanner.nextLine();
            int liczbaPytan = Integer.parseInt(scanner.nextLine());
            Zadanie zadanie = new Zadanie(liczbaPytan);
            zadanie.setPytanie(pytanie);
            for (int i = 0; i < liczbaPytan; i++) {
                zadanie.dodajOdpowiedz(scanner.nextLine());
            }
            zadania.add(zadanie);
        }

        return zadania;
    }

    private static int zadajPytanie(List<Zadanie> zadania) {
        Random rand = new Random();
        Scanner scanner = new Scanner(System.in);
        Zadanie zadanie = zadania.get(rand.nextInt(zadania.size()));
        String dobraOdpowiedz = zadanie.getOdpowiedzi().get(0);

        System.out.println(zadanie.getPytanie());
        wypiszOdpowiedzi(zadanie);
        System.out.print("Twoja odpowiedz: ");
        String odpowiedz = scanner.nextLine();

        if (dobraOdpowiedz.equalsIgnoreCase(odpowiedz)) {
            System.out.println("Dobra odpowiedz!\n");
            return 1;
        } else {
            System.out.println("Zla odpowiedz!\n");
            return 0;
        }
    }

    private static void wypiszOdpowiedzi(Zadanie zadanie) {
        Collections.shuffle(zadanie.getOdpowiedzi());
        for (String odpowiedz : zadanie.getOdpowiedzi()) {
            System.out.println("    " + odpowiedz);
        }
    }

    /*
    private static void wypiszOdpowiedzi(Zadanie zadanie) {
        int[] tab = new int[zadanie.getOdpowiedzi().size()];
        for (int i = 0; i < tab.length; i++) {
            tab[i] = 0;
        }
        Random rand = new Random();
        while(!czyWypisanoWszystkieOdpowiedzi(tab)) {
            for (int i = 0; i < tab.length; i++) {
                if(tab[i] == 0 && rand.nextInt(2) == 0) {
                    tab[i] = 1;
                    System.out.println("     " + zadanie.getOdpowiedzi().get(i));
                }
            }
        }
    }

    private static boolean czyWypisanoWszystkieOdpowiedzi(int[] tab) {
        for (final int value : tab) {
            if (value == 0) {
                return false;
            }
        }
        return true;
    }
         */
}
