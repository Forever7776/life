package com.forever7776.life.core.receiver;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.amqp.core.Message;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Service;
import org.springframework.amqp.core.MessageListener;

/**
 * @author kz
 * @date 2018-01-04
 */
@Service
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
public class SayReceiver implements MessageListener {
    private final static Logger logger = LoggerFactory.getLogger(SayReceiver.class);

    @Override
    public void onMessage(Message message) {
        System.out.println("say hello");
    }
}
