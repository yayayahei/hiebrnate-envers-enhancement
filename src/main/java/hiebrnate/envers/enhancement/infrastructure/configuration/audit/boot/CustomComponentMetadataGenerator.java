package hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot;

import org.dom4j.Element;
import org.hibernate.envers.configuration.internal.metadata.EntityXmlMappingData;
import org.hibernate.envers.configuration.internal.metadata.reader.ComponentAuditingData;
import org.hibernate.envers.configuration.internal.metadata.reader.PropertyAuditingData;
import org.hibernate.envers.internal.entities.mapper.CompositeMapperBuilder;
import org.hibernate.envers.internal.tools.ReflectionTools;
import org.hibernate.mapping.Component;
import org.hibernate.mapping.Property;
import org.hibernate.mapping.Value;

import java.util.Iterator;
import java.util.Map;

public class CustomComponentMetadataGenerator {
    private final CustomAuditMetadataGenerator mainGenerator;

    CustomComponentMetadataGenerator(CustomAuditMetadataGenerator auditMetadataGenerator) {
        mainGenerator = auditMetadataGenerator;
    }

    @SuppressWarnings({"unchecked"})
    public void addComponent(
        Element parent, PropertyAuditingData propertyAuditingData,
        Value value, CompositeMapperBuilder mapper, String entityName,
        EntityXmlMappingData xmlMappingData, boolean firstPass) {
        final Component propComponent = (Component) value;

        final Class componentClass;
        if ( propComponent.isDynamic() ) {
            componentClass = ReflectionTools.loadClass(
                Map.class.getCanonicalName(),
                mainGenerator.getClassLoaderService()
            );

        }
        else {
            componentClass = ReflectionTools.loadClass(
                propComponent.getComponentClassName(),
                mainGenerator.getClassLoaderService()
            );
        }
        final CompositeMapperBuilder componentMapper = mapper.addComponent(
            propertyAuditingData.getPropertyData(),
            componentClass
        );

        // The property auditing data must be for a component.
        final ComponentAuditingData componentAuditingData = (ComponentAuditingData) propertyAuditingData;

        // Adding all properties of the component
        final Iterator<Property> properties = (Iterator<Property>) propComponent.getPropertyIterator();
        while ( properties.hasNext() ) {
            final Property property = properties.next();

            final PropertyAuditingData componentPropertyAuditingData =
                componentAuditingData.getPropertyAuditingData( property.getName() );

            // Checking if that property is audited
            if ( componentPropertyAuditingData != null ) {
                mainGenerator.addValue(
                    parent, property.getValue(), componentMapper, entityName, xmlMappingData,
                    componentPropertyAuditingData, property.isInsertable(), firstPass, true
                );
            }
        }
    }
}
