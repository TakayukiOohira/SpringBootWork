package com.example.springboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.example.springboot.InquiryData;

@Repository
public interface InquiryDataRepository extends JpaRepository<InquiryData, Long>{

}
