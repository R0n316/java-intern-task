package com.radlance.javainterntask;

import com.radlance.javainterntask.dto.EmployeeDto;
import com.radlance.javainterntask.service.EmployeeService;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.time.LocalDate;
import java.util.List;

@Component
public class EmployeeServiceConsoleWrapper {
    private final EmployeeService employeeService;

    @Autowired
    public EmployeeServiceConsoleWrapper(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public void printEmployeeInfoById(Integer id) {
        try {
            System.out.printf("Employee with id %d: %s%n", id, employeeService.findById(id));
        } catch (EntityNotFoundException e) {
            System.out.println(e.getMessage());
        }
    }

    public void printGroupedNameInfo() {
        System.out.printf("Grouped names: %s%n", employeeService.groupByName());
    }

    public void printEmployeeInfoBornBetweenDates(LocalDate startDate, LocalDate endDate) {
        List<EmployeeDto> employees = employeeService.findBetween(startDate, endDate);

        if (employees.isEmpty()) {
            System.out.println("No employees were born between the specified dates");
        } else {
            System.out.printf("Employees born from %s to %s: %s%n", startDate, endDate, employees);
        }
    }
}
