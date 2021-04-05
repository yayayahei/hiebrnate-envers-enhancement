package hiebrnate.envers.enhancement.domain.value_object;

import lombok.Data;

import javax.persistence.Embeddable;

@Embeddable
@Data
public class Name {
    private String firstName;
    private String lastName;
    private String fullName;
    private String nickName;
    private String pseudonym;
}
