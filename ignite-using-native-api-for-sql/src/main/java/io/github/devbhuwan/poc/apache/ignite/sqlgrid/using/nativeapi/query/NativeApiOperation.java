package io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.query;

import io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model.Employee;
import io.github.devbhuwan.poc.apache.ignite.sqlgrid.using.nativeapi.model.EmployeeSalary;
import org.apache.ignite.Ignite;
import org.apache.ignite.IgniteCache;
import org.apache.ignite.Ignition;
import org.apache.ignite.cache.CacheMode;
import org.apache.ignite.cache.query.SqlFieldsQuery;
import org.apache.ignite.configuration.CacheConfiguration;

import java.util.List;

/**
 * Created by ganesh on 06/05/17.
 */
public class NativeApiOperation {

    private static final String EMPLOYEE_CACHE = "EmployeeCache";
    private static final String EMPLOYEE_SALARY_CACHE = "EmployeeSalaryCache";

    Ignite ignite = null;
    IgniteCache<Long, Employee> cacheEmployee;

    public NativeApiOperation() {
        ignite = Ignition.start();
        CacheConfiguration<Long, Employee> cacheConfigEmployee = new CacheConfiguration<>(EMPLOYEE_CACHE);
        CacheConfiguration<Long, EmployeeSalary> cacheConfigEmployeeSalary = new CacheConfiguration<>(EMPLOYEE_SALARY_CACHE);

        cacheConfigEmployee.setCacheMode(CacheMode.PARTITIONED);
        cacheConfigEmployee.setIndexedTypes(Long.class, Employee.class);

        cacheConfigEmployeeSalary.setCacheMode(CacheMode.PARTITIONED);
        cacheConfigEmployeeSalary.setIndexedTypes(Long.class, EmployeeSalary.class);

        cacheEmployee = ignite.getOrCreateCache(cacheConfigEmployee);
        ignite.getOrCreateCache(cacheConfigEmployeeSalary);
    }

    public List<List<?>> getEmployeeByName(String name) {
        SqlFieldsQuery selectQuery = new SqlFieldsQuery("select * from Employee e where e.name='" + name + "'");
        List<List<?>> employees = cacheEmployee.query(selectQuery).getAll();
        return employees;
    }


    public void addEmployeeData(String name, String address, int age) {
        Employee employee = new Employee(name, address, age);
        cacheEmployee.put(employee.getId(), employee);
    }
}
