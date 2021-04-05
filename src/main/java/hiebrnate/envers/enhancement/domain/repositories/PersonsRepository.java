package hiebrnate.envers.enhancement.domain.repositories;

import hiebrnate.envers.enhancement.domain.entities.Person;
import hiebrnate.envers.enhancement.domain.repositories.generics.Repo;

public interface PersonsRepository extends Repo.Save<Person>, Repo.FindAll<Person>, Repo.FindById<Person> {

}
