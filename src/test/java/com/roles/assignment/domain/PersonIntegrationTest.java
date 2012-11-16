package com.roles.assignment.domain;

import com.roles.assignment.repository.PersonRepository;
import com.roles.assignment.repository.UserRepository;
import com.roles.assignment.service.PersonService;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Configurable;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

@Configurable
@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(locations = "classpath:/META-INF/spring/applicationContext*.xml")
@Transactional
public class PersonIntegrationTest {

	@Autowired
    PersonService personService;

	@Autowired
    PersonRepository personRepository;

    @Autowired
    UserRepository userRepository;

    private List<Person> data;

    @Before
    public void setUp() throws Exception {
        this.data = new ArrayList<Person>();
        for (int i = 0; i < 20; i++) this.data.add(getNewTransientPerson(i));
    }

    @Test
    public void testMarkerMethod() {
    }    
    
    @Test
    public void testNotNullService() {
        Assert.assertNotNull(personService);
    }

    @Test
    public void testNotNullRepo() {
        Assert.assertNotNull(personRepository);
    }

    @Test
    public void testGetUserLogin() throws Exception {
        Person person = data.get(0);
        User user = UserIntegrationTest.getUser();
        
        personRepository.save(person).getId();

        person.setUser(user);
        String personId = personRepository.save(person).getId();
        person = personRepository.findOne(personId);

        Assert.assertNotNull(person);
        Assert.assertEquals(personId, person.getId());
        Assert.assertEquals(user.getLogin(), person.getUser().getLogin());
    }

    @Test
    public void testGetUserNoCascade() throws Exception {
        Person person = data.get(0);
        User user = UserIntegrationTest.getUser();

        String personId = personRepository.save(person).getId();
        String userId = userRepository.save(user).getId();

        person = personRepository.findOne(personId);
        user = userRepository.findOne(userId);

        person.setUser(user);
        user.setPerson(person);

        personId = personRepository.save(person).getId();
        userId = userRepository.save(user).getId();

        person = personRepository.findOne(personId);
        user = userRepository.findOne(userId);

        Assert.assertNotNull(person);
        Assert.assertNotNull(user);

        Assert.assertEquals(personId, person.getId());
        Assert.assertEquals(user.getLogin(), person.getUser().getLogin());
        Assert.assertEquals(user.getPerson().getFirstName(), person.getFirstName());
        Assert.assertEquals(user.getPerson().getId(), person.getId());
        Assert.assertEquals(person.getUser().getId(), user.getId());

        User childUser = person.getUser();
        Person childPerson = user.getPerson();

        Assert.assertEquals(childUser.getLogin(), childPerson.getUser().getLogin());
        Assert.assertEquals(childUser.getPerson().getFirstName(), childPerson.getFirstName());
        Assert.assertEquals(childUser.getPerson().getId(), childPerson.getId());
        Assert.assertEquals(childPerson.getUser().getId(), childUser.getId());
    }

    @Test
    public void testGetUserCascade() throws Exception {
        Person person = data.get(0);
        User user = UserIntegrationTest.getUser();

        String userId = userRepository.save(user).getId();
        personRepository.save(person).getId();
        person.setUser(user);
        String personId = personRepository.save(person).getId();

        person = personRepository.findOne(personId);

        Assert.assertNotNull(person);
        Assert.assertEquals(personId, person.getId());

        user = person.getUser();

        Assert.assertNotNull(user.getId());
        Assert.assertEquals(UserIntegrationTest.getUser().getLogin(), user.getLogin());
        Assert.assertEquals(userId, user.getId());

        Assert.assertNotNull(person.getFirstName());
/*      Assert.assertNotNull(user.getPerson());

        Assert.assertEquals(user.getPerson().getFirstName(), person.getFirstName());
        Assert.assertEquals(user.getPerson().getId(), person.getId());
        Assert.assertEquals(person.getUser().getId(), user.getId());*/
    }

    @Test
    public void testCountAllPeople() {
        for (Person person : data) {
            personService.savePerson(person);
        }
        long count = personService.countAllPeople();
        Assert.assertTrue("Counter for 'Person' incorrectly reported there were no entries", count > 0);
    }

