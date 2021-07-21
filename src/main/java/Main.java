/**
 * @author Nicholas King
 * @see org.yaml.snakeyaml.Yaml, java.io.InputStream, java.util.*
 * @since Java 1.8
 */

import org.yaml.snakeyaml.Yaml;
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

        OIDPrefixes = (ArrayList<String>) ((Map<String, Object>) yaml.load(
                Main.class.getClassLoader().getResourceAsStream("snmp.yaml")
        )).get("trap-type-oid-prefix");
        Collections.sort(OIDPrefixes);

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
        return Collections.binarySearch(OIDPrefixes, oid, new CustomComp()) >= -1;
    }

    private static class CustomComp implements Comparator<String> {

        @Override
        public int compare(String o1, String o2) {
            if (o1.startsWith(o2) || o2.startsWith(o1)) {
                System.out.println(true);
                return 0;
            }
            return o1.compareTo(o2);
        }
    }
}
