public class Main {
    public static void main(String[] args) {
        Ui ui = new Ui(new KonsoliIO(), new LukuvinkkienKasittely());
        ui.startUi();
    }
}

