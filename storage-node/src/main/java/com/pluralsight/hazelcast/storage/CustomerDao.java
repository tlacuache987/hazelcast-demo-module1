package com.pluralsight.hazelcast.storage;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.pluralsight.hazelcast.shared.Customer;

@Repository
public interface CustomerDao extends CrudRepository<Customer, Long> {

}
