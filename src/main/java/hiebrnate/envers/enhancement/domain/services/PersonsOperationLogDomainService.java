package hiebrnate.envers.enhancement.domain.services;

import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.repositories.PersonRevisionRepo;
import hiebrnate.envers.enhancement.domain.entities.OperationLog;
import hiebrnate.envers.enhancement.domain.entities.OperationLogDetail;
import hiebrnate.envers.enhancement.domain.support.PersonRevision;
import org.apache.commons.beanutils.PropertyUtils;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import java.lang.reflect.InvocationTargetException;
import java.text.SimpleDateFormat;
import java.util.*;

import static hiebrnate.envers.enhancement.utils.Constants.CHINA_TIME_ZONE;

@Service
public class PersonsOperationLogDomainService
{

    private final PersonRevisionRepo personRevisionRepo;

    public PersonsOperationLogDomainService(PersonRevisionRepo personRevisionRepo) {
        this.personRevisionRepo = personRevisionRepo;
    }

    public Page<OperationLog> getPagedOperationLogs(Person person,
                                                    PageRequest pageable)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        var personRevisionsPage =
            personRevisionRepo.getRevisions(person.getId(), true, pageable);
        List<OperationLog> operationLogs = new ArrayList<>();
        var personRevisions = personRevisionsPage.getContent();
        for (int i = 0; i < personRevisions.size(); i++) {
            var currentRevision = personRevisions.get(i);
            var previousRevision = (i == personRevisions.size() - 1) ? null : personRevisions.get(i + 1);
            if (operationLogs.size() < pageable.getPageSize()) {
                operationLogs.add(
                    getOperationLog(previousRevision, currentRevision)
                );
            }
        }

        return new PageImpl<>(operationLogs, pageable, personRevisionsPage.getTotalElements());
    }


    public OperationLog getOperationLog(PersonRevision previousPersonRevision, PersonRevision currentPersonRevision)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {

        return OperationLog.builder()
            .dataTable("Person")
            .operationType(currentPersonRevision.getRevisionType().toString())
            .operationTime(currentPersonRevision.getAuditRevision().getRevisionDate())
            .dataId(currentPersonRevision.getPerson().getId())
            .operationLogDetails(getOperationLogDetails(previousPersonRevision, currentPersonRevision))
            .build();
    }

    @SuppressWarnings({"PMD.AvoidLiteralsInIfCondition"})
    public List<OperationLogDetail> getOperationLogDetails(PersonRevision previousPersonRevision,
                                                           PersonRevision currentPersonRevision)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (previousPersonRevision == null) {
            return null;
        }
        List<OperationLogDetail> operationLogDetails = new ArrayList<>();
        List<String> changed = currentPersonRevision.getChangedProperties();
        for (String changedProperty : changed) {
            changedProperty = changedProperty.replace("_", ".");
            operationLogDetails.add(OperationLogDetail.builder()
                .fieldName(changedProperty)
                .oldValue(populateValue(previousPersonRevision.getPerson(), changedProperty))
                .newValue(populateValue(currentPersonRevision.getPerson(), changedProperty))
                .build());
        }
        return operationLogDetails;
    }

    private String populateValue(Object bean, String name)
        throws IllegalAccessException, NoSuchMethodException, InvocationTargetException {
        if (PropertyUtils.getProperty(bean, name) == null) {
            return null;
        }
        if (PropertyUtils.getPropertyType(bean, name).equals(Date.class)) {
            SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.CHINA);
            simpleDateFormat.setTimeZone(TimeZone.getTimeZone(CHINA_TIME_ZONE));
            return simpleDateFormat.format(PropertyUtils.getProperty(bean, name));
        } else {
            return String.valueOf(PropertyUtils.getProperty(bean, name));
        }

    }
}
