package com.auth.security;

import java.io.IOException;
import java.util.Map.Entry;

import org.codehaus.jackson.JsonGenerator;
import org.codehaus.jackson.JsonProcessingException;
import org.codehaus.jackson.map.JsonSerializer;
import org.codehaus.jackson.map.SerializerProvider;
import org.springframework.http.HttpStatus;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;

import com.fasterxml.jackson.databind.ser.std.StdSerializer;

/**
 * @author Dave Syer
 *
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