package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @author Bhuwan Upadhyay
 * @date 2017/05/05
 */
@Getter
@Setter
public class Payment implements Serializable {

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String detail;

    @QuerySqlField
    private BigDecimal amount;

}
