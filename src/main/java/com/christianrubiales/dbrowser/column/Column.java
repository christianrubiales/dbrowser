package com.christianrubiales.dbrowser.column;

import lombok.Data;
import org.hibernate.annotations.Subselect;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Data
@Subselect("SELECT * FROM INFORMATION_SCHEMA.COLUMNS")
public class Column {

    @Id
    private String columnName;

    private String tableCatalog;
    private String tableSchema;
    private String tableName;
    private String ordinalPosition;
    private String columnDefault;
    private String isNullable;
    private String dataType;
    private String characterMaximumLength;
    private String characterOctetLength;

    private String numericPrecision;
    private String numericPrecisionRadix;
    private String numericScale;
    private String datetimePrecision;
    private String intervalType;
    private String intervalPrecision;
    private String characterSetCatalog;
    private String characterSetSchema;
    private String characterSetName;
    private String collationCatalog;

    private String collationSchema;
    private String collationName;
    private String domainCatalog;
    private String domainSchema;
    private String domainName;
    private String udtCatalog;
    private String udtSchema;
    private String udtName;
    private String scopeCatalog;
    private String scopeSchema;

    private String scopeName;
    private String maximumCardinality;
    private String dtdIdentifier;
    private String isSelfReferencing;
    private String isIdentity;
    private String identityGeneration;
    private String identityStart;
    private String identityIncrement;
    private String identityMaximum;
    private String identityMinimum;

    private String identityCycle;
    private String isGenerated;
    private String generationExpression;
//    private String isSystemTimePeriodStart;
//    private String isSystemTimePeriodEnd;
//    private String systemTimePeriodTimestampGeneration;
    private String isUpdatable;
    private String declaredDataType;
    private String declaredNumericPrecision;
    private String declaredNumericScale;
}
