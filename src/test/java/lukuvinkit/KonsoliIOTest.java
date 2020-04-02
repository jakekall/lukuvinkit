package lukuvinkit;

import lukuvinkit.io.KonsoliIO;
import org.junit.Before;
import org.junit.Test;

import java.util.Scanner;

import static org.junit.Assert.*;

public class KonsoliIOTest {
    Scanner scanner;
    KonsoliIO io;

    @Before
    public void setup() {
        scanner = new Scanner("1");
        io = new KonsoliIO(scanner);
    }

    @Test
    public void konsoliReturnsRightCommand() {
        assertEquals("1", io.nextCommand());
    }

}
