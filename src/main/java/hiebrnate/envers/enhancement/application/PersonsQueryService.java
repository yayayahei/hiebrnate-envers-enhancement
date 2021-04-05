package hiebrnate.envers.enhancement.application;

import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.domain.repositories.PersonsRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class PersonsQueryService {

    public PersonsQueryService(PersonsRepository personsRepository) {
        this.personsRepository = personsRepository;
    }

    private PersonsRepository personsRepository;

    public List<Person> getPersons() {
        return personsRepository.findAll();
    }
}
