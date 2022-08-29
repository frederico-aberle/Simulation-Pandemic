import java.util.Scanner;
import terminal.VTerm;

public class Pandemie
{
    static int[][] population;
    static VTerm console;
    
    static {
        Pandemie.population = new int[101][101];
        Pandemie.console = VTerm.getInstance(25, 80, "Pandemie", 12);
    }
    
    public static void main(final String[] args) {
        final Scanner eingabe = new Scanner(System.in);
        System.out.println("Wie gro\u00df soll die Simulation werden?");
        final int a = eingabe.nextInt() + 1;
        final int b = eingabe.nextInt() + 1;
        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < b; ++j) {
                Pandemie.population[i][j] = 0;
            }
        }
        print(a, b);
        Pandemie.console.delay(500);
        init(a, b);
        System.out.println("Wie lange soll die Simulation dauern?");
        final int d = eingabe.nextInt() + 1;
        int counter_erkrankt = 3;
        int counter_geheilt = 0;
        for (int tage = 0; tage < d; ++tage) {
            for (int i = 1; i < a - 1; ++i) {
                for (int j = 1; j < b - 1; ++j) {
                    final int[] values = ansteckung(i, j, d, counter_erkrankt, counter_geheilt);
                    counter_erkrankt = values[0];
                    counter_geheilt = values[1];
                }
            }
            System.out.println("Simulationsschritt: " + tage);
            System.out.println("Noch nicht erkrankte Personen: " + ((a - 1) * (b - 1) - counter_erkrankt));
            System.out.println("Kranke Personen: " + (counter_erkrankt - counter_geheilt));
            System.out.println("Geheilte Personen: " + counter_geheilt);
            Pandemie.console.clearScreen();
            Pandemie.console.println((CharSequence)("Tag " + tage));
            print(a, b);
            Pandemie.console.delay(500);
        }
        Pandemie.console.readyToExit(true);
    }
    
    public static void print(final int a, final int b) {
        for (int i = 0; i < a; ++i) {
            for (int j = 0; j < b; ++j) {
                if (Pandemie.population[i][j] > 0 && Pandemie.population[i][j] < 8) {
                    Pandemie.console.print((CharSequence)new StringBuilder().append(Pandemie.population[i][j]).toString());
                }
                else {
                    Pandemie.console.print(' ');
                }
            }
            Pandemie.console.println();
        }
    }
    
    public static int[] ansteckung(final int x, final int y, final int d, int counter_erkrankt, int counter_geheilt) {
        if (Pandemie.population[x][y] == 0) {
            final boolean[] counter_infectious_list = { infectioes(x - 1, y), infectioes(x, y - 1), infectioes(x + 1, y), infectioes(x, y + 1) };
            double counter_infectious = 0.0;
            for (int k = 0; k < 4; ++k) {
                if (counter_infectious_list[k]) {
                    ++counter_infectious;
                }
            }
            if (infectioes(x - 1, y) || infectioes(x, y - 1) || infectioes(x + 1, y) || infectioes(x, y + 1)) {
                final int zufall = (int)(Math.random() * 0.25 * d * (1.0 / counter_infectious));
                if (zufall == 0) {
                    Pandemie.population[x][y] = 1;
                    ++counter_erkrankt;
                }
            }
        }
        else if (Pandemie.population[x][y] < 8) {
            if (Pandemie.population[x][y] == 7) {
                ++counter_geheilt;
            }
            final int[] array = Pandemie.population[x];
            ++array[y];
        }
        final int[] values = { counter_erkrankt, counter_geheilt };
        return values;
    }
    
    public static void init(final int a, final int b) {
        for (int i = 0; i < 3; ++i) {
            final int x = (int)(Math.random() * (a - 1) + 1.0);
            final int y = (int)(Math.random() * (b - 1) + 1.0);
            Pandemie.population[x][y] = 1;
        }
    }
    
    public static boolean infectioes(final int x, final int y) {
        boolean ansteckend = false;
        if (Pandemie.population[x][y] > 0 && Pandemie.population[x][y] < 8) {
            ansteckend = true;
        }
        return ansteckend;
    }
}
