/*
 */

package org.keycloak.services.filters;

import org.jboss.resteasy.spi.ResteasyProviderFactory;
import org.keycloak.models.KeycloakTransaction;

import javax.ws.rs.container.ContainerRequestContext;
import javax.ws.rs.container.ContainerResponseContext;
import javax.ws.rs.container.ContainerResponseFilter;
import java.io.IOException;

/**
 * @author <a href="mailto:sthorger@redhat.com">Stian Thorgersen</a>
 */
public class TransactionCommitter implements ContainerResponseFilter {
    @Override
    public void filter(ContainerRequestContext containerRequestContext, ContainerResponseContext containerResponseContext) throws IOException {
        if (containerRequestContext.getUriInfo().toString().contains("/auth/realms/test/clients/openid-connect")) {
            System.out.println("EXTRA SLEEP");
            try {
                Thread.sleep(10000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
        KeycloakTransaction tx = ResteasyProviderFactory.getContextData(KeycloakTransaction.class);
        if (tx != null && tx.isActive()) {
            if (tx.getRollbackOnly()) tx.rollback();
            else tx.commit();
        }
        System.out.println("EXTRA DONE");

    }
}
