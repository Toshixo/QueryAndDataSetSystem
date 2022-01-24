package JavaExperienceTestPackage;

// Exceptions
import java.io.IOException;
import java.lang.reflect.Array;
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
	public List<String> RandomNames = new ArrayList<String>(); 
	
	// @List of people
	public List<String> RandomPeople = new ArrayList<String>(); 
	
	public JSONArray personArray  = new JSONArray();
	
	// Default location for random names file
	public String location = new File("Names.txt").getAbsolutePath();
	
	// Holds list of data set titles
	public List<String> ValueList = new ArrayList<String>(); 
	
	// Initialization of total amount of titles
	public int valueCount = ValueList.size();
	
	// ! GET METHODS!
	
	// @return the randomNames
	public List<String> getRandomNames(int amountToGenerate) {
		String location = new File(".").getAbsolutePath();
		System.out.print("\n" + brk+ " File saved to " + location + "\n");
		
		return RandomNames;
	}
	// @return the users input 
	public String getUserInput() {
		@SuppressWarnings("resource")
		Scanner scanner = new Scanner(System.in);
		String userInput = scanner.nextLine();
		return userInput;
		
	}
	// @returns random names from file into 
	public void setRandomListFromTextFile(String location) {
		// In it scanner
		Scanner s = null;
		try {
			s = new Scanner(new File(location));
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
			writeText(" File Not Found Returning to Dataset Generator");
			systemFunctionSelection(null);
		}
		// Checks for has next line + adds to randomNames array || closes scanner
		 while (s.hasNext()){
		     RandomNames.add(s.next());
		 }
		 s.close();
	}
	
	// ! SET METHODS !
	
	// Adds and sets size of title list
	public void setValues() {
		ValueList.add("id");
		ValueList.add("firstName");
		ValueList.add("lastName");
		ValueList.add("age");
		ValueList.add("location");
		valueCount = ValueList.size();
	}
	
	// 
	public void setRandomPeople(int amountToGenerate) {
		String location = new File("Names.txt").getAbsolutePath();
		setRandomListFromTextFile(location);
		
		Integer incrementalId = 0;
		
		while(RandomPeople.size() < amountToGenerate * valueCount) {
			
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
		if(amountToGenerate != 223836) {
		writeText("\n Enter the name you want to call you NDJSON file.\n no need to include the extension.");
		
			createNDJSON(getUserInput());
		}
		
		
	}
	// Creation of the local NDJSON File 
	public void createNDJSON(String fileName) {
		String location = new File(fileName+".ndjson" ).getAbsolutePath();
		
		
	    try {
	    	// setting file object location to save to 
	      File myObj = new File(location);
	      // create file with above location, file not created if file already exists
	      if (myObj.createNewFile()) {
	        writeText(" File created: " + myObj.getName());
	        writeText(" Writing to new File");
	        if(writeToCreatedNDJSON(fileName)) {
	        	viewDataScreen(getUserInput());
	        }
	      } else {
	        writeText("\n File already exists. OverWriting");
	        if(writeToCreatedNDJSON(fileName)) {
	        	viewDataScreen(getUserInput());
	        }
	      }
	    } catch (IOException e) {
	      writeText(" An error occurred.");
	      e.printStackTrace();
	    }
  }
	
	// returns location based on input number and a switch
	public String generateRandomLocation(int d) {
		
		switch(d) { 
		case 1:
			return "New_Zealand";
		case 2:
			return "Australia";
		case 3:
			return "Russia";
		case 4:
			return "Switzerland";
		default:
			return "NA";
		}
	}
	//write to local file 
	public boolean writeToCreatedNDJSON(String fileName) throws IOException {
		String location = new File(fileName+".ndjson").getAbsolutePath();
		
		int amountWritten = 0;
		int dataReaderLength = 0;
		
		
		      FileWriter myWriter = new FileWriter(location);
		      // NDJSON manual convert of list to NDJSON file write
		   
		      while(amountWritten != RandomPeople.size()/valueCount) {
		    	  myWriter.write("{\"id\":\"" + RandomPeople.get(dataReaderLength) + "\"");
		    	  myWriter.write(",");
		    	  myWriter.write("\"firstName\":");
		    	  myWriter.write("\"" + RandomPeople.get(dataReaderLength+1) + "\"" );
		    	  myWriter.write(",");
		    	  myWriter.write("\"lastName\":");
		    	  myWriter.write("\"" + RandomPeople.get(dataReaderLength+2) + "\"" );
		    	  myWriter.write(",");
		    	  myWriter.write("\"age\":\"" + RandomPeople.get(dataReaderLength+3) + "\"");
		    	  myWriter.write(",");
		    	  myWriter.write("\"location\":\"" +  RandomPeople.get(dataReaderLength+4) + "\"");
		    	  myWriter.write("}\n");
		    	  dataReaderLength = dataReaderLength+valueCount;
		    	  amountWritten = amountWritten + 1;
		      }
		    
		      // close file writer to avoid possible memory leak
		      myWriter.close();
		    
		      writeText(" Successfully wrote to the file.");
		      writeText(" Press Enter to continue....");
		      return true;
		      
		    
	}
	
	
	public String writeText(String text) {
		System.out.println(text);
		return text;
	}
	
	
	

	public void viewDataScreen(String input) {
		
		writeText(brk + "Would you like to view created data?\n Y / N " + brk);
	      
	      String input1 = getUserInput();
	      switch(input1) {
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
	
	public void selectFileScreen(String fileSelected) {
		// TODO Auto-generated method stub
		String fileName = null;
		List<String> results = getAllDirectoryFiles();
		int i;
		
		for(i = 0; i < results.size(); i++) {
			writeText(i + " > " + results.get(i).toString());
			
		}
		writeText("Please enter the number of the file you would like to use");
		if(fileSelected == null) {
			fileSelected = getUserInput();
		}
		fileName = results.get(Integer.parseInt(fileSelected)).toString();
		parseJSONFromFile(fileName);
		
	}
	public void parseJSONFromFile(String file) {
		// In it scanner
		String rawDataStream = "" ;
		String location = new File(file).getAbsolutePath();
		Scanner s = null;
		personArray.clear();
		int count=0;
			try {
				s = new Scanner(new File(location));
			} catch (FileNotFoundException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
				writeText(" \nFile Not Found Returning to Dataset Generator\n ...");
				systemFunctionSelection(null);
			}
			// Checks for has next line + adds to randomNames array || closes scanner
			
			 while (s.hasNext()){
				 String nextValue = s.next();
			     JSONObject person = new JSONObject(nextValue);
			     personArray.put(person);
			 }
		s.close();
		

		
		
		while(count<personArray.length()) {
			writeText("ID: " +personArray.getJSONObject(count).getString("id") 
					+ "     FName: "
					+ personArray.getJSONObject(count).getString("firstName") 
					+ "     LName: "
					+ personArray.getJSONObject(count).getString("lastName")
					+ "     Age: "
					+ personArray.getJSONObject(count).getString("age")+ "\t"
					+ "     Location: "
					+ personArray.getJSONObject(count).getString("location")
					
					);
		
			count+=1;
		}
		writeText(brk + "Total= " + count + brk);
		
		if(file.toString() != "t.ndjson" ) {
			menuScreen(null, null);
		}
		
	
	}
	
	public String menuScreen(String input, String furtherString ) {
		// TODO Auto-generated method stub
		writeText("What would you like to do?");
		writeText("1> Query Selected Data");
		writeText("2> Change Selected Data");
		writeText("3> Generate New Data");
		writeText("4> Exit Applicaiton ");
		if (input == null) {
			input = getUserInput();
		}
		
		switch(input) {
		case "1": 
			queryScreen(furtherString,0,0);
			return "query screen";
			
		case "2": 
			selectFileScreen(furtherString);
			break;
		case "3": 
			systemFunctionSelection(null);
			break;
		case "4": 
			System.exit(0);;
			break;
			
		default:
			writeText("Sorry you can't enter that key! Please try again...");
			menuScreen(null,null);
		}
		return "not correct";
		
	}
	public String queryScreen(String input, Integer lowNum, Integer highNum) {
		
		
		writeText("\nWhat would you like to do?");
		writeText("1> Find oldest person in dataset");
		writeText("2> Group by location");
		writeText("3> Group by location and select and age range");
		writeText("4> Search person by first letter of their name");
		if(input==null) {
			input = getUserInput();
		}
		
		int i = 0;
		switch(input) {
		
		case"1":
			for(i = 0; i < getOldestPerson(personArray).length(); i++) {
				writeText("ID: " +getOldestPerson(personArray).getJSONObject(i).getString("id") 
						+ "     FName: "
						+ getOldestPerson(personArray).getJSONObject(i).getString("firstName") 
						+ "     LName: "
						+ getOldestPerson(personArray).getJSONObject(i).getString("lastName")
						+ "     Age: "
						+ getOldestPerson(personArray).getJSONObject(i).getString("age")+ "\t"
						+ "     Location: "
						+ getOldestPerson(personArray).getJSONObject(i).getString("location")
						
						);
				writeText(brk + "Total: " + getOldestPerson(personArray).length() + brk);
				
				return getOldestPerson(personArray).getJSONObject(i).getString("age")+getOldestPerson(personArray).length();
			
			}
			
		case"2":
			String localLocation = "";
			for(Locations loco : Locations.values()) {
				localLocation = loco.toString();
				for( i = 0; i < getPeopleGroupedByCountry(loco.toString()).length(); i++) {
				writeText("ID: " +getPeopleGroupedByCountry(loco.toString()).getJSONObject(i).getString("id") 
						+ "     FName: "
						+ getPeopleGroupedByCountry(loco.toString()).getJSONObject(i).getString("firstName") 
						+ "     LName: "
						+ getPeopleGroupedByCountry(loco.toString()).getJSONObject(i).getString("lastName")
						+ "     Age: "
						+ getPeopleGroupedByCountry(loco.toString()).getJSONObject(i).getString("age")+ "\t"
						+ "     Location: "
						+ getPeopleGroupedByCountry(loco.toString()).getJSONObject(i).getString("location")
						
						);
				}
				writeText(brk + localLocation +" | TOTAL: " + getPeopleGroupedByCountry(loco.toString()).length()+ "\n");
				
				
				
			}
			if(i != 0) {
				return getPeopleGroupedByCountry(localLocation).getJSONObject(0).getString("location");
			}
			break;
		case"3":
			
			
			if(lowNum ==0) {
				writeText("What age group do you want to query?");
				writeText("Enter LOWER number...");
				lowNum = Integer.parseInt(getUserInput());
			}
			if(highNum == 0) {
				writeText("Enter Higher number");
				highNum = Integer.parseInt(getUserInput());
			}
			
			String localLocation1 = "";
			for(Locations loco : Locations.values()) {
				localLocation1 = loco.toString();
				JSONArray group = getPeopleGroupedByCountryAndAge(loco.toString(),lowNum, highNum);
				
				for(i = 0; i < group.length(); i++) {
				writeText("ID: " +group.getJSONObject(i).getString("id") 
						+ "     FName: "
						+ group.getJSONObject(i).getString("firstName") 
						+ "     LName: "
						+ group.getJSONObject(i).getString("lastName")
						+ "     Age: "
						+ group.getJSONObject(i).getString("age")+ "\t"
						+ "     Location: "
						+ group.getJSONObject(i).getString("location")
						
						);
				}
				writeText(brk + localLocation1 +" | TOTAL: " + group.length()+ "\n");
				if(i != 0) {
					return  group.getJSONObject(0).getString("location") + group.getJSONObject(0).getString("age");
				}
				
			}
			break;
			
		case "4":
			String word = getUserInput();
			char letter = word.charAt(0);
			for(i = 0; i < getPeopleBasedOnFirstLetter(letter).length(); i++) {
				writeText("ID: " + getPeopleBasedOnFirstLetter(letter).getJSONObject(i).getString("id") 
						+ "     FName: "
						+ getPeopleBasedOnFirstLetter(letter).getJSONObject(i).getString("firstName") 
						+ "     LName: "
						+ getPeopleBasedOnFirstLetter(letter).getJSONObject(i).getString("lastName")
						+ "     Age: "
						+ getPeopleBasedOnFirstLetter(letter).getJSONObject(i).getString("age")+ "\t"
						+ "     Location: "
						+ getPeopleBasedOnFirstLetter(letter).getJSONObject(i).getString("location")
						
						);
				
			
			}
			writeText(brk + "Total= " + getPeopleBasedOnFirstLetter(letter).length() + brk);
			
			return getPeopleBasedOnFirstLetter(letter).length() + "";
		}
		
		return "***";
		
		
	}
	
	public JSONArray getOldestPerson(JSONArray personArray) {
		
		Integer maxAge = 0;
		JSONArray olderPerson  = new JSONArray();
		
		for(int i = 0; i<personArray.length() ; i++) {
			Integer age = Integer.parseInt(personArray.getJSONObject(i).getString("age"));
			if(age == maxAge ) {
				olderPerson.put(personArray.getJSONObject(i));
				
			}
			else if(age > maxAge) {
				maxAge = age;
				olderPerson.clear();
				olderPerson.put(personArray.getJSONObject(i));
			}
		}
		return olderPerson;
		
	}
	public JSONArray getPeopleGroupedByCountry(String locationCheck) {
		JSONArray PeopleOrganisedByLocation  = new JSONArray();
		
			
			for(int i = 0; i<personArray.length() ; i++) {
				String location = personArray.getJSONObject(i).getString("location");
				
			  if(location.toString().equals(locationCheck.toString())) {
				  PeopleOrganisedByLocation.put(personArray.getJSONObject(i));
				  
			  }
			}
		  
		return PeopleOrganisedByLocation;
	}
	
	public JSONArray getPeopleGroupedByCountryAndAge(String locationCheck, int lowNum,int highNum) {
		JSONArray PeopleOrganisedByLocationAndAge  = new JSONArray();
		
			
			for(int i = 0; i<personArray.length() ; i++) {
				String location = personArray.getJSONObject(i).getString("location");
				
			  if(location.toString().equals(locationCheck.toString()) && Integer.parseInt(personArray.getJSONObject(i).getString("age")) < highNum && Integer.parseInt(personArray.getJSONObject(i).getString("age")) > lowNum) {
				  PeopleOrganisedByLocationAndAge.put(personArray.getJSONObject(i));
				  
			  }
			}
		  
		return PeopleOrganisedByLocationAndAge;
	}
	public JSONArray getPeopleBasedOnFirstLetter(char nameCheck) {
		JSONArray PeopleFoundByName  = new JSONArray();
		for(int i = 0; i<personArray.length() ; i++) {
			String name = personArray.getJSONObject(i).getString("firstName").toString();
			char nameChar = name.charAt(0);
		  if(nameChar == nameCheck ) {
			  PeopleFoundByName.put(personArray.getJSONObject(i));
			  
		  }
		}
	  
	return PeopleFoundByName;
	}
	public List<String> getAllDirectoryFiles() {
		List<String> results = new ArrayList<String>();
		String location = new File("").getAbsolutePath();

		File[] files = new File(location).listFiles();
		//If this pathname does not denote a directory, then listFiles() returns null. 

	for (File file : files) {
	    if (file.isFile()) {
		        results.add(file.getName());
		    }
		}
	return results;
}

	// System @function selector Data Generator / Data Query(only unlocked after data generation or local file is used) 
	public void systemFunctionSelection(String input) {
		writeText(brk + " Welcome to the Data generator\n");
		writeText(" How many people would you like to generate?" + brk);
		if(input ==null) {
			input = getUserInput();
		}
	
		
		Integer amountToGenerate = Integer.parseInt(input);
		writeText(brk + " You chose to generate " + amountToGenerate + " People\n ... \n");
		writeText(" Please wait while dataset is generated\n ...");
		setRandomPeople(amountToGenerate);
		
	}
	

	

}
