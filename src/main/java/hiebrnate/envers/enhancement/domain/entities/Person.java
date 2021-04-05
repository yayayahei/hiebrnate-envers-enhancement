package hiebrnate.envers.enhancement.domain.entities;

import hiebrnate.envers.enhancement.domain.value_object.Name;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
@Table(name = "persons")
@Audited
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Audited
    @Embedded
    private Name name;

    @Audited(withModifiedFlag = true)
    private String lastOperationUser;

}
