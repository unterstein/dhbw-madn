package dhbw.unterstein.madn.spiel;

import dhbw.unterstein.madn.helper.IOHelper;

import java.util.ArrayList;
import java.util.List;

public class Spieler {
  private final String name;
  private final List<Figur> figuren;

  public Spieler(String name) {
    this.name = name;
    this.figuren = new ArrayList<>();
    for (int i = 0; i < 4; i++) {
      figuren.add(new Figur());
    }
  }

  public Figur waehleFigur() {
    System.out.println("Bitte wähle eine Figur:");
    String eingabe = IOHelper.readString();
    if ("weiter".equals(eingabe)) {
      return null;
    }
    return figuren.get(Integer.valueOf(eingabe));
  }

  public boolean isFertig() {
    return figuren.stream().allMatch(figur -> figur.stehtAuf(FeldArt.END));
  }

  public String getName() {
    return name;
  }

  public List<Figur> getFiguren() {
    return figuren;
  }
}
