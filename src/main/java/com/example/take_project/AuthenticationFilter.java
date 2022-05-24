package com.example.take_project;

import com.example.take_project.models.UserToken;
import com.example.take_project.otherstuff.annotations.Secured;
import com.example.take_project.otherstuff.exceptions.UserTokenExpiredException;
import com.example.take_project.otherstuff.exceptions.UserTokenNotFoundException;
import com.example.take_project.services.UserTokenServiceInterface;
import jakarta.annotation.Priority;
import jakarta.ejb.EJB;
import jakarta.ws.rs.Priorities;
import jakarta.ws.rs.core.HttpHeaders;
import jakarta.ws.rs.core.SecurityContext;
import jakarta.ws.rs.ext.Provider;

import jakarta.ws.rs.container.ContainerRequestContext;
import jakarta.ws.rs.container.ContainerRequestFilter;

import jakarta.ws.rs.core.Response;

import java.io.IOException;
import java.security.Principal;
import java.sql.Timestamp;

@Secured
@Provider
@Priority(Priorities.AUTHENTICATION)
public class AuthenticationFilter implements ContainerRequestFilter {
    @EJB
    UserTokenServiceInterface userTokenServiceInterface;
    private static final String REALM = "example";
    private static final String AUTHENTICATION_SCHEME = "Bearer";

    @Override
    public void filter(ContainerRequestContext containerRequestContext) throws IOException {
        String authorizationHeader = containerRequestContext.getHeaderString(HttpHeaders.AUTHORIZATION);

        if (!isTokenBasedAuthentication(authorizationHeader)){
            abortWithUnauthorized(containerRequestContext);
            return;
        }

        String token = authorizationHeader.substring(AUTHENTICATION_SCHEME.length()).trim();

        try {
            validateToken(token);
            final SecurityContext currentSecurityContext = containerRequestContext.getSecurityContext();
            containerRequestContext.setSecurityContext(new SecurityContext() {
                @Override
                public Principal getUserPrincipal() {
                    return () -> {
                        try {
                            return userTokenServiceInterface.getTokenByName(token).getUser().getName();
                        } catch (UserTokenNotFoundException e) {
                            throw new RuntimeException(e);
                        }
                    };
                }

                @Override
                public boolean isUserInRole(String s) {
                    return true;
                }

                @Override
                public boolean isSecure() {
                    return currentSecurityContext.isSecure();
                }

                @Override
                public String getAuthenticationScheme() {
                    return AUTHENTICATION_SCHEME;
                }
            });
        }
        catch (Exception e){
            abortWithUnauthorized(containerRequestContext);
        }
    }

    private boolean isTokenBasedAuthentication(String authorizationHeader){
        return authorizationHeader != null && authorizationHeader.toLowerCase().startsWith(AUTHENTICATION_SCHEME.toLowerCase() + "");
    }

    private void abortWithUnauthorized(ContainerRequestContext requestContext){
        requestContext.abortWith(
                Response.status(Response.Status.UNAUTHORIZED)
                        .header(HttpHeaders.WWW_AUTHENTICATE,
                                AUTHENTICATION_SCHEME + " realm=\""+REALM+"\"")
                        .build());
    }

    private void validateToken(String token) throws UserTokenExpiredException, UserTokenNotFoundException {
        UserToken userToken = userTokenServiceInterface.getTokenByName(token);
        if (userToken.getExpirationTime().before(new Timestamp(System.currentTimeMillis()))) throw new UserTokenExpiredException();
    }
}
