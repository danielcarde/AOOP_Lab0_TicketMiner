import java.util.*;
import java.io.*;

public class TicketMiner {
    public static void main(String[] args) {
        
        clearScreen();

        Scanner input = new Scanner(System.in);
        File fileName = new File("EventListPA0.csv");
        int amountLines = amountLinesCounter(fileName);
        Sport [] sportEvents = fileToArray(fileName,amountLines);

        boolean exit = false;

        while(!exit){
            printMainMenu();
            String choice = input.nextLine();
            switch(choice){
                case "1":
                    clearScreen();
                    sportsChosen(sportEvents);
                    break;
                case "2":
                    clearScreen();
                    System.out.println("Exiting...");
                    exit = true;
                    break;
                default:
                    clearScreen();
                    System.out.println("Error: Invalid Input");
					System.out.println();
            }
        }

    }
    public static int amountLinesCounter(File fileName){//gets the amount of lines in the file in order to create a big enough array even with extra space due to headers
            int amountLines = 0;
            try(Scanner fileReader = new Scanner(fileName);){
                while(fileReader.hasNextLine()){
                    amountLines++;
                    fileReader.nextLine();
                }
            }
            catch (Exception e){
                e.printStackTrace();
            }
            return amountLines;
        }
    
    public static Sport [] fileToArray(File fileName,int amountLines){//coverts the file into a Sport array
        Sport [] sportEvents = new Sport[amountLines];
        try(BufferedReader br = new BufferedReader(new FileReader(fileName));){
            String currLine = "";
            int i = 0;
            while((currLine = br.readLine()) != null){
                if(currLine.isBlank() || !Character.isDigit(currLine.charAt(0))){// i want to make sure that the line actually has event details and not a header or empty
                    continue;
                }
                
                String [] details = currLine.split(","); //each event detail is seperated by a comma allowing for easy seperation
                
                if(details.length < 10){//if there is not enough details then skip
                    continue;
                }

                int id = Integer.parseInt(details[0]);
                String eventType = details[1].trim();
                String name = details[2].trim();
                String date = details[3].trim();
                String time = details[4].trim();
                double VIPPrice = Double.parseDouble(details[5].trim());
                double goldPrice = Double.parseDouble(details[6].trim());
                double silverPrice = Double.parseDouble(details[7].trim());
                double bronzePrice = Double.parseDouble(details[8].trim());
                double generalAdPrice = Double.parseDouble(details[9].trim());

                if(eventType.equals("Sport")){
                    sportEvents[i]=new Sport(id,name,date,time,VIPPrice,goldPrice,silverPrice,bronzePrice,generalAdPrice);
                }else{
                    System.out.println("something broke");//i have this here in order to allow for updates just in case there is different events, as well as if this runs its an indicator of an error.
                }
                i++;
            }
            return sportEvents;
        }
        catch(IOException e){
            e.printStackTrace();
        }
        return sportEvents;
    }

    public static void printMainMenu(){// method in order to keep things cleaner in the main method
        System.out.println("---------------Ticket Miner---------------");
        System.out.println("               1) Sports");
        System.out.println("               2) Exit");
        System.out.println("------------------------------------------");
    }

