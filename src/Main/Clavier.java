package Main;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;

public final class Clavier
{
  public static boolean readBoolean()
  {
    return Boolean.getBoolean(readLine());
  }
  
  public static char readChar()
  {
    return readLine().charAt(0);
  }
  
  public static int readInt()
  {
    int i;
    try
    {
      i = new Integer(readLine()).intValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      System.err.println("Rentrez un int");
      i = readInt();
    }
    return i;
  }
  
  public static double readDouble()
  {
    double d;
    try
    {
      d = new Double(readLine()).doubleValue();
    }
    catch (NumberFormatException localNumberFormatException)
    {
      System.err.println("Rentrez un double");
      d = readDouble();
    }
    return d;
  }
  
  public static String readString()
  {
    return readLine();
  }
  
  private static String readLine()
  {
    String str = "";
    try
    {
      BufferedReader localBufferedReader = new BufferedReader(new InputStreamReader(System.in));
      str = localBufferedReader.readLine();
    }
    catch (IOException localIOException)
    {
      System.err.println("Erreur entree/sortie");
      System.exit(0);
    }
    return str;
  }
}
