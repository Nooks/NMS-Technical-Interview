/**
 * @author Nicholas King
 * @see org.yaml.snakeyaml.Yaml, java.io.InputStream, java.util.*
 * @since Java 1.8
 */

import org.yaml.snakeyaml.Yaml;

import java.io.InputStream;
import java.util.*;

public class Main {
    public static ArrayList<String> OIDPrefixes = new ArrayList<>();

    /*
     * The main method handles the running of program and deals with user input and
     * the results
     */
    public static void main(String[] args) {
        Main get = new Main();
        get.readFile();


        Scanner input = new Scanner(System.in);

        while (true) {
            String inputOID = input.nextLine();

            if (inputOID.equals("exit")) {
                break;
            }
            System.out.println(String.format("%s: %s", inputOID, checkValidOID(inputOID)));

        }
        input.close();
    }

    /**
     * This method uses a Library called SnakeYAML which parses and loads the YAML file into a map
     * and by using the key of the map it adds it to the ArrayList.
     */
    public static void readFile() {
        OIDPrefixes.clear();
        Yaml yaml = new Yaml();

        InputStream inputStream = Main.class
                .getClassLoader()
                .getResourceAsStream("snmp.yaml");
        Map<String, Object> obj = (Map<String, Object>) yaml.load(inputStream);
        OIDPrefixes.addAll((List<String>) obj.get("trap-type-oid-prefix"));
    }

    /**
     * This method checks to the ArrayList and when it finds a OID that starts with pref or oid it
     * returns true, if not return false.
     *
     * @param oid this is the passed variable that's used to check if it matches any of the OIDs
     *            in the ArrayList
     * @return This method will return true or false when the given OID is filtered.
     */
    public static boolean checkValidOID(String oid) {
        for (String pref : OIDPrefixes) {
            if (oid.startsWith(pref) || pref.startsWith(oid)) {
                return true;
            }
        }
        return false;
    }

}
