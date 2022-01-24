package JavaExperienceTestPackage;

// Exceptions
import java.io.IOException;
import java.io.FileNotFoundException;

// File generation and management
import java.io.FileWriter;
import java.io.File;


// Name generation
import java.util.ArrayList;
import java.util.List;

// User input 
import java.util.Scanner;

import org.json.JSONArray;
import org.json.JSONObject;



//Query System UI & Interaction
public class QuerySystemUI {
    // Consistent UI Line breaks
    public String brk = "\n______________________________________________________\n\n";

    // @List of names
    public List < String > RandomNames = new ArrayList < String > ();

    // @List of people
    public List < String > RandomPeople = new ArrayList < String > ();

    public JSONArray personArray = new JSONArray();

    // Default location for random names file
    public String location = new File("Names.txt").getAbsolutePath();

    // Holds list of data set titles
    public List < String > ValueList = new ArrayList < String > ();

    // Initialization of total amount of titles
    public int valueCount = ValueList.size();
    
    // get queries
    SystemQueries Query = new SystemQueries();
   

    // ! GET METHODS!

   /**
    * 
    * @param amountToGenerate
    * @return Random names from file
    */
    public List < String > getRandomNames(int amountToGenerate) {
        String location = new File(".").getAbsolutePath();
        System.out.print("\n" + brk + " File saved to " + location + "\n");

        return RandomNames;
    }
    /**
     * 
     * @return user input
     */
    public String getUserInput() {
        @SuppressWarnings("resource")
        Scanner scanner = new Scanner(System.in);
        String userInput = scanner.nextLine();
        return userInput.strip().trim();

    }

    // ! SET METHODS !

    /**
     * @Adds values to value list + is later used in calculations and 
     * generation of people as objects.
     */
    public void setValues() {
        ValueList.add("id");
        ValueList.add("firstName");
        ValueList.add("lastName");
        ValueList.add("age");
        ValueList.add("location");
        valueCount = ValueList.size();
    }
    /**
     * 
     * @param amountToGenerate
     * @Creates people 
     * @From RandomNames List<> 
     * & Locations Enum
     */
    public void setRandomPeople(int amountToGenerate) {
        String location = new File("Names.txt").getAbsolutePath();
        Scanner s = null;
        try {
            s = new Scanner(new File(location));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
            writeText(" File Not Found Returning to Dataset Generator");
            dataGenerator(null);
        }
        // Checks for has next line + adds to randomNames array || closes scanner
        while (s.hasNext()) {
            RandomNames.add(s.next());
        }
        s.close();
        
        Integer incrementalId = 0;
        
        while (RandomPeople.size() < amountToGenerate * valueCount) {

            int randomNumberFName = (int) Math.floor(Math.random() * RandomNames.size());
            int randomNumberLName = (int) Math.floor(Math.random() * RandomNames.size());
            Integer randomNumberAge = (int) Math.floor(Math.random() * 100);
            
            incrementalId += 1;
            
            RandomPeople.add(incrementalId.toString());
            RandomPeople.add(RandomNames.get(randomNumberFName));
            RandomPeople.add(RandomNames.get(randomNumberLName));
            RandomPeople.add(randomNumberAge.toString());
            RandomPeople.add(generateRandomLocation((int) Math.floor(Math.random() * 5)));
        }
        if (amountToGenerate != 223836) {
            writeText("\n Enter the name you want to call you NDJSON file.\n no need to include the extension.");
            createNDJSON(getUserInput());
        }
    }
   
   /**
    * 
    * @param input
    * @return location from Location Enum based on the input param
    */
    public String generateRandomLocation(int input) {
        switch (input) {
            case 1:
                return Locations.New_Zealand.toString();
            case 2:
                return Locations.Australia.toString();
            case 3:
                return Locations.Russia.toString();
            case 4:
                return Locations.Switzerland.toString();
            default:
                return Locations.NA.toString();
        }
    }
   

