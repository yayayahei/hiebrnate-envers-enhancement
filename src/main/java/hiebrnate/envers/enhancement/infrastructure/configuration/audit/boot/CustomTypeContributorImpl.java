package hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot;

import org.hibernate.boot.model.TypeContributions;
import org.hibernate.boot.model.TypeContributor;
import org.hibernate.envers.internal.entities.RevisionTypeType;
import org.hibernate.service.ServiceRegistry;

public class CustomTypeContributorImpl implements TypeContributor {
    @Override
    public void contribute(TypeContributions typeContributions, ServiceRegistry serviceRegistry) {
        final CustomEnversService enversService = serviceRegistry.getService(CustomEnversService.class);
        if (!enversService.isEnabled()) {
            return;
        }

        typeContributions.contributeType(
            new RevisionTypeType(),
            new String[]{RevisionTypeType.class.getName()}
        );
    }

}
