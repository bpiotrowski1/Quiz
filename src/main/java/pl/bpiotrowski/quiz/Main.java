package pl.bpiotrowski.quiz;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.*;

public class Main {
    public static void main(String[] args) throws FileNotFoundException {
        int liczbaPunktow = 0;
        List<Zadanie> zadania = wybierzKategorie();
        Map<String, Boolean> kolaRatunkowe = new HashMap<>();
        kolaRatunkowe.put("Telefon do przyjaciela", true);

        for (int i = 0; i < 10; i++) {
            wypiszKolaRatunkowe(kolaRatunkowe);
            liczbaPunktow += zadajPytanie(zadania);
        }
        System.out.println("Udzieliles " + liczbaPunktow + " poprawne odpowiedzi!");
    }

    private static void wypiszKolaRatunkowe(Map<String, Boolean> kolaRatunkowe) {
        kolaRatunkowe.forEach((key, value) -> System.out.println(key + ": " + value));
    }

    private static List<Zadanie> wybierzKategorie() throws FileNotFoundException {
        List<Zadanie> zadania = new ArrayList<>();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Dostepne kategorie:");
        File[] files = new File("src/main/resources").listFiles();
        for (int i = 0; i < files.length; i++) {
            System.out.println(i + ") " + files[i].getName().substring(0, files[i].getName().lastIndexOf(".")));
        }
        System.out.println("16) Wszystkie\n");

        System.out.print("Podaj kategorie pytań: ");
        int kategoria = scanner.nextInt();
        if (kategoria == 16) {
            for (File file : files) {
                zadania.addAll(wczytaj(file));
            }
        } else {
            zadania = wczytaj(files[kategoria]);
        }

        return zadania;
    }

    private static List<Zadanie> wczytaj(File plik) throws FileNotFoundException {
        List<Zadanie> zadania = new ArrayList<>();
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
        int odpowiedz = scanner.nextInt();

        if (dobraOdpowiedz.equalsIgnoreCase(zadanie.getOdpowiedzi().get(odpowiedz))) {
            System.out.println("Dobra odpowiedz!\n");
            return 1;
        } else {
            System.out.println("Zla odpowiedz!\n");
            return 0;
        }
    }

    private static void wypiszOdpowiedzi(Zadanie zadanie) {
        Collections.shuffle(zadanie.getOdpowiedzi());
        //po wymieszaniu, gdy zapyta drugi raz o to samo zadanie bedzie problem z poprawna odpowiedzia
        List<String> odpowiedzi = zadanie.getOdpowiedzi();
        for (int i = 0; i < odpowiedzi.size(); i++) {
            System.out.println("   " + i + "] " + odpowiedzi.get(i));
        }
    }

    /*
            COLLECTIONS.SHUFFLE
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
