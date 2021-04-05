package hiebrnate.envers.enhancement.representation.dtos;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class NameDto {
    private String firstName;
    private String lastName;
    private String fullName;
    private String nickName;
    private String pseudonym;
}
