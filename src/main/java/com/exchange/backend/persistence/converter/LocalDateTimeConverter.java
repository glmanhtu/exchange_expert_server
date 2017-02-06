package com.exchange.backend.persistence.converter;

import javax.persistence.AttributeConverter;
import java.sql.Timestamp;
import java.time.LocalDateTime;

/**
 * Created by Mrs Hoang on 12/15/2016.
 */
public class LocalDateTimeConverter
        implements AttributeConverter<LocalDateTime, Timestamp> {

    @Override
    public final Timestamp convertToDatabaseColumn(
            final LocalDateTime localDateTime) {
        return (localDateTime == null
                ? null : Timestamp.valueOf(localDateTime));
    }

    @Override
    public final LocalDateTime convertToEntityAttribute(
            final Timestamp timestamp) {
        return (timestamp == null
                ? null : timestamp.toLocalDateTime());
    }
}
