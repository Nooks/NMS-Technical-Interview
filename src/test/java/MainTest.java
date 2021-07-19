/*
 * Checks to see if file given is true and tests to see if OIDs are filtered properly
 */

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class MainTest {
    /**
     * Read the file before running tests
     */
    @BeforeAll
    static void setUpBeforeClass() {
        Main.readFile();
    }

    /**
     * test the given prefixes to check if it is true or false
     */
    @Test
    void testValidOID() {
        assertTrue(Main.checkValidOID(".1.3.6.1.4.1.9.9.117.2.0.1"));
        assertTrue(Main.checkValidOID(".1.3.6.1.4.1.9.9.117"));
        assertFalse(Main.checkValidOID(".1.3.6.1.4.1.9.9.118.2.0.1"));
        assertFalse(Main.checkValidOID("000000.1.3.6.1.4.1.9.9.117.2.0.1"));
    }
}
