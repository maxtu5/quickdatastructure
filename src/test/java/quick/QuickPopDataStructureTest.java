package quick;

import org.junit.jupiter.api.Test;
import util.Person;

import java.time.LocalDate;
import java.util.Comparator;

import static org.junit.jupiter.api.Assertions.*;

class QuickPopDataStructureTest {

    @Test
    void testBoxed() {
        QuickDataStructure<Integer> structureInt = new QuickPopDataStructure<>();
        assertNull(structureInt.pop());
        structureInt.push(3);
        structureInt.push(7);
        structureInt.push(7);
        structureInt.push(2);
        structureInt.push(4);
        assertEquals(7, structureInt.pop());
        assertEquals(7, structureInt.pop());
        assertEquals(4, structureInt.pop());
        assertEquals(3, structureInt.pop());
        assertEquals(2, structureInt.pop());
        assertNull(structureInt.pop());
    }

    @Test
    void testString() {
        QuickDataStructure<String> structureString = new QuickPopDataStructure<>();
        structureString.push("Mary");
        structureString.push("had");
        structureString.push("a");
        structureString.push("little");
        structureString.push("lamb");
        assertEquals("little", structureString.pop());
        assertEquals("lamb", structureString.pop());
        assertEquals("had", structureString.pop());
        assertEquals("a", structureString.pop());
        assertEquals("Mary", structureString.pop());
    }

    @Test
    void testStringOverrideComparator() {
        QuickDataStructure<String> structureString = new QuickPopDataStructure<>(new Comparator<String>() {
            @Override
            public int compare(String o1, String o2) {
                return o1.compareToIgnoreCase(o2);
            }
        });

        structureString.push("Mary");
        structureString.push("had");
        structureString.push("a");
        structureString.push("little");
        structureString.push("lamb");
        assertEquals("Mary", structureString.pop());
        assertEquals("little", structureString.pop());
        assertEquals("lamb", structureString.pop());
        assertEquals("had", structureString.pop());
        assertEquals("a", structureString.pop());
    }

    @Test
    void testPerson() {
        QuickDataStructure<Person> structurePerson = new QuickPopDataStructure<>(new Comparator<Person>() {
            @Override
            public int compare(Person o1, Person o2) {
                return o1.getBirth().compareTo(o2.getBirth());
            }
        });
        structurePerson.push(new Person("Smith", LocalDate.parse("1979-03-18")));
        structurePerson.push(new Person("Wissotzky", LocalDate.parse("2001-10-15")));
        structurePerson.push(new Person("Adams", LocalDate.parse("1988-08-21")));
        assertEquals("Wissotzky", structurePerson.pop().getName());
        assertEquals("Adams", structurePerson.pop().getName());
        assertEquals("Smith", structurePerson.pop().getName());
    }

    @Test
    void testNotComparable() {
        QuickDataStructure<Person> structurePersonByName = new QuickPopDataStructure<>();
        structurePersonByName.push(new Person("Smith", LocalDate.parse("1999-03-18")));
        assertThrows(ClassCastException.class, () ->
                structurePersonByName.push(new Person("Wissotzky", LocalDate.parse("2001-10-15")))
        );
    }

}