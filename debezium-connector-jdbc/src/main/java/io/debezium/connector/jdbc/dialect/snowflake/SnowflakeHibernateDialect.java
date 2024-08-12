/*
 * Copyright Debezium Authors.
 *
 * Licensed under the Apache Software License version 2.0, available at http://www.apache.org/licenses/LICENSE-2.0
 */
package io.debezium.connector.jdbc.dialect.snowflake;

import static org.hibernate.type.SqlTypes.BLOB;
import static org.hibernate.type.SqlTypes.CLOB;
import static org.hibernate.type.SqlTypes.FLOAT;
import static org.hibernate.type.SqlTypes.NCHAR;
import static org.hibernate.type.SqlTypes.NCLOB;
import static org.hibernate.type.SqlTypes.NVARCHAR;
import static org.hibernate.type.SqlTypes.TIMESTAMP_WITH_TIMEZONE;
import static org.hibernate.type.SqlTypes.TIME_UTC;
import static org.hibernate.type.SqlTypes.TIME_WITH_TIMEZONE;

import org.hibernate.dialect.DatabaseVersion;

public class SnowflakeHibernateDialect extends org.hibernate.dialect.Dialect {
    @Override
    public DatabaseVersion getVersion() {
        return new DatabaseVersion() {
            @Override
            public int getDatabaseMajorVersion() {
                return 0;
            }

            @Override
            public int getDatabaseMinorVersion() {
                return 0;
            }
        };
    }

    @Override
    protected String columnType(int sqlTypeCode) {
        return switch (sqlTypeCode) {
            case NVARCHAR -> "varchar($l)";
            case CLOB, NCLOB -> "varchar";
            case NCHAR -> "char($l)";
            case BLOB -> "binary";
            case FLOAT -> "float";
            case TIME_WITH_TIMEZONE, TIME_UTC -> "time($p)";
            case TIMESTAMP_WITH_TIMEZONE -> "timestamp_tz($p)";
            default -> super.columnType(sqlTypeCode);
        };
    }
}