    /**
     * 
     * @param text
     * @return text
     */
    public String writeText(String text) {
        System.out.println(text);
        return text;
    }
    /**
     * 
     * @param input
     * @Sent to view data screen function...
     * @OR goes through again.
     */
    public void viewDataScreen(String input) {

        writeText(brk + "Would you like to view created data?\n Y / N " + brk);

        String input1 = getUserInput();
        switch (input1) {
            case "y":
            case "yes":
            case "yup":
                selectFileScreen(null);
                break;
            case "n":
            case "no":
                break;
            default:
                writeText("You can not use this letter or number \n ...");
                viewDataScreen(getUserInput());
        }
    }
   
    /**
     * 
     * @param input
     * @param furtherString
     * @return ***
     * 
     * NOTE_ helps with testing to taking in the further string 
     * didn't know how to manipulate automatic user input
     * 
     * 1> Query Selected Data
     * 2> Change Selected Data
     * 3> Generate New Data
     * 4> Exit Application
     * 
     */
    
    public String menuScreen(String input, String furtherString) {
        // TODO Auto-generated method stub
        writeText("What would you like to do?");
        writeText("1> Query Selected Data");
        writeText("2> Change Selected Data");
        writeText("3> Generate New Data");
        writeText("4> Exit Applicaiton ");
        if (input == null) {
            input = getUserInput();
        }

        switch (input) {
            case "1":
                queryScreen(furtherString, 0, 0);
                return "query screen";

            case "2":
                selectFileScreen(furtherString);
                break;
            case "3":
            	dataGenerator(null);
                break;
            case "4":
                System.exit(0);;
                break;

            default:
                writeText("Sorry you can't enter that key! Please try again...");
                menuScreen(null, null);
        }
        return "***";
    }
    /**
     * 
     * @param input
     * @param lowNum
     * @param highNum
     * @return value based on first string input
     * 
     * @Cases : 4
     * NOTE_ Missing Default...
     * 
     * 1> Find oldest person in data set
     * 2> Group by location
     * 3> Group by location and select and age range
     * 4> Search person by first letter of their name
     */
    @SuppressWarnings({ "unused" })
	public String queryScreen(String input, Integer lowNum, Integer highNum) {

        writeText("\nWhat would you like to do?");
        writeText("1> Find oldest person in dataset");
        writeText("2> Group by location");
        writeText("3> Group by location and select and age range");
        writeText("4> Search person by first letter of their name");
        
        if (input == null) {
            input = getUserInput();
        }

        int i = 0;
        
        switch (input) {

            case "1":
            	JSONArray group = Query.getOldestPerson(personArray);
                for (i = 0; i < group.length(); i++) {
                    writeText("ID: " + group.getJSONObject(i).getString("id") +
                        "     FName: " +
                        group.getJSONObject(i).getString("firstName") +
                        "     LName: " +
                        group.getJSONObject(i).getString("lastName") +
                        "     Age: " +
                        group.getJSONObject(i).getString("age") + "\t" +
                        "     Location: " +
                        group.getJSONObject(i).getString("location")

                    );
                  
                    
                }
                writeText(brk + "Total: " +group.length() + brk);
                if (i != 0) {
                	  menuScreen(null,null);
                	return group.getJSONObject(0).getString("age") + group.length();
                }
                
 
            case "2":
                String localLocation = "";
                for (Locations loco: Locations.values()) {
                    localLocation = loco.toString();
                    JSONArray group1 = Query.getPeopleGroupedByCountry(localLocation, personArray);
                    for (i = 0; i < group1.length(); i++) {
                        writeText("ID: " + group1.getJSONObject(i).getString("id") +
                            "     FName: " +
                            group1.getJSONObject(i).getString("firstName") +
                            "     LName: " +
                            group1.getJSONObject(i).getString("lastName") +
                            "     Age: " +
                            group1.getJSONObject(i).getString("age") + "\t" +
                            "     Location: " +
                            group1.getJSONObject(i).getString("location")

                        );
                    }
                    writeText(brk + localLocation + " | TOTAL: " + group1.length() + "\n");
                }
             
                if (i != 0) {
                	  menuScreen(null,null);
                    return Query.getPeopleGroupedByCountry(localLocation, personArray).getJSONObject(0).getString("location");
                }
              
                break;
            case "3":

                if (lowNum == 0) {
                    writeText("What age group do you want to query?");
                    writeText("Enter LOWER number...");
                    lowNum = Integer.parseInt(getUserInput());
                }
                if (highNum == 0) {
                    writeText("Enter Higher number");
                    highNum = Integer.parseInt(getUserInput());
                }

                String localLocation1 = "";
                for (Locations loco: Locations.values()) {
                    localLocation1 = loco.toString();
                    JSONArray group1 = Query.getPeopleGroupedByCountryAndAge(loco.toString(), lowNum, highNum, personArray);

                    for (i = 0; i < group1.length(); i++) {
                        writeText("ID: " + group1.getJSONObject(i).getString("id") +
                            "     FName: " +
                            group1.getJSONObject(i).getString("firstName") +
                            "     LName: " +
                            group1.getJSONObject(i).getString("lastName") +
                            "     Age: " +
                            group1.getJSONObject(i).getString("age") + "\t" +
                            "     Location: " +
                            group1.getJSONObject(i).getString("location")

                        );
                    }
                    writeText(brk + localLocation1 + " | TOTAL: " + group1.length() + "\n");
                    if (i > 0 && i == group1.length()) {
                    	  menuScreen(null,null);
                        return Query.getPeopleGroupedByCountryAndAge(localLocation1, lowNum, highNum, personArray).getJSONObject(0).getString("location") + Query.getPeopleGroupedByCountryAndAge(localLocation1, lowNum, highNum, personArray).getJSONObject(0).getString("age");
                    }
                }
               break;
                
            case "4":
            	writeText("Please Enter the Letter You want to search by and press enter!");
                String word = getUserInput().toLowerCase();
                char letter = word.charAt(0);
                JSONArray group1 = Query.getPeopleBasedOnFirstLetter(letter,personArray);
                for (i = 0; i < Query.getPeopleBasedOnFirstLetter(letter,personArray).length(); i++) {
                    writeText("ID: " + group1.getJSONObject(i).getString("id") +
                        "     FName: " +
                        group1.getJSONObject(i).getString("firstName") +
                        "     LName: " +
                        group1.getJSONObject(i).getString("lastName") +
                        "     Age: " +
                        group1.getJSONObject(i).getString("age") + "\t" +
                        "     Location: " +
                        group1.getJSONObject(i).getString("location")

                    );


                }
                writeText(brk + "Total= " + group1.length() + brk);
                menuScreen(null,null);
                return group1.length() + "";
            default:
            	return "***";
               
        }
		return "***";
	
       


    }

