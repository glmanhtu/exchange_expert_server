package com.exchange.backend.persistence.converter;

import org.joda.time.LocalDateTime;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;

/**
 * Created by Mrs Hoang on 12/15/2016.
 */
public class LocalDateTimeConverter
        implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public final Timestamp convertToDatabaseColumn(
            final LocalDateTime localDateTime) {
        return (localDateTime == null
                ? null : new Timestamp(localDateTime.toDateTime().getMillis()));
    }

    @Override
    public final LocalDateTime convertToEntityAttribute(
            final Timestamp timestamp) {
        return (timestamp == null
                ? null : new LocalDateTime(timestamp.getTime()));
    }
}
