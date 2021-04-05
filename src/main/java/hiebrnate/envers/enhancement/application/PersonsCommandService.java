package hiebrnate.envers.enhancement.application;

import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.repositories.PersonsRepository;
import org.springframework.data.repository.query.ParameterOutOfBoundsException;
import org.springframework.stereotype.Service;

@Service
public class PersonsCommandService {
    private PersonsRepository personsRepository;

    public PersonsCommandService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    public void createPerson(Person person) {
        personsRepository.save(person);
    }

    public void putPerson(Long id, Person person) {
        var existingPerson = personsRepository.getOne(id);
        if (existingPerson == null) {
            throw new ParameterOutOfBoundsException("Person not exist", null);
        }
        existingPerson.setName(person.getName());
        personsRepository.save(existingPerson);
    }
}
