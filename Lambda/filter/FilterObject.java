package Lambda.filter;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

class Employee{
    String name;
    int salary;

    public Employee(String name, int salary) {
        this.name=name;
        this.salary=salary;
    }

    public String toString() {
        return name + " - " + salary;
    }

    
}

public class FilterObject {
    public static void main(String[] args) {
        List<Employee> employee = Arrays.asList(
            new Employee("Shubhra", 55000),
            new Employee("Aman", 80000),
            new Employee("Tusar", 85000),
            new Employee("Nilanjan", 100000)
        );

        List<Employee> res = employee.stream().filter(emp->emp.salary>60000).collect(Collectors.toList());
        System.out.println(res);
    }
}
