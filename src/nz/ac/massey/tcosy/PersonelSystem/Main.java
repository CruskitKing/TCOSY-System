package nz.ac.massey.tcosy.PersonelSystem;

import java.beans.BeanInfo;
import java.beans.IntrospectionException;
import java.beans.Introspector;
import java.beans.PropertyDescriptor;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/*
 * This is a prototype that
 * Currently operates at the command line
 *
 * TODO Implement: GUI
 *
 * @author Mitchell Osborne
 *
 */
public class Main {

	public static void main(String[] args) throws IOException, IntrospectionException {
		
		String xmlFile = "XML.xml";
        BufferedReader br = new BufferedReader(new InputStreamReader(System.in));
        Boolean saved = true;

		Manager manager = new Manager(xmlFile);
        Person selectedPerson = null;

        while (true) {
            System.out.println(manager.getPersons());
            System.out.println(selectedPerson + "\n");
            System.out.print("Command: ");
            String inputArray = br.readLine();
            String[] input = inputArray.split(" ");

            if (input[0].equals("quit")) {
                if (!saved) {
                    System.out.println("Save? (Y/N)");
                    if (br.readLine().equals("Y")) {
                        manager.savePersons(xmlFile);
                        break;
                    }
                }
                else {
                    break;
                }
            }
            else if (input[0].equals("add")) {
                try {
                    manager.addPerson(new Person(input[1], input[2], Integer.parseInt(input[3]), input[4], input[5]));
                    saved = false;
                }
                catch (Exception e) {
                    System.out.println("Invalid Details");
                }
            }
            else if (input[0].equals("save")) {
                manager.savePersons(xmlFile);
                saved = true;
            }
            else if (input[0].equals("load") ){
                try {
                    manager.loadPersons(xmlFile);
                }
                catch (IOException e) {
                    System.out.println("File Not Found");
                }
            }
            else if (input[0].equals("remove")) {
                if (selectedPerson == null) {
                    System.out.println("No Person Selected");
                }
                else {
                    manager.removePerson(selectedPerson);
                    selectedPerson = null;
                    saved = false;
                }
            }
            else if (input[0].equals("get")) {
                selectedPerson = manager.getPerson(input[1],input[2]);
                if (selectedPerson == null) {
                    System.out.println("Cannot find " + input[1] + ", " + input[2]);
                }
            }
            else if (input[0].equals("modify")) {
                try {
                    BeanInfo beanInfo = Introspector.getBeanInfo(selectedPerson != null ? selectedPerson.getClass() : null);
                    PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
                    for (PropertyDescriptor property : properties) {
                        if (property.getName().equals(input[1])) {
                            property.getWriteMethod().invoke(selectedPerson, input[2]);
                        }
                    }
                }
                catch (Exception e) {
                    System.out.println("Invalid Inputs");
                }
            }
            else {
                System.out.println("Invalid Command\n");
            }
        }
	}
}