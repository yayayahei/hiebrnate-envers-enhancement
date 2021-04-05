package hiebrnate.envers.enhancement.domain.repositories;

import hiebrnate.envers.enhancement.domain.support.PersonRevision;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;

public interface PersonRevisionRepo {
    Page<PersonRevision> getRevisions(Long id, boolean fetchChanges, PageRequest pageable);
}