    @Test
    public void testFindByLastNameLike() {
        ArrayList<Person> list = new ArrayList<Person>();
        for (int i = 0; i < 5; i++) {
            Person person = new Person();
            person.setEmail("mail" + i);
            if (i < 4) {
                person.setLastName("Similar");
                person.setFirstName("name" + i);
            } else {
                person.setLastName("Not equals");
                person.setFirstName("test");
            }
            personService.savePerson(person);
        }
        List<Person> found = personService.findByLastNameSortByFirstNameLike("Similar", 0, 5, 0);
        if (found.size() < 5) {
            found.size();
        }
        Assert.assertEquals(4, found.size());
        found = personService.findByLastNameSortByFirstNameLike("Not equals", 0, 5, 0);
        Assert.assertEquals(1, found.size());
        Assert.assertEquals("test", found.get(0).getFirstName());
    }    
    
	@Test
    public void testFindPerson() {
        Person obj = data.get(0);
        String id = personService.savePerson(obj).getId();
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", obj);
        Assert.assertNotNull("Data on demand for 'Person' failed to provide an identifier", id);
        for (int i = 1; i < data.size(); i++) {
            personService.savePerson(data.get(i));
        }        
        obj = personService.findPerson(id);
        Assert.assertNotNull("Find method for 'Person' illegally returned null for id '" + id + "'", obj);
        Assert.assertEquals("Find method for 'Person' returned the incorrect identifier", id, obj.getId());
    }

/*	@Test
    public void testFindAllPeople() {
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", dod.getRandomPerson());
        long count = personService.countAllPeople();
        Assert.assertTrue("Too expensive to perform a find all test for 'Person', as there are " + count + " entries; set the findAllMaximum to exceed this value or set findAll=false on the integration test annotation to disable the test", count < 250);
        List<Person> result = personService.findAllPeople();
        Assert.assertNotNull("Find all method for 'Person' illegally returned null", result);
        Assert.assertTrue("Find all method for 'Person' failed to return any data", result.size() > 0);
    }*/

/*	@Test
    public void testFindPersonEntries() {
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", dod.getRandomPerson());
        long count = personService.countAllPeople();
        if (count > 20) count = 20;
        int firstResult = 0;
        int maxResults = (int) count;
        List<Person> result = personService.findPersonEntries(firstResult, maxResults);
        Assert.assertNotNull("Find entries method for 'Person' illegally returned null", result);
        Assert.assertEquals("Find entries method for 'Person' returned an incorrect number of entries", count, result.size());
    }*/

/*	@Test
    public void testFlush() {
        Person obj = dod.getRandomPerson();
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Person' failed to provide an identifier", id);
        obj = personService.findPerson(id);
        Assert.assertNotNull("Find method for 'Person' illegally returned null for id '" + id + "'", obj);
        boolean modified =  dod.modifyPerson(obj);
        Integer currentVersion = obj.getVersion();
        personRepository.flush();
        Assert.assertTrue("Version for 'Person' failed to increment on flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testUpdatePersonUpdate() {
        Person obj = dod.getRandomPerson();
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Person' failed to provide an identifier", id);
        obj = personService.findPerson(id);
        boolean modified =  dod.modifyPerson(obj);
        Integer currentVersion = obj.getVersion();
        Person merged = personService.updatePerson(obj);
        personRepository.flush();
        Assert.assertEquals("Identifier of merged object not the same as identifier of original object", merged.getId(), id);
        Assert.assertTrue("Version for 'Person' failed to increment on merge and flush directive", (currentVersion != null && obj.getVersion() > currentVersion) || !modified);
    }

	@Test
    public void testSavePerson() {
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", dod.getRandomPerson());
        Person obj = dod.getNewTransientPerson(Integer.MAX_VALUE);
        Assert.assertNotNull("Data on demand for 'Person' failed to provide a new transient entity", obj);
        Assert.assertNull("Expected 'Person' identifier to be null", obj.getId());
        personService.savePerson(obj);
        personRepository.flush();
        Assert.assertNotNull("Expected 'Person' identifier to no longer be null", obj.getId());
    }

	@Test
    public void testDeletePerson() {
        Person obj = dod.getRandomPerson();
        Assert.assertNotNull("Data on demand for 'Person' failed to initialize correctly", obj);
        Long id = obj.getId();
        Assert.assertNotNull("Data on demand for 'Person' failed to provide an identifier", id);
        obj = personService.findPerson(id);
        personService.deletePerson(obj);
        personRepository.flush();
        Assert.assertNull("Failed to remove 'Person' with identifier '" + id + "'", personService.findPerson(id));
    }*/

    public Person getNewTransientPerson(int index) {
        Person obj = new Person();
        setCity(obj, index);
        setEmail(obj, index);
        setFirstName(obj, index);
        setLastName(obj, index);
        setPhoneNumber(obj, index);
        setPostalCode(obj, index);
        setStreetAddress(obj, index);
        return obj;
    }

