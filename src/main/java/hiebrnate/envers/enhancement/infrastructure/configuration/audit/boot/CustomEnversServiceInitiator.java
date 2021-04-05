package hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot;

import org.hibernate.boot.registry.StandardServiceInitiator;
import org.hibernate.service.spi.ServiceRegistryImplementor;

import java.util.Map;

public class CustomEnversServiceInitiator implements StandardServiceInitiator<CustomEnversService> {
    /**
     * Singleton access
     */
    public static final CustomEnversServiceInitiator INSTANCE = new CustomEnversServiceInitiator();

    @Override
    public CustomEnversService initiateService(
        Map configurationValues,
        ServiceRegistryImplementor registry) {
        return new CustomEnversServiceImpl();
    }

    @Override
    public Class<CustomEnversService> getServiceInitiated() {
        return CustomEnversService.class;
    }
}
