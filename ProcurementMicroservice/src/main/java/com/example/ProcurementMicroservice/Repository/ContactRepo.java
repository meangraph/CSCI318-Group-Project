package com.example.ProcurementMicroservice.Repository;

import com.example.ProcurementMicroservice.Model.Contact;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ContactRepo extends JpaRepository<Contact,Long> {
}
