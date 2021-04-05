package hiebrnate.envers.enhancement.representation;

import hiebrnate.envers.enhancement.application.PersonsCommandService;
import hiebrnate.envers.enhancement.application.PersonsQueryService;
import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import hiebrnate.envers.enhancement.representation.dtos.mapper.PersonDtoMapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Component
public class PersonsFacade {
    private PersonsCommandService personsCommandService;
    private PersonsQueryService personsQueryService;

    public PersonsFacade(PersonsCommandService personsCommandService, PersonsQueryService personsQueryService) {
        this.personsCommandService = personsCommandService;
        this.personsQueryService = personsQueryService;
    }

    public void create(PersonDto personDto) {
        personsCommandService.createPerson(PersonDtoMapper.INSTANCE.toPerson(personDto));
    }

    public List<PersonDto> getPersons() {
        List<Person> personList = personsQueryService.getPersons();
        return PersonDtoMapper.INSTANCE.toPersonDtoList(personList);
    }
}
