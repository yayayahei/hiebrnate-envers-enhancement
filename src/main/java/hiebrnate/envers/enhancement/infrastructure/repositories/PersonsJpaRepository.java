package hiebrnate.envers.enhancement.infrastructure.repositories;

import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.domain.repositories.PersonsRepository;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PersonsJpaRepository extends PersonsRepository, JpaRepository<Person,Long> {
}
