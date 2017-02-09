package com.exchange.backend.persistence.repositories;

import com.exchange.ExchangeApplication;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.SpringApplicationConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.junit.Assert.*;

/**
 * Created by Mrs Hoang on 08/02/2017.
 */
@RunWith(SpringRunner.class)
@SpringApplicationConfiguration(ExchangeApplication.class)
public class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Test
    public void test() throws Exception{

    }

}