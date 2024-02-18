package org.sanedge.infrastructure.configuration;

import org.sanedge.domain.feature.CreateUser;
import org.sanedge.domain.feature.FindUserById;
import org.sanedge.domain.feature.FindUserByUsername;
import org.sanedge.domain.feature.LoginUser;
import org.sanedge.domain.feature.UpdateUser;
import org.sanedge.domain.feature.impl.CreateUserImpl;
import org.sanedge.domain.feature.impl.FindUserByIdImpl;
import org.sanedge.domain.feature.impl.FindUserByUsernameImpl;
import org.sanedge.domain.feature.impl.LoginUserImpl;
import org.sanedge.domain.feature.impl.UpdateUserImpl;
import org.sanedge.domain.model.provider.HashProvider;
import org.sanedge.domain.model.user.UserModelBuilder;
import org.sanedge.domain.model.user.UserRepository;
import org.sanedge.domain.validator.ModelValidator;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Singleton;
import jakarta.validation.Validator;
import jakarta.ws.rs.Produces;

@Dependent
public class UsersConfiguration {

    @Produces
    @Singleton
    public CreateUser createUser(
            UserRepository userRepository, HashProvider hashProvider, UserModelBuilder userBuilder) {
        return new CreateUserImpl(userRepository, hashProvider, userBuilder);
    }

    @Produces
    @Singleton
    public UpdateUser updateUser(
            FindUserById findUserById, UserRepository userRepository, ModelValidator modelValidator) {
        return new UpdateUserImpl(findUserById, userRepository, modelValidator);
    }

    @Produces
    @Singleton
    public FindUserById findUserById(UserRepository userRepository) {
        return new FindUserByIdImpl(userRepository);
    }

    @Produces
    @Singleton
    public LoginUser loginUser(UserRepository userRepository, HashProvider hashProvider) {
        return new LoginUserImpl(userRepository, hashProvider);
    }

    @Produces
    @Singleton
    public FindUserByUsername findUserByUsername(UserRepository userRepository) {
        return new FindUserByUsernameImpl(userRepository);
    }

    @Produces
    @Singleton
    public UserModelBuilder userModelBuilder(ModelValidator modelValidator) {
        return new UserModelBuilder(modelValidator);
    }

    @Produces
    @Singleton
    public ModelValidator modelValidator(Validator validator) {
        return new ModelValidator(validator);
    }
}