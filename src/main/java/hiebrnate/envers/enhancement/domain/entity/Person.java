package hiebrnate.envers.enhancement.domain.entity;

import hiebrnate.envers.enhancement.domain.value_object.Name;
import lombok.Data;

import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Data
@Table(name = "persons")
public class Person {
    @Id
    private Long id;

    @Embedded
    private Name name;

}
