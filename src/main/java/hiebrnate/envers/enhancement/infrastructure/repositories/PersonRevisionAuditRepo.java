package hiebrnate.envers.enhancement.infrastructure.repositories;

import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.support.PersonRevision;
import hiebrnate.envers.enhancement.domain.repositories.PersonRevisionRepo;
import hiebrnate.envers.enhancement.infrastructure.configuration.audit.CustomAuditReaderFactory;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.query.AuditEntity;
import org.hibernate.envers.query.AuditQuery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class PersonRevisionAuditRepo implements PersonRevisionRepo {
    private EntityManager entityManager;

    public PersonRevisionAuditRepo(EntityManager entityManager) {
        this.entityManager = entityManager;
    }

    @Override
    public Page<PersonRevision> getRevisions(Long id, boolean fetchChanges, PageRequest pageable) {
        AuditReader auditReader = CustomAuditReaderFactory.get(entityManager);
        AuditQuery auditQuery;
        if (fetchChanges) {
            auditQuery = auditReader.createQuery()
                .forRevisionsOfEntityWithChanges(Person.class, true);
        } else {
            auditQuery = auditReader.createQuery()
                .forRevisionsOfEntity(Person.class, true);
        }

        auditQuery.add(AuditEntity.id().eq(id))
            .addOrder(AuditEntity.revisionNumber().desc())
            .setFirstResult(pageable.getPageNumber() * pageable.getPageSize())
            .setMaxResults(pageable.getPageSize() + 1);
        List<Object[]> revisions = auditQuery.getResultList();
        var result = revisions.stream().
            map(revision -> new PersonRevision(List.of(revision)))
            .collect(Collectors.toList());

        return new PageImpl<>(result, pageable, fetchTotalCountOfRevisions(id, auditReader));
    }

    private Integer fetchTotalCountOfRevisions(Long id, AuditReader auditReader) {
        List<Number> revisionNumbers = auditReader.getRevisions(Person.class, id);
        return revisionNumbers.size();
    }
}
