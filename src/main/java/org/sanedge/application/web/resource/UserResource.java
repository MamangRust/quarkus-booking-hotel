package org.sanedge.application.web.resource;

import java.util.UUID;

import org.sanedge.application.web.model.request.UpdateUserRequest;
import org.sanedge.application.web.model.response.UserResponse;
import org.sanedge.domain.feature.FindUserById;
import org.sanedge.domain.feature.UpdateUser;
import org.sanedge.domain.model.constants.ValidationMessages;
import org.sanedge.infrastructure.web.provider.TokenProvider;
import org.sanedge.infrastructure.web.security.annotation.Secured;
import org.sanedge.infrastructure.web.security.profile.Role;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.PUT;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.core.SecurityContext;
import lombok.AllArgsConstructor;

@Path("/user")
@AllArgsConstructor
public class UserResource {

    private final FindUserById findUserById;
    private final UpdateUser updateUser;
    private final TokenProvider tokenProvider;

    @GET
    @Secured({ Role.ADMIN, Role.USER })
    @Produces(MediaType.APPLICATION_JSON)
    public Response getUser(@Context SecurityContext securityContext) {
        final var userId = UUID.fromString(securityContext.getUserPrincipal().getName());
        final var user = findUserById.handle(userId);
        final var token = tokenProvider.createUserToken(user.getId().toString());
        return Response.ok(new UserResponse(user, token)).status(Response.Status.OK).build();
    }

    @PUT
    @Transactional
    @Secured({ Role.ADMIN, Role.USER })
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response update(
            @Context SecurityContext securityContext,
            @Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_BE_NOT_NULL) UpdateUserRequest updateUserRequest) {
        final var userId = UUID.fromString(securityContext.getUserPrincipal().getName());
        final var user = updateUser.handle(updateUserRequest.toUpdateUserInput(userId));
        final var token = tokenProvider.createUserToken(user.getId().toString());
        return Response.ok(new UserResponse(user, token)).status(Response.Status.OK).build();
    }
}