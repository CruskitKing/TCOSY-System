package nz.ac.massey.tcosy.PersonelSystem;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import com.thoughtworks.xstream.XStream;
import com.thoughtworks.xstream.io.xml.StaxDriver;

public class PersonManager {
	
	private List<Person> persons = new ArrayList<Person>();
	private String xmlFileName;
	private static XStream xstream = new XStream(new StaxDriver());
    private Boolean saved = true;
    private Person selectedPerson = null;

//	public PersonManager() {
//		super();
//	}
	
	public PersonManager(String fileName) {
		super();
		xmlFileName = fileName;
		xstream.alias("person", Person.class);
	}

    @SuppressWarnings("unchecked")
	public void loadPersons() throws IOException {
        //
        //Loads the List<Person> from the XML File
        //
        FileInputStream fis = new FileInputStream(this.xmlFileName);
		this.persons = (List<Person>)xstream.fromXML(fis);
        fis.close();
	}
	
	public void savePersons() throws IOException {
		FileOutputStream fos = new FileOutputStream(xmlFileName);
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
	
	public void removePerson() {
		if (!getPersons().remove(selectedPerson)){
            System.out.println(selectedPerson.getName() + ", " + selectedPerson.getFirstName() + "does not exist");
        }
	}
	
	public List<Person> getPersons() {
		return persons;
	}
    public Boolean getSaved() {
        return saved;
    }
    public void setSaved(Boolean saved) {
        this.saved = saved;
    }
    public Person getSelectedPerson() {
        return selectedPerson;
    }
    public void setSelectedPerson(Person selectedPerson) {
        this.selectedPerson = selectedPerson;
    }
}