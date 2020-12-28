package com.ulrictodman.portfoliobackend.repository;

import com.ulrictodman.portfoliobackend.model.ContactInformation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactInformationRepository extends JpaRepository<ContactInformation, Integer> {
}