     /**
      * 
      * @param input
      * @Generates RandomePeople
      */
    void systemFunctionSelection() {
       writeText("Welcome to the Query System Would you like to...\n\n 1> Generate data?\n 2> Query an existing file?\n 3> EXIT");
       String input = getUserInput().toString();
       if(input.equals("1") ){
    	   dataGenerator(null);
       }else if(input.equals("2")){
    	   selectFileScreen(null);
       }
       else {
    	   System.exit(0);
       }
    }
    void dataGenerator(String input) {
    	 writeText(brk + " Welcome to the Data generator\n");
         writeText(" How many people would you like to generate?" + brk);
         if (input == null) {
             input = getUserInput();
         }
         Integer amountToGenerate = Integer.parseInt(input);
         writeText(brk + " You chose to generate " + amountToGenerate + " People\n ... \n");
         writeText(" Please wait while dataset is generated\n ...");
         setRandomPeople(amountToGenerate);
    }
    
    
    /**
     ********************************************* FILE MANAGENT ****************************************
     */
    /**
     * 
     * @param fileSelected
	 * @param personArray 
	 * @return 
     * @returns a file based on a number selection 
     * @SentTo File parser to be used in data manipulation and querying
     */
    public JSONArray selectFileScreen(String fileSelected) {
        // TODO Auto-generated method stub
        String fileName = null;
        List < String > results = getAllDirectoryFiles();
        int i;

        for (i = 0; i < results.size(); i++) {
        	System.out.println(i + " > " + results.get(i).toString());

        }
        System.out.println("Please enter the number of the file you would like to use");
        if (fileSelected == null) {
            fileSelected = getUserInput();
        }
        fileName = results.get(Integer.parseInt(fileSelected)).toString();
        JSONArray arr = parseJSONFromFile(fileName);
		return arr;

    }
    /**
     * 
     * @param file
     * @return 
     * @Clears old array
     * @Defines new person Array
     * 
     * @Alternative user sent back to menu 
     */
    public JSONArray parseJSONFromFile(String file) {
        // In it scanner
        @SuppressWarnings("unused")
		String rawDataStream = "";
        String location = new File(file).getAbsolutePath();
        Scanner s = null;
        personArray.clear();
        int count = 0;
        try {
            s = new Scanner(new File(location));
        } catch (FileNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
            System.out.println(" \nFile Not Found Try Again\n ...");
            selectFileScreen(null);
        }
        // Checks for has next line + adds to randomNames array || closes scanner

        while (s.hasNext()) {
            String nextValue = s.next();
            JSONObject person = new JSONObject(nextValue);
            personArray.put(person);
        }
        s.close();

        while (count < personArray.length()) {
        	System.out.println("ID: " + personArray.getJSONObject(count).getString("id") +
                "     FName: " +
                personArray.getJSONObject(count).getString("firstName") +
                "     LName: " +
                personArray.getJSONObject(count).getString("lastName") +
                "     Age: " +
                personArray.getJSONObject(count).getString("age") + "\t" +
                "     Location: " +
                personArray.getJSONObject(count).getString("location")

            );

            count += 1;
        }
        System.out.println(brk + "Total= " + count + brk);

        if (file.toString() != "t.ndjson") {
        	
        	menuScreen(null, null);
        }
		return personArray;
    }
    
