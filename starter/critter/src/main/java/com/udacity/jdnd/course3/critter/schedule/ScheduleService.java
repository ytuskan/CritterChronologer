package com.udacity.jdnd.course3.critter.schedule;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import com.udacity.jdnd.course3.critter.user.Customer;
import com.udacity.jdnd.course3.critter.user.CustomerRepository;
import com.udacity.jdnd.course3.critter.user.Employee;
import com.udacity.jdnd.course3.critter.user.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class ScheduleService {
    @Autowired
    private ScheduleRepository scheduleRepository;

    @Autowired
    private PetRepository petRepository;

    @Autowired
    private EmployeeRepository employeeRepository;

    @Autowired
    private CustomerRepository customerRepository;

    public Long insertSchedule(Schedule schedule){
        return scheduleRepository.save(schedule).getId();
    }

    public List<Schedule> findSchedulesByPet(Pet pet){
        return scheduleRepository.findScheduleByPetsContaining(pet);
    }

    public List<Schedule> findSchedulesByEmployee(Employee employee){
        return scheduleRepository.findSchedulesByEmployeesContaining(employee);
    }

    public List<Schedule> findScheduleByCustomer(Customer customer){
        return scheduleRepository.findSchedulesByCustomersContaining(customer);
    }

    public List<Schedule> findAllSchedules(){
        return scheduleRepository.findAll();
    }
}
