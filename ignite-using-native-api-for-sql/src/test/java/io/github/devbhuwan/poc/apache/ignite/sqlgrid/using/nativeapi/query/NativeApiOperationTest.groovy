package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.query

import io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model.Employee
import org.apache.ignite.cache.query.SqlFieldsQuery
import org.apache.ignite.cache.query.SqlQuery
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by ganesh on 06/05/17.
 */
public class NativeApiOperationTest {

    private NativeApiOperation apiOperation;

    @Before
    public void setup() {
        apiOperation = new NativeApiOperation();
    }

    @Test
    public void initTest() {
        String name = "Ganesh Joshi";
        apiOperation.addEmployeeData(name, "Nepal", 20);
        apiOperation.addEmployeeData(name, "USA", 29);
        def employees = apiOperation.getEmployeeByName(name);
        Assert.assertTrue(employees.size() == 2);
    }

}
