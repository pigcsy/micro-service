package com.core.base;

import com.core.listenters.SpringHolde;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.freemarker.FreeMarkerAutoConfiguration;
import org.springframework.boot.autoconfigure.sendgrid.SendGridAutoConfiguration;
import org.springframework.boot.autoconfigure.session.SessionAutoConfiguration;
import org.springframework.boot.autoconfigure.websocket.WebSocketAutoConfiguration;
import org.springframework.boot.web.support.SpringBootServletInitializer;
import org.springframework.context.annotation.Import;


@SpringBootApplication(exclude = {WebSocketAutoConfiguration.class, SessionAutoConfiguration.class,
        SendGridAutoConfiguration.class, FreeMarkerAutoConfiguration.class})
@Import(SpringHolde.class)
public class DefaultSpringbootInitializer extends SpringBootServletInitializer {

}