    public void setCity(Person obj, int index) {
        String city = "city_" + index;
        if (city.length() > 20) {
            city = city.substring(0, 20);
        }
        obj.setCity(city);
    }

    public void setEmail(Person obj, int index) {
        String email = "foo" + index + "@bar.com";
        obj.setEmail(email);
    }

    public void setFirstName(Person obj, int index) {
        String firstName = "firstName_" + index;
        obj.setFirstName(firstName);
    }

    public void setLastName(Person obj, int index) {
        String lastName = "lastName_" + index;
        obj.setLastName(lastName);
    }

    public void setPhoneNumber(Person obj, int index) {
        String phoneNumber = "phoneNumber_" + index;
        obj.setPhoneNumber(phoneNumber);
    }

    public void setPostalCode(Person obj, int index) {
        Integer postalCode = new Integer(index);
        if (postalCode > 99999) {
            postalCode = 99999;
        }
        obj.setPostalCode(postalCode);
    }

    public void setStreetAddress(Person obj, int index) {
        String streetAddress = "streetAddress_" + index;
        if (streetAddress.length() > 80) {
            streetAddress = streetAddress.substring(0, 80);
        }
        obj.setStreetAddress(streetAddress);
    }

    public static ArrayList<Person> createTestPersonData() {
        final String[] fnames = { "Peter", "Alice", "Joshua", "Mike", "Olivia",
                "Nina", "Alex", "Rita", "Dan", "Umberto", "Henrik", "Rene",
                "Lisa", "Marge" };
        final String[] lnames = { "Smith", "Gordon", "Simpson", "Brown",
                "Clavel", "Simons", "Verne", "Scott", "Allison", "Gates",
                "Rowling", "Barks", "Ross", "Schneider", "Tate" };
        final String cities[] = { "Amsterdam", "Berlin", "Helsinki",
                "Hong Kong", "London", "Luxemburg", "New York", "Oslo",
                "Paris", "Rome", "Stockholm", "Tokyo", "Turku" };
        final String streets[] = { "4215 Blandit Av.", "452-8121 Sem Ave",
                "279-4475 Tellus Road", "4062 Libero. Av.", "7081 Pede. Ave",
                "6800 Aliquet St.", "P.O. Box 298, 9401 Mauris St.",
                "161-7279 Augue Ave", "P.O. Box 496, 1390 Sagittis. Rd.",
                "448-8295 Mi Avenue", "6419 Non Av.",
                "659-2538 Elementum Street", "2205 Quis St.",
                "252-5213 Tincidunt St.", "P.O. Box 175, 4049 Adipiscing Rd.",
                "3217 Nam Ave", "P.O. Box 859, 7661 Auctor St.",
                "2873 Nonummy Av.", "7342 Mi, Avenue",
                "539-3914 Dignissim. Rd.", "539-3675 Magna Avenue",
                "Ap #357-5640 Pharetra Avenue", "416-2983 Posuere Rd.",
                "141-1287 Adipiscing Avenue", "Ap #781-3145 Gravida St.",
                "6897 Suscipit Rd.", "8336 Purus Avenue", "2603 Bibendum. Av.",
                "2870 Vestibulum St.", "Ap #722 Aenean Avenue",
                "446-968 Augue Ave", "1141 Ultricies Street",
                "Ap #992-5769 Nunc Street", "6690 Porttitor Avenue",
                "Ap #105-1700 Risus Street",
                "P.O. Box 532, 3225 Lacus. Avenue", "736 Metus Street",
                "414-1417 Fringilla Street", "Ap #183-928 Scelerisque Road",
                "561-9262 Iaculis Avenue" };
        ArrayList<Person> c = null;
        Random r = new Random(0);
        try {
            c = new ArrayList<Person>();
            for (int i = 0; i < 100; i++) {
                Person p = new Person();
                p.setFirstName(fnames[r.nextInt(fnames.length)]);
                p.setLastName(lnames[r.nextInt(lnames.length)]);
                p.setCity(cities[r.nextInt(cities.length)]);
                p.setEmail(p.getFirstName().toLowerCase() + "."
                        + p.getLastName().toLowerCase() + "@assignment.com");
                p.setPhoneNumber("+358 02 555 " + r.nextInt(10) + r.nextInt(10)
                        + r.nextInt(10) + r.nextInt(10));
                int n = r.nextInt(100000);
                if (n < 10000) {
                    n += 10000;
                }
                p.setPostalCode(n);
                p.setStreetAddress(streets[r.nextInt(streets.length)]);
                c.add(p);
            }
        } catch (Exception e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return c;
    }
}
