import java.util.Scanner;

public class KonsoliIO implements IO{
    private Scanner scanner;

    public KonsoliIO() {
        scanner = new Scanner(System.in);
    }

    @Override
    public void print(String s) {
        System.out.println(s);
    }

    @Override
    public String nextCommand() {
        return scanner.nextLine();
    }
}
