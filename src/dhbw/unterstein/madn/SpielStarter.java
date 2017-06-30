package dhbw.unterstein.madn;

import dhbw.unterstein.madn.helper.IOHelper;
import dhbw.unterstein.madn.spiel.Spiel;
import dhbw.unterstein.madn.spiel.Spieler;

import java.util.ArrayList;
import java.util.List;

public class SpielStarter {
  public static void main(String[] args) {
    new Spiel(spielerEingabe());
  }

  private static List<Spieler> spielerEingabe() {
    List<Spieler> result = new ArrayList<>();
    int counter = 0;
    while (counter < 4) {
      System.out.println("Bitte Namen eingeben:");
      String eingabe = IOHelper.readString();
      if ("ende".equals(eingabe)) {
        return result;
      }
      result.add(new Spieler(eingabe));
      counter++;
    }
    return result;
  }
}
