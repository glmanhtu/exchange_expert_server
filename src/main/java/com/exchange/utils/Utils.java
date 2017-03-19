package com.exchange.utils;

import java.util.Date;

/**
 * Created by optimize on 3/19/17.
 */
public class Utils {

    private Utils() {

    }

    public static Long getCurrentTimestamp() {
        return new Date().getTime() / Constants.MILISECOND;
    }
}
