package hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot;

import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.service.spi.ServiceContributor;

public class CustomEnversServiceContributor implements ServiceContributor {
    @Override
    public void contribute(StandardServiceRegistryBuilder serviceRegistryBuilder) {
        serviceRegistryBuilder.addInitiator( CustomEnversServiceInitiator.INSTANCE );
    }
}