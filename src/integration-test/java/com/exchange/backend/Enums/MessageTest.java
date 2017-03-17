package com.exchange.backend.Enums;

import com.exchange.backend.enums.MessageEnum;
import com.exchange.backend.persistence.domain.Message;
import org.junit.Test;

/**
 * Created by greenlucky on 3/17/17.
 */
public class MessageTest {

    @Test
    public void message() throws Exception{
        String badWordDes = "abc";
        Message message = new Message(MessageEnum.BAD_WORD_DES, badWordDes);
        System.out.println(message.getMessage().toString());
    }
}
