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
public class Console {

    private static String xmlFileName = "XML.xml";
    private static PersonManager manager = new PersonManager(xmlFileName);
    private static BufferedReader br = new BufferedReader(new InputStreamReader(System.in));

    public static void main(String[] args) throws IOException, IntrospectionException {

        while (true) {
            System.out.println(manager.getPersons());
            System.out.println(manager.getSelectedPerson() + "\n");
            System.out.print("Command: ");
            String inputArray = br.readLine();
            String[] input = inputArray.split(" ");

            if (input[0].equals("quit")) {
                checkSaved();
                break;
            }
            else if (input[0].equals("add")) {
                add(input[1],input[2],input[3],input[4], input[5]);
            }
            else if (input[0].equals("save")) {
                save();
            }
            else if (input[0].equals("load") ){
                load();
            }
            else if (input[0].equals("remove")) {
                remove();
            }
            else if (input[0].equals("get")) {
                getPerson(input[1],input[2]);
            }
            else if (input[0].equals("modify")) {
                modify(input[1], input[2]);
            }
            else if (input[0].equals("show")) {
                show();
            }
            else {
                System.out.println("Invalid Command\n");
            }
        }
	}

    public static void add(String name, String firstName, String studentID, String email, String gameID) {
        try {
            manager.addPerson(new Person(name,firstName, studentID, email, gameID));
            manager.setSaved(false);
        }
        catch (Exception e) {
            System.out.println("Invalid Details");
        }
    }

    public static void save() {
        try {
            manager.savePersons();
            manager.setSaved(true);
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void load() {
        try {
            checkSaved();
            manager.loadPersons();
        }
        catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static void remove() {
        if (manager.getSelectedPerson() == null) {
            System.out.println("No Person Selected");
        }
        else {
            manager.removePerson();
            manager.setSelectedPerson(null);
            manager.setSaved(false);
        }
    }

    public static void getPerson(String name, String firstName) {
        manager.setSelectedPerson(manager.getPerson(name,firstName));
        if (manager.getSelectedPerson()== null) {
            System.out.println("Cannot find " + name + ", " + firstName);
        }
    }

    public static void modify(String attribute, String newValue) {
        try {
            BeanInfo beanInfo = Introspector.getBeanInfo(manager.getSelectedPerson() != null ? manager.getSelectedPerson().getClass() : null);
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : properties) {
//                System.out.println(property);
                if (property.getName().equals(attribute)) {
                    if (property.getPropertyType().equals(Boolean.class)) {
                        property.getWriteMethod().invoke(manager.getSelectedPerson(), newValue.equals("true") ? true : null);
                    }
                    else {
                        property.getWriteMethod().invoke(manager.getSelectedPerson(), newValue);
                    }
                    manager.setSaved(false);
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void checkSaved() {
        try {
            if (!manager.getSaved()) {
                System.out.println("Save? (Y/N)");
                if (br.readLine().equals("Y")) {
                    save();
                }
            }
        }
        catch (IOException e){
            e.printStackTrace();
        }
    }

    public static void show() {
        try {
            BeanInfo beanInfo= Introspector.getBeanInfo(manager.getSelectedPerson() != null ? manager.getSelectedPerson().getClass() : null);
            PropertyDescriptor[] properties = beanInfo.getPropertyDescriptors();
            for (PropertyDescriptor property : properties) {
                if (!property.getName().equals("class")) {
                    System.out.println(property.getName() + ":\t" + property.getReadMethod().invoke(manager.getSelectedPerson()));
                }
            }
        }
        catch (Exception e) {
            e.printStackTrace();
        }
    }
}