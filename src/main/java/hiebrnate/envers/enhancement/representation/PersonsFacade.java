package hiebrnate.envers.enhancement.representation;

import hiebrnate.envers.enhancement.application.PersonsCommandService;
import hiebrnate.envers.enhancement.application.PersonsOperationLogService;
import hiebrnate.envers.enhancement.application.PersonsQueryService;
import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.representation.dtos.OperationLogDTO;
import hiebrnate.envers.enhancement.representation.dtos.PersonDto;
import hiebrnate.envers.enhancement.representation.dtos.mapper.CommonPageRequestMapper;
import hiebrnate.envers.enhancement.representation.dtos.mapper.OperationLogMapper;
import hiebrnate.envers.enhancement.representation.dtos.mapper.PersonDtoMapper;
import hiebrnate.envers.enhancement.representation.dtos.request.CommonPageRequest;
import hiebrnate.envers.enhancement.representation.dtos.response.CommonPageResponse;
import org.springframework.stereotype.Component;

import java.lang.reflect.InvocationTargetException;
import java.util.List;

@Component
public class PersonsFacade {
    private PersonsCommandService personsCommandService;
    private PersonsQueryService personsQueryService;
    private PersonsOperationLogService personsOperationLogService;

    public PersonsFacade(PersonsCommandService personsCommandService, PersonsQueryService personsQueryService, PersonsOperationLogService personsOperationLogService) {
        this.personsCommandService = personsCommandService;
        this.personsQueryService = personsQueryService;
        this.personsOperationLogService = personsOperationLogService;
    }

    public void create(PersonDto personDto) {
        personsCommandService.createPerson(PersonDtoMapper.INSTANCE.toPerson(personDto));
    }

    public List<PersonDto> getPersons() {
        List<Person> personList = personsQueryService.getPersons();
        return PersonDtoMapper.INSTANCE.toPersonDtoList(personList);
    }

    public CommonPageResponse<OperationLogDTO> getPagedOperationLogs(Long id, CommonPageRequest pageRequest)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var operationLogs = personsOperationLogService.getOperationLogs(id,
            CommonPageRequestMapper.INSTANCE.toCommand(pageRequest));
        CommonPageResponse<OperationLogDTO> response = CommonPageResponse.of(operationLogs);
        response.setContent(OperationLogMapper.INSTANCE.toOperationLogDTOList(operationLogs.getContent()));
        return response;
    }

    public void putPerson(Long id, PersonDto personDto) {
        personsCommandService.putPerson(id, PersonDtoMapper.INSTANCE.toPerson(personDto));
    }
}
