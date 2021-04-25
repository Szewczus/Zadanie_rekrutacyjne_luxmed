package com.example.demo.repo;

import com.example.demo.entity.Dog;
import com.example.demo.entity.DogOwner;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DogRepository extends JpaRepository<Dog, Long> {
    public void deleteDogById(Long id);
    public Dog findDogById(Long id);
    @Query(value = "select * from Dog d where d.DOG_OWNER_DOG_ID  like :dogOwner", nativeQuery = true)
    public List<Dog> findAllDogsDogowners(@Param("dogOwner") Long id);
}
