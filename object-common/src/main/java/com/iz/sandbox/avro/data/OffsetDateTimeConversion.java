package com.iz.sandbox.avro.data;

import org.apache.avro.Conversion;
import org.apache.avro.LogicalType;
import org.apache.avro.Schema;

import java.time.OffsetDateTime;
import java.time.format.DateTimeFormatter;

public class OffsetDateTimeConversion extends Conversion<OffsetDateTime> {
    private static final DateTimeFormatter DATE_TIME_FORMATTER = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.nnnnnnZZZZZ");


    @Override
    public Class<OffsetDateTime> getConvertedType() {
        return OffsetDateTime.class;
    }

    @Override
    public String getLogicalTypeName() {
        return OffsetDateTimeLogicalTypeFactory.LOGICAL_TYPE;
    }

    @Override
    public OffsetDateTime fromCharSequence(CharSequence value, Schema schema, LogicalType type) {
        return OffsetDateTime.parse(value, DATE_TIME_FORMATTER);
    }

    @Override
    public CharSequence toCharSequence(OffsetDateTime value, Schema schema, LogicalType type) {
        return value.format(DATE_TIME_FORMATTER);
    }
}
