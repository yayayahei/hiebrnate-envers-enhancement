package hiebrnate.envers.enhancement.infrastructure.configuration.audit;


import hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot.CustomEnversService;
//import hiebrnate.envers.enhancement.infrastructure.configuration.audit.tmp.PostInsert;
import org.hibernate.HibernateException;
import org.hibernate.boot.Metadata;
import org.hibernate.engine.spi.SessionFactoryImplementor;
import org.hibernate.envers.event.spi.*;
import org.hibernate.event.service.spi.EventListenerRegistry;
import org.hibernate.event.spi.EventType;
import org.hibernate.integrator.spi.Integrator;
import org.hibernate.service.spi.SessionFactoryServiceRegistry;
import org.jboss.logging.Logger;

public class CustomIntegrator implements Integrator {
    private static final Logger log = Logger.getLogger( CustomIntegrator.class );

    public static final String AUTO_REGISTER = "hibernate.envers.autoRegisterListeners";

    public void integrate(
        Metadata metadata,
        SessionFactoryImplementor sessionFactory,
        SessionFactoryServiceRegistry serviceRegistry) {
        final CustomEnversService enversService = serviceRegistry.getService( CustomEnversService.class );


        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Verify that the EnversService is fully initialized and ready to go.
        if ( !enversService.isInitialized() ) {
            throw new HibernateException(
                "Expecting EnversService to have been initialized prior to call to EnversIntegrator#integrate"
            );
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Opt-out of registration if no audited entities found
        if ( !enversService.getEntitiesConfigurations().hasAuditedEntities() ) {
            log.debug( "Skipping Envers listener registrations : No audited entities found" );
            return;
        }

        // ~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~~
        // Do the registrations
        final EventListenerRegistry listenerRegistry = serviceRegistry.getService( EventListenerRegistry.class );
        listenerRegistry.addDuplicationStrategy( EnversListenerDuplicationStrategy.INSTANCE );

        if ( enversService.getEntitiesConfigurations().hasAuditedEntities() ) {
            listenerRegistry.appendListeners(
                EventType.POST_DELETE,
                new EnversPostDeleteEventListenerImpl( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.POST_INSERT,
//                new PostInsert( enversService )
                new EnversPostInsertEventListenerImpl( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.PRE_UPDATE,
                new EnversPreUpdateEventListenerImpl( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.POST_UPDATE,
                new PostUpdate( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.POST_COLLECTION_RECREATE,
                new EnversPostCollectionRecreateEventListenerImpl( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.PRE_COLLECTION_REMOVE,
                new EnversPreCollectionRemoveEventListenerImpl( enversService )
            );
            listenerRegistry.appendListeners(
                EventType.PRE_COLLECTION_UPDATE,
                new EnversPreCollectionUpdateEventListenerImpl( enversService )
            );
        }
    }

    @Override
    public void disintegrate(SessionFactoryImplementor sessionFactory, SessionFactoryServiceRegistry serviceRegistry) {
        // nothing to do
    }
}
