package hiebrnate.envers.enhancement.infrastructure.web;

import hiebrnate.envers.enhancement.representation.PersonsFacade;
import hiebrnate.envers.enhancement.representation.dtos.OperationLogDTO;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import hiebrnate.envers.enhancement.representation.dtos.request.CommonPageRequest;
import hiebrnate.envers.enhancement.representation.dtos.response.CommonPageResponse;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
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

    @PutMapping("{id}")
    public void putPerson(@PathVariable Long id, @RequestBody PersonDto personDto) {
        personsFacade.putPerson(id, personDto);
    }

    @GetMapping("{id}/operation-logs")
    public CommonPageResponse<OperationLogDTO> getOperationLogs(@PathVariable Long id,
                                                                CommonPageRequest pageRequest)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        return personsFacade.getPagedOperationLogs(id, pageRequest);
    }

}
