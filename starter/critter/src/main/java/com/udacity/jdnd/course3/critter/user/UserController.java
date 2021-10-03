package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetService;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.time.DayOfWeek;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * Handles web requests related to Users.
 *
 * Includes requests for both customers and employees. Splitting this into separate user and customer controllers
 * would be fine too, though that is not part of the required scope for this class.
 */
@RestController
@RequestMapping("/user")
public class UserController {

    @Autowired
    private CustomerService customerService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PetService petService;

    @PostMapping("/customer")
    public CustomerDTO saveCustomer(CustomerDTO customerDTO){
        Customer customer = convertCustomerDTOToCustomer(customerDTO);
        customerService.insertCustomer(customer);
        return convertCustomerToCustomerDTO(customer);
    }
    @GetMapping("/customer")
    public List<CustomerDTO> getAllCustomers(){
        return convertListCustomerToListCustomerDTO(customerService.getAllCustomer());
    }

    @GetMapping("/customer/pet/{petId}")
    public CustomerDTO getOwnerByPet(@PathVariable long petId){
        Pet pet = petService.getPetById(petId);
        return convertCustomerToCustomerDTO(customerService.findCustomerByPet(pet));
    }

    @PostMapping("/employee")
    public EmployeeDTO saveEmployee(@RequestBody EmployeeDTO employeeDTO) {
        Employee employee = convertEmployeeDTOToEmployee(employeeDTO);
        employeeService.insertEmployee(employee);
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PostMapping("/employee/{employeeId}")
    public EmployeeDTO getEmployee(@PathVariable long employeeId) {
        Employee employee = employeeService.getEmployeeById(employeeId);
        return convertEmployeeToEmployeeDTO(employee);
    }

    @PutMapping("/employee/{employeeId}")
    public void setAvailability(@RequestBody Set<DayOfWeek> daysAvailable, @PathVariable long employeeId) {
        employeeService.addAvailability(daysAvailable, employeeId);
    }

    @GetMapping("/employee/availability")
    public List<EmployeeDTO> findEmployeesForService(@RequestBody EmployeeRequestDTO employeeDTO) {
        List<Employee> employees = employeeService.getAllAvailableEmployee(employeeDTO.getDate(), employeeDTO.getSkills());
        return convertListEmployeeToListEmployeeDTO(employees);
    }

    private CustomerDTO convertCustomerToCustomerDTO(Customer customer){
        CustomerDTO customerDTO = new CustomerDTO();
        BeanUtils.copyProperties(customer, customerDTO);
        return customerDTO;
    }

    private Customer convertCustomerDTOToCustomer(CustomerDTO customerDTO){
        Customer customer = new Customer();
        BeanUtils.copyProperties(customerDTO, customer);
        return customer;
    }

    private List<CustomerDTO> convertListCustomerToListCustomerDTO(List<Customer> customers){
        List<CustomerDTO> customerDTOS = new ArrayList<>();
        CustomerDTO temp = new CustomerDTO();
        for(Customer customer: customers){
            BeanUtils.copyProperties(customer, temp);
            customerDTOS.add(temp);
        }
        return customerDTOS;
    }

    private EmployeeDTO convertEmployeeToEmployeeDTO(Employee employee){
        EmployeeDTO employeeDTO = new EmployeeDTO();
        BeanUtils.copyProperties(employee, employeeDTO);
        return employeeDTO;
    }

    private Employee convertEmployeeDTOToEmployee(EmployeeDTO employeeDTO){
        Employee employee = new Employee();
        BeanUtils.copyProperties(employeeDTO, employee);
        return employee;
    }

    private List<EmployeeDTO> convertListEmployeeToListEmployeeDTO(List<Employee> employees){
        List<EmployeeDTO> employeeDTOS = new ArrayList<>();
        EmployeeDTO temp = new EmployeeDTO();
        for(Employee employee: employees){
            BeanUtils.copyProperties(employee, temp);
            employeeDTOS.add(temp);
        }
        return employeeDTOS;
    }

}
