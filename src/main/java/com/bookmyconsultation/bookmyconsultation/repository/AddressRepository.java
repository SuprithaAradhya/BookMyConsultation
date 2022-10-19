package com.bookmyconsultation.bookmyconsultation.repository;

import com.bookmyconsultation.bookmyconsultation.entity.Address;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AddressRepository extends CrudRepository<Address, String> {
}
