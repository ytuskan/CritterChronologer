package com.udacity.jdnd.course3.critter.pet;

import com.udacity.jdnd.course3.critter.user.Customer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;

@Service
@Transactional
public class PetService {
    @Autowired
    private PetRepository petRepository;

    public Pet getPetById(Long id){
        return petRepository.getOne(id);
    }

    public List<Pet> getAllPets(){
        return petRepository.findAll();
    }

    public Customer getOwnerByPet(Long petId){
        return petRepository.getOne(petId).getCustomer();
    }

    public Long insertPet(Pet pet){
        return petRepository.save(pet).getId();
    }
}
