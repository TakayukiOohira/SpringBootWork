package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.SampleData;

@Repository
public interface SampleDataRepository extends JpaRepository<SampleData, Long>{

}
