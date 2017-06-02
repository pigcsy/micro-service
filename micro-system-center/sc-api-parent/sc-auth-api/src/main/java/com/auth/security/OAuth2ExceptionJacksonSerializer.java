package com.auth.security;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;
import org.springframework.http.HttpStatus;

import java.io.IOException;

/**
 * @author Dave Syer
 */
public class OAuth2ExceptionJacksonSerializer extends StdSerializer<CustomOAuth2Exception> {

    public OAuth2ExceptionJacksonSerializer() {
        super(CustomOAuth2Exception.class);
    }

    @Override
    public void serialize(CustomOAuth2Exception value, com.fasterxml.jackson.core.JsonGenerator gen,
                          com.fasterxml.jackson.databind.SerializerProvider provider) throws IOException {
        gen.writeStartObject();
        gen.writeStringField("code", value.getCode());
        gen.writeStringField("message", value.getMessage());
        gen.writeStringField("exception", value.getException());
        gen.writeStringField("httpStatus", String.valueOf(HttpStatus.INTERNAL_SERVER_ERROR.value()));
        gen.writeEndObject();

    }

}