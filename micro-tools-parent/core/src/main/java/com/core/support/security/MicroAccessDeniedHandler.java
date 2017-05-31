package com.core.support.security;

import com.core.exception.MicroException;
import com.core.exception.UnPermissionException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.AccessDeniedException;
import org.springframework.security.oauth2.common.exceptions.OAuth2Exception;
import org.springframework.security.oauth2.provider.error.AbstractOAuth2SecurityExceptionHandler;
import org.springframework.security.oauth2.provider.error.DefaultWebResponseExceptionTranslator;
import org.springframework.security.oauth2.provider.error.WebResponseExceptionTranslator;
import org.springframework.security.web.access.AccessDeniedHandler;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

public class MicroAccessDeniedHandler extends AbstractOAuth2SecurityExceptionHandler implements AccessDeniedHandler {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private WebResponseExceptionTranslator exceptionTranslator = new DefaultWebResponseExceptionTranslator();
    private ExceptionRenderer exceptionRenderer = new ExceptionRenderer();

    public MicroAccessDeniedHandler() {

    }

    public MicroAccessDeniedHandler(ExceptionRenderer exceptionRenderer) {
        this.exceptionRenderer = exceptionRenderer;
    }

    protected void handleHttpEntityResponse(ResponseEntity<OAuth2Exception> result, HttpServletResponse response,
                                            Exception ex) {
        MicroException exception = null;
        try {
            if (OAuth2Exception.ACCESS_DENIED.equalsIgnoreCase(result.getBody().getOAuth2ErrorCode())) {
                exception = new com.core.exception.IllegalAccessException(result.getBody().getMessage());
            } else if (result.getStatusCode() == HttpStatus.UNAUTHORIZED
                    || OAuth2Exception.UNAUTHORIZED_CLIENT.equalsIgnoreCase(result.getBody().getOAuth2ErrorCode())) {
                exception = new com.core.exception.UnPermissionException(result.getBody().getMessage());
            } else {
                exception = new UnPermissionException(result.getBody().getMessage());
            }
        } catch (Exception e) {
            exception = new MicroException("未知错误");
        } finally {
            logger.error("鉴权失败", ex);
            exceptionRenderer.handleHttpEntityResponse(response, exception, HttpStatus.UNAUTHORIZED);
        }

    }

    @Override
    public void handle(HttpServletRequest request, HttpServletResponse response,
                       AccessDeniedException accessDeniedException) throws IOException, ServletException {
        try {
            ResponseEntity<OAuth2Exception> result = exceptionTranslator.translate(accessDeniedException);
            handleHttpEntityResponse(result, response, accessDeniedException);
        } catch (Exception e) {
            handleHttpEntityResponse(null, response, accessDeniedException);
        }
    }

}
