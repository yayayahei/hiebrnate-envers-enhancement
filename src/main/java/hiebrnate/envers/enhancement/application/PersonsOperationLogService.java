package hiebrnate.envers.enhancement.application;

import hiebrnate.envers.enhancement.domain.command.PagingQueryCommand;
import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.services.PersonsOperationLogDomainService;
import hiebrnate.envers.enhancement.domain.entities.OperationLog;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.util.*;

@Service
public class PersonsOperationLogService {
    private PersonsQueryService personsQueryService;
    private PersonsOperationLogDomainService personsOperationLogDomainService;

    public PersonsOperationLogService(PersonsQueryService personsQueryService, PersonsOperationLogDomainService personsOperationLogDomainService) {
        this.personsQueryService = personsQueryService;
        this.personsOperationLogDomainService = personsOperationLogDomainService;
    }

    public Page<OperationLog> getOperationLogs(Long id, PagingQueryCommand pagingQueryCommand)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        PageRequest pageable = PageRequest.of(pagingQueryCommand.getPageNumber(), pagingQueryCommand.getPageSize());
        Person person = personsQueryService.findById(id);
        if (person == null) {
            return new PageImpl<>(List.of(), pageable, 0);
        }
        return personsOperationLogDomainService.getPagedOperationLogs(person, pageable);
    }

}
