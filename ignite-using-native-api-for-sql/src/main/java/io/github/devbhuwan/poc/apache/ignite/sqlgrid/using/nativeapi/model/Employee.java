package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model;

import lombok.Getter;
import lombok.Setter;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.affinity.AffinityKey;
import org.apache.ignite.cache.query.annotations.QuerySqlField;

import java.io.Serializable;
import java.util.concurrent.atomic.AtomicLong;

/**
 * Created by ganesh on 06/05/17.
 */
@Getter
@Setter
public class Employee implements Serializable {

    private static final AtomicLong ID_GEN = new AtomicLong();

    @QuerySqlField(index = true)
    private Long id;

    @QuerySqlField
    private String name;

    @QuerySqlField
    private String address;

    @QuerySqlField
    private int age;


    public Employee(String name, String address, int age) {
        this.id = ID_GEN.incrementAndGet();
        this.name = name;
        this.address = address;
        this.age = age;
    }


}
