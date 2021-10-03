package com.udacity.jdnd.course3.critter.user;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.time.DayOfWeek;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

@Service
@Transactional
public class EmployeeService {
    @Autowired
    private EmployeeRepository employeeRepository;

    public Employee getEmployeeById(Long id) {
        return employeeRepository.getOne(id);
    }

    public List<Employee> findAllEmployee(){
        return employeeRepository.findAll();
    }

    public Long insertEmployee(Employee employee) {
        return employeeRepository.save(employee).getId();
    }

    public void addAvailability(Set<DayOfWeek> days, Long id) {
        Employee employee = employeeRepository.getOne(id);
        employee.setDaysAvailable(days);
    }

    public List<Employee> getAllAvailableEmployee(LocalDate date, Set<EmployeeSkill> skills){
        List<Employee> employees = employeeRepository.findAll();
        for (Employee e : employees){
            if(checkAvailability(e.getId(), date, skills) != null){
                employees.add(e);
            }
        }
        return employees;
    }

    private Employee checkAvailability(Long id, LocalDate date, Set<EmployeeSkill> skills) {
        Employee employee = employeeRepository.getOne(id);
        if (checkEmployeeDayAvailability(employee, date) && checkEmployeeSkill(employee, skills)) {
            return employee;
        } else {
            return null;
        }
    }

    private boolean checkEmployeeSkill(Employee employee, Set<EmployeeSkill> skills) {
        for (EmployeeSkill skill : skills
        ) {
            if (!employee.getSkills().contains(skill)) {
                return false;
            }
        }
        return true;
    }

    private boolean checkEmployeeDayAvailability(Employee employee, LocalDate date) {
        if (employee.getDaysAvailable().contains(date.getDayOfWeek())) {
            return true;
        } else {
            return false;
        }
    }


}
