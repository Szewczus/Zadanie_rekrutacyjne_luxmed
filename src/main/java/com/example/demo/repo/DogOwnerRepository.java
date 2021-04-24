package com.example.demo.repo;

import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DogOwnerRepository extends JpaRepository<DogOwner, Long> {
    public Boolean existsDogOwnerByEmail(String email);
    public DogOwner findDogOwnerById(Long id);
    public void deleteDogOwnerByEmail(String email);
    public Boolean existsDogOwnerById(Long id);
}
