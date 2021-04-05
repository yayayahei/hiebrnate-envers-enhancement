package hiebrnate.envers.enhancement.application;

import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.domain.repositories.PersonsRepository;
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
}