    /**
     * 
     * @param fileName
     * @return true value when file is created
     * @throws IOException
     */
    public boolean writeToCreatedNDJSON(String fileName) throws IOException {
        String location = new File(fileName + ".ndjson").getAbsolutePath();

        int amountWritten = 0;
        int dataReaderLength = 0;

        FileWriter myWriter = new FileWriter(location);
        // NDJSON manual convert of list to NDJSON file write

        while (amountWritten != RandomPeople.size() / valueCount) {
            myWriter.write("{\"id\":\"" + RandomPeople.get(dataReaderLength) + "\"");
            myWriter.write(",");
            myWriter.write("\"firstName\":");
            myWriter.write("\"" + RandomPeople.get(dataReaderLength + 1) + "\"");
            myWriter.write(",");
            myWriter.write("\"lastName\":");
            myWriter.write("\"" + RandomPeople.get(dataReaderLength + 2) + "\"");
            myWriter.write(",");
            myWriter.write("\"age\":\"" + RandomPeople.get(dataReaderLength + 3) + "\"");
            myWriter.write(",");
            myWriter.write("\"location\":\"" + RandomPeople.get(dataReaderLength + 4) + "\"");
            myWriter.write("}\n");
            //value count externally counted 
            dataReaderLength = dataReaderLength + valueCount;
            amountWritten = amountWritten + 1;
        }
        // close file writer to avoid possible memory leak
        myWriter.close();
        writeText(" Successfully wrote to the file.");
        writeText(" Press Enter to continue....");
        return true;
    }
    
    // Creation of the local NDJSON File 
    public void createNDJSON(String fileName) {
        String location = new File(fileName + ".ndjson").getAbsolutePath();
        try {
            // setting file object location to save to 
            File myObj = new File(location);
            // create file with above location, file not created if file already exists
            if (myObj.createNewFile()) {
                writeText(" File created: " + myObj.getName());
                writeText(" Writing to new File");
                if (writeToCreatedNDJSON(fileName)) {
                    viewDataScreen(getUserInput());
                }
            } else {
                writeText("\n File already exists. OverWriting");
                if (writeToCreatedNDJSON(fileName)) {
                    viewDataScreen(getUserInput());
                }
            }
        } catch (IOException e) {
            writeText(" An error occurred.");
            e.printStackTrace();
        }
    }

    /**
     * 
     * @return Directory results of absolute pathway defined
     */
     List < String > getAllDirectoryFiles() {
        List < String > results = new ArrayList < String > ();
        String location = new File("").getAbsolutePath();

        File[] files = new File(location).listFiles();
        //If this pathname does not denote a directory, then listFiles() returns null. 

        for (File file: files) {
            if (file.isFile()) {
                results.add(file.getName());
            }
        }
        return results;
    }




}