package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model;

import lombok.Data;
import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by ganesh on 06/05/17.
 */
@Getter
@Setter
public class EmployeeSalary {

    @QuerySqlField(index = true,name = "empSalId")
    private int id;

    @QuerySqlField
    private BigDecimal salary;

    @QuerySqlField
    private Employee employee;

    private Date date;


}
