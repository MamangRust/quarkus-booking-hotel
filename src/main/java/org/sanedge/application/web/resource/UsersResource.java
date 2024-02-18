package org.sanedge.application.web.resource;

import org.sanedge.application.web.model.request.LoginRequest;
import org.sanedge.application.web.model.request.NewUserRequest;
import org.sanedge.application.web.model.response.UserResponse;
import org.sanedge.domain.exception.InvalidPasswordException;
import org.sanedge.domain.exception.UserNotFoundException;
import org.sanedge.domain.feature.CreateUser;
import org.sanedge.domain.feature.LoginUser;
import org.sanedge.domain.model.constants.ValidationMessages;
import org.sanedge.domain.model.user.User;
import org.sanedge.infrastructure.web.exception.UnauthorizedException;
import org.sanedge.infrastructure.web.provider.TokenProvider;

import jakarta.transaction.Transactional;
import jakarta.validation.Valid;
import jakarta.validation.constraints.NotNull;
import jakarta.ws.rs.Consumes;
import jakarta.ws.rs.POST;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.Context;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lombok.AllArgsConstructor;

@Path("/users")
@AllArgsConstructor
public class UsersResource {

    private final CreateUser createUser;
    private final LoginUser loginUser;
    private final TokenProvider tokenProvider;

    @POST
    @Transactional
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response create(
            @Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_BE_NOT_NULL) NewUserRequest newUserRequest,
            @Context SecurityException context) {
        final var user = createUser.handle(newUserRequest.toCreateUserInput());
        final var token = tokenProvider.createUserToken(user.getId().toString());
        return Response.ok(new UserResponse(user, token)).status(Response.Status.CREATED).build();
    }

    @POST
    @Path("/login")
    @Consumes(MediaType.APPLICATION_JSON)
    @Produces(MediaType.APPLICATION_JSON)
    public Response login(
            @Valid @NotNull(message = ValidationMessages.REQUEST_BODY_MUST_BE_NOT_NULL) LoginRequest loginRequest) {
        User user;
        try {
            user = loginUser.handle(loginRequest.toLoginUserInput());
        } catch (UserNotFoundException | InvalidPasswordException ex) {
            throw new UnauthorizedException();
        }
        final var token = tokenProvider.createUserToken(user.getId().toString());
        return Response.ok(new UserResponse(user, token)).status(Response.Status.OK).build();
    }
}