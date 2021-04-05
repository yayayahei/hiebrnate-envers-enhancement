package hiebrnate.envers.enhancement.infrastructure.configuration.audit;

import lombok.extern.slf4j.Slf4j;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.internal.entities.PropertyData;
import org.hibernate.envers.internal.entities.mapper.ComponentPropertyMapper;
import org.hibernate.envers.internal.entities.mapper.PropertyMapper;
import org.hibernate.envers.internal.tools.ReflectionTools;

import java.util.Map;

@Slf4j
public class CustomMultiPropertyMapper {


    private PropertyData propertyData;
    private ComponentPropertyMapper propertyMapper;

    public CustomMultiPropertyMapper(PropertyData propertyData, ComponentPropertyMapper propertyMapper) {
        this.propertyData = propertyData;
        this.propertyMapper = propertyMapper;
    }

    public void mapModifiedFlagsToMapFromEntity(
        SessionImplementor session,
        Map<String, Object> data,
        Object newObj,
        Object oldObj) {
        log.info("{} ,{}", oldObj, newObj);

        for (Map.Entry<PropertyData, PropertyMapper> entry : propertyMapper.getProperties().entrySet()){
            var singlePropertyData = entry.getKey();
            var singlePropertyMapper=entry.getValue();
            singlePropertyMapper.mapModifiedFlagsToMapFromEntity(
                session, data,
                newObj == null ? null : ReflectionTools.getGetter(
                    newObj.getClass(),
                    singlePropertyData,
                    session.getFactory().getServiceRegistry()
                ).get( newObj ),
                oldObj == null ? null : ReflectionTools.getGetter(
                    oldObj.getClass(),
                    singlePropertyData,
                    session.getFactory().getServiceRegistry()
                ).get( oldObj )
            );
        }
    }
}
