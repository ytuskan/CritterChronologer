package com.udacity.jdnd.course3.critter.user;

import com.udacity.jdnd.course3.critter.pet.Pet;
import com.udacity.jdnd.course3.critter.pet.PetRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class CustomerService {

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private PetRepository petRepository;

    public Customer findCustomerById(Long id) {
        return customerRepository.getOne(id);
    }

    public List<Customer> findCustomersByName(String name) {
        return findCustomersByName(name);
    }

    public Customer findCustomerByPet(Pet pet){

        return customerRepository.findCustomerByPetsContaining(pet);
    }

    public List<Pet> getAllPets(Long id) {
        return customerRepository.getOne(id).getPets();
    }

    public Long insertCustomer(Customer customer) {
        return customerRepository.save(customer).getId();
    }

    public List<Customer> getAllCustomer() {
        return customerRepository.findAll();
    }

    public void addPetToCustomer(Long customerId, Long petId) {
        Customer customer = customerRepository.getOne(customerId);
        Pet pet = petRepository.getOne(petId);
        List<Pet> pets = customer.getPets();
        pets.add(pet);
        customer.setPets(pets);
    }
}
