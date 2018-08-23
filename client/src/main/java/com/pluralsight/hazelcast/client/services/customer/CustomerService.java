package com.pluralsight.hazelcast.client.services.customer;

import java.util.Collection;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import javax.annotation.PostConstruct;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;

import com.hazelcast.core.HazelcastInstance;
import com.hazelcast.core.IMap;
import com.hazelcast.query.Predicate;
import com.hazelcast.query.Predicates;
import com.hazelcast.query.SqlPredicate;
import com.pluralsight.hazelcast.shared.Customer;
import com.pluralsight.hazelcast.shared.MapNames;

@Service
public class CustomerService {

	private HazelcastInstance hazelcastInstance;
	private IMap<Long, Customer> customersMap;

	@Autowired
	public CustomerService(@Qualifier("clientInstance") HazelcastInstance clientInstance) {
		this.hazelcastInstance = clientInstance;
	}

	@PostConstruct
	public void init() {
		customersMap = hazelcastInstance.getMap(MapNames.CUSTOMERS_MAP);
	}

	public Customer getCustomer(Long key) {
		return customersMap.get(key);
	}

	public void addCustomer(Customer customer) {
		customersMap.put(customer.getId(), customer);
	}

	public void addCustomers(Collection<Customer> customers) {

		Map<Long, Customer> customersLocalMap = new HashMap<>();
		for (Customer customer : customers) {
			customersLocalMap.put(customer.getId(), customer);
		}
		customersMap.putAll(customersLocalMap);
	}

	public void updateCustomer(Customer customer) {
		customersMap.put(customer.getId(), customer);
	}

	public void deleteCustomer(Customer customer) {
		customersMap.delete(customer.getId());
	}

	public Collection<Customer> findCustomer(Date dobStart, Date dobEnd) {

		Predicate dobStartPredicate = Predicates.greaterEqual("dob", dobStart);
		Predicate dobEndPredicate = Predicates.lessThan("dob", dobEnd);
		Predicate andPredicate = Predicates.and(dobStartPredicate, dobEndPredicate);

		return customersMap.values(andPredicate);
	}

	public Collection<Customer> findCustomersByEmail(String email) {
		SqlPredicate sqlPredicate = new SqlPredicate("email LIKE '" + email + "'");
		return customersMap.values(sqlPredicate);
	}

}