    public static void clearScreen(){//common command i used in order to make the terminal more readable
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

    public static void sportsChosen(Sport [] sportEvents){
        Scanner input = new Scanner(System.in);
        
        boolean exit = false;

        while(!exit){
            scMenu();
            String choice = input.nextLine();
            switch(choice){
                case "1":
                    clearScreen();
                    boolean isDigit = false;
                    String idInput ="";
                    int idInputInt = 0;
                    while(!isDigit){//this while loop ensures a correct input is put into ID to prevent an exception
                        System.out.print("Enter ID: ");
                        idInput = input.nextLine();
                        try{
                            idInputInt = Integer.parseInt(idInput);
                            isDigit = true;
                        } catch (Exception e){
                            clearScreen();
                            System.out.println("Error: Not a Valid Id!");
                        }
                    }
                    int idx = searchByID(sportEvents, idInputInt);
                    if (idx == -1) {//if an id is inputted that is not within the array it will give a -1
                        clearScreen();
                        System.out.println("Event ID " + idInput + " not found.");
                    } else {
                        Sport chosenSport = sportEvents[idx];
                        correctID(sportEvents, chosenSport);//if a valid ID is chosen you are moved on to the next menu
                    }
                    break;
                case "2":
                    clearScreen();
                    System.out.println("Returning to Main Menu...");
                    exit = true;
                    break;
                default:
                    clearScreen();
                    System.out.println("Error: Invalid Input");
					System.out.println();
            }
        }
    }

    public static void scMenu(){
            System.out.println("----------------Event Menu----------------");
            System.out.println("          1) Search Event by ID");
            System.out.println("          2) Exit");
            System.out.println("------------------------------------------");
        }
    
    public static void correctID(Sport [] sportEvents, Sport chosenSport){//menu if correct id is chosen
        Scanner input = new Scanner(System.in);
        
        boolean exit = false;

        while(!exit){ 
            correctIDMenu();
            System.out.print("Choose an Option: ");
            String choice = input.nextLine();
            switch (choice) {
                case "1":
                    System.out.println("Current ID: " + chosenSport.getId());
                    System.out.println();
                    System.out.print("Would you like to edit ID? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean isDigit = false;
                        int idInputInt = 0;
                        while(!isDigit){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New ID: ");
                            String idInput = input.nextLine();
                            try{
                                idInputInt = Integer.parseInt(idInput);//if it parses its an correct input
                                isDigit = true;
                            } catch (Exception e){
                                clearScreen();
                                System.out.println("Error: Not a Valid Id!");
                            }
                        }
                        int oldID = chosenSport.getId();
                        chosenSport.setId(idInputInt);
                        clearScreen();
                        System.out.println("ID updated returning to Details Menu");
                        logAction("Event ID " + oldID + " updated ID to " + idInputInt);
                    }else{
                        clearScreen();
                    }
                    break;

                case "2":
                    System.out.println("Current Name: " + chosenSport.getName());
                    System.out.println();
                    System.out.print("Would you like to edit name? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        System.out.print("Enter New name: ");
                        String oldName = chosenSport.getName();
                        String newName = input.nextLine();
                        chosenSport.setName(newName);
                        clearScreen();
                        System.out.println("Name updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated name from " + oldName + " to " + newName);
                    }else{
                        clearScreen();
                    }
                    break;

                case "3":
                    System.out.println("Current Date: " + chosenSport.getDate());
                    System.out.println();
                    System.out.print("Would you like to edit date? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        System.out.print("Enter New date: ");
                        String oldDate = chosenSport.getDate();
                        String newDate = input.nextLine();
                        chosenSport.setDate(newDate);
                        clearScreen();
                        System.out.println("Date updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated date from " + oldDate + " to " + newDate);
                    }else{
                        clearScreen();
                    }
                    break;

                case "4":
                    System.out.println("Current Time: " + chosenSport.getTime());
                    System.out.println();
                    System.out.print("Would you like to edit time? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        System.out.print("Enter New time: ");
                        String oldTime = chosenSport.getTime();
                        String newTime = input.nextLine();
                        chosenSport.setTime(newTime);
                        clearScreen();
                        System.out.println("Time updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated time from " + oldTime + " to " + newTime);
                    }else{
                        clearScreen();
                    }
                    break;
                case "5":
                    System.out.println("Current VIP Price: " + chosenSport.getVIPPrice());
                    System.out.println();
                    System.out.print("Would you like to edit VIP Price? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean validDouble = false;
                        double newVIPPrice = 0;
                        while(!validDouble){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New VIP Price: ");
                            String priceInput = input.nextLine();
                            try{
                                newVIPPrice = Double.parseDouble(priceInput);//if it parses its an correct input
                                validDouble = true;
                            } catch(Exception e){
                                clearScreen();
                                System.out.println("Error: Not a valid VIP Price!");
                            }
                        }
                        double oldVIPPrice = chosenSport.getVIPPrice();
                        chosenSport.setVIPPrice(newVIPPrice);
                        clearScreen();
                        System.out.println("VIP Price updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated VIP Price from " + oldVIPPrice + " to " + newVIPPrice);
                    }else{
                        clearScreen();
                    }
                    break;

                case "6":
                    System.out.println("Current Gold Price: " + chosenSport.getGoldPrice());
                    System.out.println();
                    System.out.print("Would you like to edit Gold Price? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean validDouble = false;
                        double newGoldPrice = 0;
                        while(!validDouble){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New Gold Price: ");
                            String priceInput = input.nextLine();
                            try{
                                newGoldPrice = Double.parseDouble(priceInput);//if it parses its an correct input
                                validDouble = true;
                            } catch(Exception e){
                                clearScreen();
                                System.out.println("Error: Not a valid Gold Price!");
                            }
                        }
                        double oldGoldPrice = chosenSport.getGoldPrice();
                        chosenSport.setGoldPrice(newGoldPrice);
                        clearScreen();
                        System.out.println("Gold Price updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated Gold Price from " + oldGoldPrice + " to " + newGoldPrice);
                    }else{
                        clearScreen();
                    }
                    break;

                case "7":
                    System.out.println("Current Silver Price: " + chosenSport.getSilverPrice());
                    System.out.println();
                    System.out.print("Would you like to edit Silver Price? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean validDouble = false;
                        double newSilverPrice = 0;
                        while(!validDouble){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New Silver Price: ");
                            String priceInput = input.nextLine();
                            try{
                                newSilverPrice = Double.parseDouble(priceInput);//if it parses its an correct input
                                validDouble = true;
                            } catch(Exception e){
                                clearScreen();
                                System.out.println("Error: Not a valid Silver Price!");
                            }
                        }
                        double oldSilverPrice = chosenSport.getSilverPrice();
                        chosenSport.setSilverPrice(newSilverPrice);
                        clearScreen();
                        System.out.println("Silver Price updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated Silver Price from " + oldSilverPrice + " to " + newSilverPrice);
                    }else{
                        clearScreen();
                    }
                    break;

                case "8":
                    System.out.println("Current Bronze Price: " + chosenSport.getBronzePrice());
                    System.out.println();
                    System.out.print("Would you like to edit Bronze Price? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean validDouble = false;
                        double newBronzePrice = 0;
                        while(!validDouble){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New Bronze Price: ");
                            String priceInput = input.nextLine();
                            try{
                                newBronzePrice = Double.parseDouble(priceInput);//if it parses its an correct input
                                validDouble = true;
                            } catch(Exception e){
                                clearScreen();
                                System.out.println("Error: Not a valid Bronze Price!");
                            }
                        }
                        double oldBronzePrice = chosenSport.getBronzePrice();
                        chosenSport.setBronzePrice(newBronzePrice);
                        clearScreen();
                        System.out.println("Bronze Price updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated Bronze Price from " + oldBronzePrice + " to " + newBronzePrice);
                    }else{
                        clearScreen();
                    }
                    break;

                case "9":
                    System.out.println("Current General Admission Price: " + chosenSport.getGeneralAdPrice());
                    System.out.println();
                    System.out.print("Would you like to edit General Admission Price? (y or n) ");
                    if(input.nextLine().equalsIgnoreCase("y")){
                        boolean validDouble = false;
                        double newGenAdPrice = 0;
                        while(!validDouble){//this while loop ensures there is not a user inflicted exception by putting in incorrect inputs
                            System.out.print("Enter New General Admission Price: ");
                            String priceInput = input.nextLine();
                            try{
                                newGenAdPrice = Double.parseDouble(priceInput);//if it parses its an correct input
                                validDouble = true;
                            } catch(Exception e){
                                clearScreen();
                                System.out.println("Error: Not a valid General Admission Price!");
                            }
                        }
                        double oldGenAdPrice = chosenSport.getGeneralAdPrice();
                        chosenSport.setGeneralAdPrice(newGenAdPrice);
                        clearScreen();
                        System.out.println("General Admission Price updated returning to Details Menu");
                        logAction("Event ID " + chosenSport.getId() + " updated General Admission Price from " + oldGenAdPrice + " to " + newGenAdPrice);
                    }else{
                        clearScreen();
                    }
                    break;
                case "10":
                    chosenSport.printInfo();
                    break;
                case "11":
                    clearScreen();
                    System.out.println("Returning to Event Menu...");
                    exit = true;
                    break;
                default:
                    clearScreen();
                    System.out.println("Error: Invalid Input");
                    System.out.println();
            }
        }
    }

    public static void correctIDMenu(){
        System.out.println("--------------Chose an Option--------------");
        System.out.println("          1) ID");
        System.out.println("          2) Name");
        System.out.println("          3) Date");
        System.out.println("          4) Time");
        System.out.println("          5) VIP Price");
        System.out.println("          6) Gold Price");
        System.out.println("          7) Silver Price");
        System.out.println("          8) Bronze Price");
        System.out.println("          9) General Admission Price");
        System.out.println("          10) View All Information");
        System.out.println("          11) Exit");
        System.out.println("-------------------------------------------");
    }
    
    public static int searchByID(Sport [] sportsEvents, int id){//checks if the id is within the array if it is not return a -1
        for(int i = 0; i < sportsEvents.length; i++){
            if(sportsEvents[i] != null && sportsEvents[i].getId()==id){
                return i;
            }
        }
        return -1;
    }

    public static void logAction(String message) {//write every change made by user by taking in string
        try (BufferedWriter writer = new BufferedWriter(new FileWriter("ActionLog.txt", true))) {
            writer.write(message);
            writer.newLine();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}