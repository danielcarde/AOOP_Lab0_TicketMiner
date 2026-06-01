import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.StringReader;

public class originalFileToArray {
    public static void fileToArray(File fileName, int amountLines) {
    try {
        BufferedReader br = new BufferedReader(new FileReader(fileName));
        Sport[] sportEvents = new Sport[amountLines];

        for (int i = 0; i < sportEvents.length; i++) {
            String currLine = br.readLine();
            if (currLine == null || !Character.isDigit(currLine.charAt(0))) {
                continue;
            }

            BufferedReader brString = new BufferedReader(new StringReader(currLine));

            int id = 0;
            String eventType = "";
            String name = "";
            String date = "";
            String time = "";
            double VIPPrice = 0;
            double goldPrice = 0;
            double silverPrice = 0;
            double bronzePrice = 0;
            double generalAdPrice = 0;

            int j = 0;
            char stopChar = ',';

            while (j < 10) {
                int currInt;
                String currSelection = "";

                while ((currInt = brString.read()) != -1) {
                    char currChar = (char) currInt;
                    if (currChar == stopChar) {
                        break;
                    }
                    currSelection += currChar;
                }

                switch (j) {
                    case 0:
                        id = Integer.parseInt(currSelection);
                        break;
                    case 1:
                        eventType = currSelection;
                        break;
                    case 2:
                        name = currSelection;
                        break;
                    case 3:
                        date = currSelection;
                        break;
                    case 4:
                        time = currSelection;
                        break;
                    case 5:
                        VIPPrice = Double.parseDouble(currSelection);
                        break;
                    case 6:
                        goldPrice = Double.parseDouble(currSelection);
                        break;
                    case 7:
                        silverPrice = Double.parseDouble(currSelection);
                        break;
                    case 8:
                        bronzePrice = Double.parseDouble(currSelection);
                        break;
                    case 9:
                        generalAdPrice = Double.parseDouble(currSelection);
                        break;
                    default:
                        System.out.println("Something broke");
                        break;
                }

                j++;
            }

            if (eventType.equals("Sport")) {
                Sport tempSport = new Sport(id, name, date, time, VIPPrice, goldPrice, silverPrice, bronzePrice, generalAdPrice);
                sportEvents[i] = tempSport;
            } else {
                System.out.println("Something broke");
            }
        }
    } catch (IOException e) {
        e.printStackTrace();
    }
}

}
