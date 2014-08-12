package nz.ac.massey.tcosy.PersonelSystem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class Manager {
	
	private static List<Person> persons = new ArrayList<Person>();
	private static String xmlFile;
	private static XStream xstream = new XStream(new StaxDriver());
	
	
//	public Manager() {
//		super();
//	}
	
	public Manager(String fileName) {
		super();
		xmlFile = fileName;
		xstream.alias("person", Person.class);
	}

    @SuppressWarnings("unchecked")
	public void loadPersons(String xmlFile) throws IOException {
        //
        //Loads the List<Person> from the XML File
        //
        FileInputStream fis = new FileInputStream(xmlFile);
		persons = (List<Person>)xstream.fromXML(fis);
        fis.close();
	}
	
	public void savePersons(String xmlFile) throws IOException {
		//
		// Save the List<Person> to the XML File
		//
		FileOutputStream fos = new FileOutputStream(xmlFile);
		xstream.toXML(getPersons(),fos);
        fos.close();
	}
	
	public void addPerson(Person person) {
		this.getPersons().add(person);
	}
	
	public Person getPerson(String name, String firstName) {
		for (Person person: getPersons()) {
			if (person.getName().equals(name) && person.getFirstName().equals(firstName)) {
				return person;
			}
		}
		return null;
	}
	
	public void removePerson(Person person) {
		if (!getPersons().remove(person)){
            System.out.println(person.getName() + ", " + person.getFirstName() + "does not exist");
        }
	}
	
	public List<Person> getPersons() {
		return persons;
	}
	
	public String getXmlFile() {
		return xmlFile;
	}
}