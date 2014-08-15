package nz.ac.massey.tcosy.PersonelSystem;

import org.junit.Test;

/**
 * Created by cloud202 on 15/08/14.
 */
public class Tests {

    @Test
    public void test1() {
        PersonManager manager = new PersonManager("test.xml");
        manager.addPerson(new Person("Osborne","Mitchell","12110243","cloud202@hotmail.com","Cruskit_King"));

    }

}
