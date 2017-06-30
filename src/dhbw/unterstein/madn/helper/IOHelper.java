package dhbw.unterstein.madn.helper;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class IOHelper {

  public static String readString() {
    String eingabe = new String();
    try {
      BufferedReader i = new BufferedReader(new InputStreamReader(System.in));
      eingabe = i.readLine();
    } catch (IOException e) {
      e.printStackTrace();
    }
    return eingabe;
  }
}
