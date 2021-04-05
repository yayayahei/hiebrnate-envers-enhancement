package hiebrnate.envers.enhancement.infrastructure.configuration.audit;

import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.internal.entities.PropertyData;
import org.hibernate.envers.internal.entities.mapper.ComponentPropertyMapper;
import org.hibernate.envers.internal.entities.mapper.ExtendedPropertyMapper;
import org.hibernate.envers.internal.entities.mapper.PropertyMapper;

import java.security.AccessController;
import java.security.PrivilegedAction;
import java.util.Map;

public class ComponentPropertyMapperWithModifiedFlag {

    private final ExtendedPropertyMapper componentPropertyMapper;


    public ComponentPropertyMapperWithModifiedFlag(ExtendedPropertyMapper componentPropertyMapper) {
        this.componentPropertyMapper = componentPropertyMapper;
    }

    public void fillDataWithModifiedFlag(final SessionImplementor session,
                                         String[] propertyNames,
                                         Object[] newState,
                                         Object[] oldState,
                                         Map<String, Object> data) {
        for (int i = 0; i < propertyNames.length; i++) {
            final String propertyName = propertyNames[i];
            final Object newObj = getAtIndexOrNull(newState, i);
            final Object oldObj = getAtIndexOrNull(oldState, i);
            mapModifiedFlagsToMapFromEntity(session, data, newObj, oldObj, propertyName);

        }
    }

    protected Object getAtIndexOrNull(Object[] array, int index) {
        return array == null ? null : array[index];
    }


    public void mapModifiedFlagsToMapFromEntity(
        final SessionImplementor session,
        final Map<String, Object> data,
        final Object newObj,
        final Object oldObj,
        String propertyName) {
        AccessController.doPrivileged(
            new PrivilegedAction<Object>() {
                @Override
                public Object run() {
                    for (Map.Entry<PropertyData, PropertyMapper> entry
                        : componentPropertyMapper.getProperties().entrySet()) {
                        final PropertyData propertyData = entry.getKey();
                        final PropertyMapper propertyMapper = entry.getValue();
                        if (propertyData.getName().equals(propertyName)
                            && propertyMapper instanceof ComponentPropertyMapper) {
                            new CustomMultiPropertyMapper(propertyData, (ComponentPropertyMapper) propertyMapper)
                                .mapModifiedFlagsToMapFromEntity(session, data, newObj, oldObj);
                        }
                    }

                    return null;
                }
            }
        );
    }

}
