package org.sanedge.infrastructure.configuration;

import org.sanedge.infrastructure.web.qualifiers.NoWrapRootValueObjectMapper;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.fasterxml.jackson.databind.SerializationFeature;
import com.fasterxml.jackson.datatype.jsr310.JavaTimeModule;

import jakarta.enterprise.context.Dependent;
import jakarta.inject.Singleton;
import jakarta.ws.rs.Produces;

@Dependent
public class ApplicationConfiguration {
    @Singleton
    @Produces
    public ObjectMapper objectMapper() {
        ObjectMapper objectMapper = new ObjectMapper();
        objectMapper.enable(SerializationFeature.WRAP_ROOT_VALUE);
        objectMapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);

        objectMapper.registerModule(new JavaTimeModule());

        return objectMapper;
    }

    @Singleton
    @Produces
    @NoWrapRootValueObjectMapper
    public ObjectMapper noWrapRootValueObjectMapper() {
        return new ObjectMapper().registerModule(new JavaTimeModule());
    }
}
