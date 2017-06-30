package dhbw.unterstein.madn.helper;

import dhbw.unterstein.madn.spiel.Figur;
import dhbw.unterstein.madn.spiel.Spiel;
import dhbw.unterstein.madn.spiel.SpielBrett;
import dhbw.unterstein.madn.spiel.Spieler;

import java.io.*;
import java.util.ArrayList;
import java.util.List;

public class SpielSerializer {

  /**
   * Serializiert das gegebene Spiel in die gegebene Datei mit folgendem Format:
   * <pre>
   *   Spieler;Peter
   *   Figuren;I1,E1,E3,S36
   *   AmZug;false
   *   Spieler;Klaus
   *   Figuren;I0,E3,S4,S24
   *   AmZug;true
   * <pre>
   *
   * @param spiel
   * @param file
   */
  public static void serialize(Spiel spiel, File file) {
    try (PrintWriter writer = new PrintWriter(new BufferedWriter(new FileWriter(file)))) {
      for (Spieler spieler : spiel.getSpieler()) {
        writer.println(String.format("Spieler;%s", spieler.getName()));
        writer.println(String.format("Figuren;%s", filterPositionen(spieler, spiel.getSpielBrett())));
        writer.println(String.format("AmZug;%s", spiel.getAktuellerSpieler().equals(spieler) ? "true" : "false"));
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
  }

  private static String filterPositionen(Spieler spieler, SpielBrett spielBrett) {
    return spieler.getFiguren().stream()
        .map(figur -> {
              switch (figur.getAktuellesFeld().getArt()) {
                case SPIEL:
                  return "S" + spielBrett.getSpielFeld().indexOf(figur);
                case END:
                  return "E" + spielBrett.getEndFeld(spieler).indexOf(figur);
                case INITIAL:
                  return "I" + spielBrett.getInitialFeld(spieler).indexOf(figur);
                default:
                  throw new IllegalArgumentException("Figurposition ungültig");
                  //exception
              }
            }
        ).reduce((a, b) -> a + "," + b).orElse("");
  }

  public static Spiel deserialize(File file) {
    List<Spieler> spieler = new ArrayList<>();
    try (BufferedReader reader = new BufferedReader(new FileReader(file))) {
      String line = null;
      Spieler aktuellerSpieler = null;
      while ((line = reader.readLine()) != null) {
        if (line.startsWith("Spieler;")) {
          aktuellerSpieler = new Spieler(line.replace("Spieler;", ""));
          spieler.add(aktuellerSpieler);
        }
        if (line.startsWith("Initial;")) {
          String[] figuren = line.replace("Initial;", "").split(";");
          int counter = 0;
          for (Figur figur : aktuellerSpieler.getFiguren()) {
            if (figuren[counter].startsWith("S")) {
              // TODO
            } else if (figuren[counter].startsWith("E")) {
              // TODO
            } else if (figuren[counter].startsWith("O")) {
              // TODO
            } else {
              throw new IllegalArgumentException("Figurposition ungültig");
            }
            counter++;
          }
        }
      }
    } catch (Exception e) {
      e.printStackTrace();
    }
    Spiel result = new Spiel(spieler);
    return result;
  }
}
