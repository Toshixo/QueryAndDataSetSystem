package Testing;

import static org.junit.jupiter.api.Assertions.*;

import java.io.IOException;

import org.json.JSONArray;
import JavaExperienceTestPackage.QuerySystemUI;

class Test {
	//start instance of System
	QuerySystemUI QuerySystem = new QuerySystemUI();
	
	@org.junit.jupiter.api.Test
	void testInitValues() {
		QuerySystem.setValues();
		assertTrue(QuerySystem.ValueList.size() == 5);
	}
	
	/**
	 * @Test of method GetRandomLocation
	 * expecting correct values when number(1 through 5) is passed through.
	 * expect true when passing in right number
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGetRandomLocationNZ() {
		QuerySystem.setValues();
		String output = QuerySystem.generateRandomLocation(1);
		assertTrue(output == "New_Zealand");
		assertFalse(output == "New_Zealsand");
	}
	/**
	 * @Test of method GetRandomLocation
	 * expecting correct values when number(1 through 5) is passed through.
	 * expect true when passing in right number
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGetRandomLocationNA() {
		QuerySystem.setValues();
		String output = QuerySystem.generateRandomLocation(5);
		assertTrue(output == "NA");
		assertFalse(output == "N/a");
	}
	/**
	 * @Test of method GetRandomLocation
	 * expecting correct values when number(1 through 5) is passed through.
	 * expect true when passing in right number
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGetRandomLocationSW() {
		QuerySystem.setValues();
		String output = QuerySystem.generateRandomLocation(4);
		assertTrue(output == "Switzerland");
		assertFalse(output == "e");
	}
	/**
	 * @Test of method GetRandomLocation
	 * expecting correct values when number(1 through 5) is passed through.
	 * expect true when passing in right number
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGetRandomLocationRA() {
		QuerySystem.setValues();
		String output = QuerySystem.generateRandomLocation(3);
		assertTrue(output == "Russia");
		assertFalse(output == "Russiwaef");
	}
	/**
	 * @Test of method GetRandomLocation
	 * expecting correct values when number(1 through 5) is passed through.
	 * expect true when passing in right number
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGetRandomLocationAUS() {
		QuerySystem.setValues();
		String output = QuerySystem.generateRandomLocation(2);
		assertTrue(output == "Australia");
		assertFalse(output == "Aussie");
	}
	/**
	 * @Test for Writing to a NDJSON file 
	 * This case is tested with a file called 't.ndjson'
	 */
	@org.junit.jupiter.api.Test
	void testRandomPeopleFile() {
		QuerySystem.setValues();
		assertTrue(0==QuerySystem.RandomPeople.size());
		QuerySystem.setRandomPeople(223836);
		assertTrue(1119180==QuerySystem.RandomPeople.size());
		
	}
	/**
	 * @Test of method to get the highest aged person
	 * expecting correct age passed back from a pre created file "t.ndjson"
	 * expect true when passing in this file.
	 * expecting a false when checking wrong text
	 */
	@org.junit.jupiter.api.Test
	void testGettingHighestAgedPerson() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		
		JSONArray OP = QuerySystem.getOldestPerson(QuerySystem.personArray);
		System.out.println(OP);
		assertEquals(87, Integer.parseInt(OP.getJSONObject(0).getString("age")));
	}
	/**
	 * tests the query screen menu with the first option
	 * also uses the test "t" file
	 * result is generated and is expected to be 821 
	 * 82 is the highest age and 1 is the amount of people that us oldest
	 * this shows that the UI Correctly works
	 */
	@org.junit.jupiter.api.Test
	void testViewQueryScreenAge() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		String result = QuerySystem.queryScreen("1",1,1);
		assertEquals(result, "871");
		
		
	}
	/**
	 * this is a test on the query screen "option 2" 
	 * this should return a the location from someone from switzerland
	 */
	@org.junit.jupiter.api.Test
	void testViewQueryScreenLocation() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		String result = QuerySystem.queryScreen("2",1,1);
		assertEquals(result, "Switzerland");
		
	}
	/**
	 * this is a test on the query screen "option 3" 
	 * it should show a selected person between the ages of 10 and 12
	 * and show the country switzerland based on the data
	 */
	@org.junit.jupiter.api.Test
	void testViewQueryScreenLocationAndAge() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		String result = QuerySystem.queryScreen("3",10,12);
		assertEquals( "***",result);
		
	}
	@org.junit.jupiter.api.Test
	void testViewQueryScreenLocationAndAge2() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		String result = QuerySystem.queryScreen("3",7,9);
		assertEquals( "Switzerland8",result);
		
	}

	@org.junit.jupiter.api.Test
	void testViewMenuScreenOpt1() {
		QuerySystem.setValues();
		String value = (String) QuerySystem.menuScreen("1", "1");
		assertEquals(value, "query screen");
		
	}
	/**
	 * Test for the correct return value from the t.json file
	 * this being 1 person starting with the character 'D'
	 */
	@org.junit.jupiter.api.Test
	void testGetPersonBasedOnFirstName() {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		String value = QuerySystem.getPeopleBasedOnFirstLetter('D').length() + "";
		assertEquals(value, "2");
		
	}
	@org.junit.jupiter.api.Test
	void testWriteText() {

		String value =QuerySystem.writeText("hi");
		assertEquals(value, "hi");
		
	}
	
	@org.junit.jupiter.api.Test
	void testWriteToFile() throws IOException {
		QuerySystem.setValues();
		QuerySystem.parseJSONFromFile("t.ndjson");
		assertEquals(true, QuerySystem.writeToCreatedNDJSON("w"));
	}
	/**
	 * this test is meant to test the option two on the menu screen however causes test to require input.
	 * to make things easier this is commented out for the time being.
	 * 
	 * breaks other tests
	 */
	@org.junit.jupiter.api.Test
	void testViewMenuScreenOpt2() {
//		QuerySystem.setValues();
//		String value = (String) QuerySystem.menuScreen("2", "8");
//		assertEquals(value, "select file screen");
		
		
	}
	/**
	 * this test is meant to test the generation of data however causes test to require input.
	 * to make things easier this is commented out for the time being.
	 * 
	 * the expected result is 10 based on the input value * 5.
	 * 
	 * breaks other tests
	 * 
	 */
	@org.junit.jupiter.api.Test
	void testFunctionSelectionAndGenerationOfData() {
//		QuerySystem.setValues();
//		QuerySystem.RandomPeople.clear();
//		int result = QuerySystem.RandomPeople.size();
//		assertEquals(result, 0);
//		QuerySystem.systemFunctionSelection("2");
//		result = QuerySystem.RandomPeople.size();
//		assertEquals(result, 10);
//		
		
	}
	
	

	
	

}
