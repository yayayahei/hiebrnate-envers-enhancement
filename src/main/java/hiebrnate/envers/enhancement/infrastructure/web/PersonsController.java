package hiebrnate.envers.enhancement.infrastructure.web;

import hiebrnate.envers.enhancement.representation.PersonsFacade;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/persons")
public class PersonsController {
    private PersonsFacade personsFacade;

    public PersonsController(PersonsFacade personsFacade) {
        this.personsFacade = personsFacade;
    }

    @PostMapping("")
    public void createPerson(@RequestBody PersonDto personDto) {
        personsFacade.create(personDto);
    }

    @GetMapping("")
    public List<PersonDto> getPersons() {
        return personsFacade.getPersons();
    }
}
