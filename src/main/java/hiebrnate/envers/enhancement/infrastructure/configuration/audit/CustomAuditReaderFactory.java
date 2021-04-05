package hiebrnate.envers.enhancement.infrastructure.configuration.audit;

import hiebrnate.envers.enhancement.infrastructure.configuration.audit.boot.CustomEnversService;
import org.hibernate.Session;
import org.hibernate.engine.spi.SessionImplementor;
import org.hibernate.envers.AuditReader;
import org.hibernate.envers.exception.AuditException;
import org.hibernate.envers.internal.reader.AuditReaderImpl;
import org.hibernate.service.ServiceRegistry;

import javax.persistence.EntityManager;

public class CustomAuditReaderFactory {
    private CustomAuditReaderFactory() {
    }

    /**
     * Create an audit reader associated with an open session.
     *
     * @param session An open session.
     *
     * @return An audit reader associated with the given sesison. It shouldn't be used
     *         after the session is closed.
     *
     * @throws AuditException When the given required listeners aren't installed.
     */
    public static AuditReader get(Session session) throws AuditException {
        SessionImplementor sessionImpl;
        if ( !(session instanceof SessionImplementor) ) {
            sessionImpl = (SessionImplementor) session.getSessionFactory().getCurrentSession();
        }
        else {
            sessionImpl = (SessionImplementor) session;
        }

        final ServiceRegistry serviceRegistry = sessionImpl.getFactory().getServiceRegistry();
        final CustomEnversService enversService = serviceRegistry.getService( CustomEnversService.class );

        return new AuditReaderImpl( enversService, session, sessionImpl );
    }

    /**
     * Create an audit reader associated with an open entity manager.
     *
     * @param entityManager An open entity manager.
     *
     * @return An audit reader associated with the given entity manager. It shouldn't be used
     *         after the entity manager is closed.
     *
     * @throws AuditException When the given entity manager is not based on Hibernate, or if the required
     * listeners aren't installed.
     */
    public static AuditReader get(EntityManager entityManager) throws AuditException {
        if ( entityManager.getDelegate() instanceof Session ) {
            return get( (Session) entityManager.getDelegate() );
        }

        if ( entityManager.getDelegate() instanceof EntityManager ) {
            return get( (EntityManager) entityManager.getDelegate() );
        }

        throw new AuditException( "Hibernate EntityManager not present!" );
    }
}
