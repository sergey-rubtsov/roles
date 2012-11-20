package com.roles.assignment.service;

import com.roles.assignment.domain.Person;
import com.roles.assignment.repository.PersonRepository;
import com.roles.assignment.ui.QueryMetaData;
import com.roles.assignment.utils.Paging;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class PersonServiceImpl implements PersonService {

	@Autowired
    PersonRepository personRepository;

	public long countAllPeople() {
        return personRepository.count();
    }

	public void deletePerson(Person person) {
        personRepository.delete(person);
    }

	public Person findPerson(String id) {
        return personRepository.findOne(id);
    }

    public List<Person> findByLastNameSortByFirstNameLike(String lastName, int first, int max, int offset) {
        Sort sort = new Sort(Sort.Direction.ASC, "firstName");
        return personRepository.findByLastNameLike(lastName, new Paging(first, max, offset, sort));
    }

	public List<Person> findAllPeople() {
        return (List<Person>) personRepository.findAll();
    }

	public Person savePerson(Person person) {
        return personRepository.save(person);
    }

	public List<Person> findPersonEntries(QueryMetaData queryMetaData) {
        return findAllPeople();
/*		List<Person> people = null;
		if (queryMetaData.getPropertyName() == null) {
			people = findAllPeople();
		} else {
			//people = personRepository.findByLastNameLike('%' + queryMetaData.getSearchTerm() + '%', new PageRequest(0, 10));
			StringBuffer methodName = new StringBuffer(20);
			methodName.append("findBy");
			methodName.append(StringUtils.capitalize(queryMetaData.getPropertyName()));
			Class[] paramTypes = new Class[2];
			Object searchTerm = null;
			if (!queryMetaData.getPropertyName().equals("postalCode")) {
			  methodName.append("Like");
			  paramTypes[0] = String.class;
			  searchTerm = '%' + queryMetaData.getSearchTerm() + '%';
			} else {
				paramTypes[0] = Integer.class;
				searchTerm = Integer.valueOf(queryMetaData.getSearchTerm());
			}
			paramTypes[1] = Pageable.class;
			System.out.println("personRepository.getClass() = " + personRepository.getClass() + ", methodName = #" + methodName.toString() + "#");
			Method method = ReflectionUtils.findMethod(personRepository.getClass(), methodName.toString(), paramTypes);
			people = (List<Person>)ReflectionUtils.invokeMethod(method, personRepository, new Object[] { searchTerm , new PageRequest(0, 100)});  
		}
		return people;*/
	}
}
