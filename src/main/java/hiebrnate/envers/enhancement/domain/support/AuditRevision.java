package hiebrnate.envers.enhancement.domain.support;


import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.Builder;
import lombok.Data;
import lombok.EqualsAndHashCode;
import org.hibernate.envers.DefaultRevisionEntity;
import org.hibernate.envers.RevisionEntity;

import javax.persistence.*;

@EqualsAndHashCode(callSuper = true)
@Data
@Entity
@Table(name = "revision_info")
@AttributeOverrides({
    @AttributeOverride(name = "timestamp", column = @Column(name = "rev_timestamp")),
    @AttributeOverride(name = "id", column = @Column(name = "revision_id"))
})
@RevisionEntity(AuditRevisionListener.class)
@Builder
@JsonIgnoreProperties({"hibernateLazyInitializer", "handler"})
public class AuditRevision extends DefaultRevisionEntity {
    private static final long serialVersionUID = 1L;

}
