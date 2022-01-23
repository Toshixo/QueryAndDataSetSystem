# QueryAndDataSetSystem
WELCOME TO MY QUERY AND DATA SET SYSTEM.
This is made in responce to a coding evaluation/test 

Time Taken: Aproximatley 5 Hrs 
Shared on :23/01/22

Requirements: 

1.	Generate data

•	Create a randomly generated dataset that will output NDJSON. Would like this randomly generated dataset to look like names. One approach would be to have a big list of names and randomly pick two from this list for first and last name.
•	Should take an argument to specify how many rows to generate.
•	The schema of a row should have the following fields:
▫	firstName
▫	lastName
▫	age
▫	id
▫	Country

!IMAGE BELOW CONTAINS CLI WORKING AS INTENDED

![image](https://user-images.githubusercontent.com/63938964/150668157-a6f05f8e-2ede-48c6-8691-807a62034f94.png)

2.	Query generated data
•	Should take an argument to pass in the generated dataset from step 1.

!INFO _ 
Displays the files in the file save directory

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
  
!IMAGE BELOW CONTAINS CLI WORKING AS INTENDED
  
!INFO_ 
opens file save directory for user to select what nsjson file they want to use doesnt have to be the one just generated
  
![image](https://user-images.githubusercontent.com/63938964/150668231-ce4669e3-92bf-4ad8-a9e0-ffd847b927da.png)
  
INFO_ 
system creates a NDJSON file out of generated names based upon the Names.txt file
system then writes to that file a first and last name a randomized age an incremental id and a location out of the enum.
when the system wants to read the selected file it is converted into json which is then stored into lists and arrays for looping( Ineffiecient but did 
  what I could with my spare time) 
  
•	Find the oldest person.

!INFO 
below code checks inital person age and compares it to the next person in the array
the higherperson replaces the lower person
if the person is the same age that gets added to the array of older people until someone older 
replaces them. 

Integer maxAge = 0;
		JSONArray olderPerson  = new JSONArray();
		
		for(int i = 0; i<personArray.length() ; i++) {
			Integer age = Integer.parseInt(personArray.getJSONObject(i).getString("age"));
			if(age == maxAge ) {
				olderPerson.put(personArray.getJSONObject(i));
				
			}
			else if(age > maxAge) {
				maxAge = age;
//				writeText(age + " | "+ maxAge);
				olderPerson.clear();
				olderPerson.put(personArray.getJSONObject(i));
			}
		}
		return olderPerson;
    
•	Group by country and return count.
  
!INFO_ 
this method takes in a location string and compares it to people in the person array if theres a match the person is added to the perople organised by location JSON array and 
then returned.
  
private JSONArray getPeopleGroupedByCountry(String locationCheck) {
		JSONArray PeopleOrganisedByLocation  = new JSONArray();
		
			
			for(int i = 0; i<personArray.length() ; i++) {
				String location = personArray.getJSONObject(i).getString("location");
				
			  if(location.toString().equals(locationCheck.toString())) {
				  PeopleOrganisedByLocation.put(personArray.getJSONObject(i));
				  
			  }
			}
		  
		return PeopleOrganisedByLocation;
	}
•	For a given country group by age ranges: 0 -> 10, 10 -> 20 etc.
  
!INFO_ 
this method is a modified version of the grouped by counry method the change is it also compares the person against a lower and a higher age restriction.
  
private JSONArray getPeopleGroupedByCountryAndAge(String locationCheck, int lowNum,int highNum) {
		JSONArray PeopleOrganisedByLocationAndAge  = new JSONArray();
		
			
			for(int i = 0; i<personArray.length() ; i++) {
				String location = personArray.getJSONObject(i).getString("location");
				
			  if(location.toString().equals(locationCheck.toString()) && Integer.parseInt(personArray.getJSONObject(i).getString("age")) < highNum && Integer.parseInt(personArray.getJSONObject(i).getString("age")) > lowNum) {
				  PeopleOrganisedByLocationAndAge.put(personArray.getJSONObject(i));
				  
			  }
			}
		  
		return PeopleOrganisedByLocationAndAge;
	}
•	One of your own creation.
  
!INFO _ added functionality of searching by the first letter of peoples names in a selected country.
  
