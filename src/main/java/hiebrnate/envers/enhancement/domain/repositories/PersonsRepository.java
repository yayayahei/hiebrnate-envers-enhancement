package hiebrnate.envers.enhancement.domain.repositories;

import hiebrnate.envers.enhancement.domain.entity.Person;
import hiebrnate.envers.enhancement.domain.repositories.generics.Repo;

public interface PersonsRepository extends Repo.Save<Person>, Repo.FindAll<Person> {

}
