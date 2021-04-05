package hiebrnate.envers.enhancement.domain.support;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.envers.EntityTrackingRevisionListener;
import org.hibernate.envers.RevisionType;

import java.io.Serializable;

@Slf4j
public class AuditRevisionListener implements EntityTrackingRevisionListener {

    @Override
    public void newRevision(Object revisionEntity) {
//        AuditRevision audit = (AuditRevision) revisionEntity;
    }

    @Override
    public void entityChanged(Class entityClass, String entityName, Serializable entityId,
                              RevisionType revisionType, Object revisionEntity) {
        log.info("entityClass: {}", entityClass.getName());
        log.info("entityName: {}", entityName);
        log.info("entityId: {}", entityId);
        log.info("revisionType: {}", revisionType);
        log.info("revisionEntity: {}", revisionEntity);
    }
}
