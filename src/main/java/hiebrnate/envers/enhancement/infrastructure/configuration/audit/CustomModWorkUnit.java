package hiebrnate.envers.enhancement.infrastructure.configuration.audit;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.boot.internal.EnversService;
import org.hibernate.envers.internal.entities.mapper.ExtendedPropertyMapper;
import org.hibernate.envers.internal.synchronization.work.ModWorkUnit;
import org.hibernate.persister.entity.EntityPersister;

import java.io.Serializable;

public class CustomModWorkUnit extends ModWorkUnit {
    private final ExtendedPropertyMapper propertyMapper;
    private final ComponentPropertyMapperWithModifiedFlag componentPropertyMapper;

    public CustomModWorkUnit(SessionImplementor sessionImplementor,
                             String entityName,
                             EnversService enversService, Serializable id,
                             EntityPersister entityPersister,
                             Object[] newState,
                             Object[] oldState) {
        super(sessionImplementor, entityName, enversService, id, entityPersister, newState, oldState);
        this.propertyMapper = enversService.getEntitiesConfigurations().get(getEntityName()).getPropertyMapper();
        this.componentPropertyMapper = new ComponentPropertyMapperWithModifiedFlag(propertyMapper);
        componentPropertyMapper.fillDataWithModifiedFlag(
            sessionImplementor,
            entityPersister.getPropertyNames(),
            newState, oldState, getData());
    }
}
