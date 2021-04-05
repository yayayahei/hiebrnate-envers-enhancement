package hiebrnate.envers.enhancement.domain.support;

import hiebrnate.envers.enhancement.domain.entities.Person;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.envers.RevisionType;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class PersonRevision {
    private Person person;
    private AuditRevision auditRevision;
    private RevisionType revisionType;
    private List<String> changedProperties;

    @SuppressWarnings({"checkstyle:MagicNumber"})
    public PersonRevision(List<Object> personRevision) {
        this.setPerson((Person) personRevision.get(0));
        this.setAuditRevision((AuditRevision) personRevision.get(1));
        this.setRevisionType((RevisionType) personRevision.get(2));
        this.setChangedProperties(new ArrayList<>((HashSet) personRevision.get(3)));
    }

    public Object[] toRawObjectList() {
        return new Object[]{
            person,
            auditRevision,
            revisionType,
            new HashSet<>(changedProperties)
        };
    }
}
