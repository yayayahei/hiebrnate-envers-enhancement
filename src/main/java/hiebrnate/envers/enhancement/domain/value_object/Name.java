package hiebrnate.envers.enhancement.domain.value_object;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.Audited;

import javax.persistence.Embeddable;

@Embeddable
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Name {
    @Audited(withModifiedFlag = true,modifiedColumnName = "first_name_mod")
    private String firstName;
    @Audited(withModifiedFlag = true,modifiedColumnName = "last_name_mod")
    private String lastName;
    @Audited(withModifiedFlag = true,modifiedColumnName = "full_name_mod")
    private String fullName;
    @Audited(withModifiedFlag = true,modifiedColumnName = "nick_name_mod")
    private String nickName;
    @Audited(withModifiedFlag = true,modifiedColumnName = "pseudonym_mod")
    private String pseudonym;
}
