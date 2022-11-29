package com.iz.sandbox.avro.data;

import org.apache.avro.LogicalType;
import org.apache.avro.LogicalTypes;
import org.apache.avro.Schema;

public class OffsetDateTimeLogicalTypeFactory implements LogicalTypes.LogicalTypeFactory {
    public static final String LOGICAL_TYPE = "offset-date-time";

    @Override
    public LogicalType fromSchema(Schema schema) {
        return new LogicalType(LOGICAL_TYPE);
    }

    @Override
    public String getTypeName() {
        return LOGICAL_TYPE;
    }
}
