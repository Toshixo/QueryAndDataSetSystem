package JavaExperienceTestPackage;

import org.json.JSONArray;

public class SystemQueries {
	/**
	 * 
	 * @param personArray
	 * @return older person out of the array passed in
	 */
	public JSONArray getOldestPerson(JSONArray personArray) {

        Integer maxAge = 0;
        JSONArray olderPerson = new JSONArray();

        for (int i = 0; i < personArray.length(); i++) {
            Integer age = Integer.parseInt(personArray.getJSONObject(i).getString("age"));
            if (age == maxAge) {
                olderPerson.put(personArray.getJSONObject(i));

            } else if (age > maxAge) {
                maxAge = age;
                olderPerson.clear();
                olderPerson.put(personArray.getJSONObject(i));
            }
        }
       
        return olderPerson;

    } 
	/**
	 * 
	 * @param personArray
	 * @return grouped person(s) out of the array passed in based on locations
	 */
    public JSONArray getPeopleGroupedByCountry(String locationCheck, JSONArray personArray) {
        JSONArray PeopleOrganisedByLocation = new JSONArray();


        for (int i = 0; i < personArray.length(); i++) {
            String location = personArray.getJSONObject(i).getString("location");

            if (location.toString().equals(locationCheck.toString())) {
                PeopleOrganisedByLocation.put(personArray.getJSONObject(i));

            }
        }

        return PeopleOrganisedByLocation;
    }
    /**
	 * 
	 * @param personArray
	 * @return grouped person(s) out of the array passed in based on locations.
	 * also based on age range provided by user input
	 */
    public JSONArray getPeopleGroupedByCountryAndAge(String locationCheck, int lowNum, int highNum, JSONArray personArray) {
        JSONArray PeopleOrganisedByLocationAndAge = new JSONArray();


        for (int i = 0; i < personArray.length(); i++) {
            String location = personArray.getJSONObject(i).getString("location");

            if (location.toString().equals(locationCheck.toString()) && Integer.parseInt(personArray.getJSONObject(i).getString("age")) < highNum && Integer.parseInt(personArray.getJSONObject(i).getString("age")) > lowNum) {
                PeopleOrganisedByLocationAndAge.put(personArray.getJSONObject(i));

            }
        }

        return PeopleOrganisedByLocationAndAge;
    }
    /**
   	 * 
   	 * @param personArray
   	 * @return grouped person(s) out of the array passed in based on a character.
   	 * chosen character depends on use rinput
   	 */
    public JSONArray getPeopleBasedOnFirstLetter(char nameCheck, JSONArray personArray) {
        JSONArray PeopleFoundByName = new JSONArray();
        for (int i = 0; i < personArray.length(); i++) {
            String name = personArray.getJSONObject(i).getString("firstName").toString().toLowerCase();
            char nameChar = name.charAt(0);
            if (nameChar == nameCheck) {
                PeopleFoundByName.put(personArray.getJSONObject(i));

            }
        }

        return PeopleFoundByName;
    }
}
