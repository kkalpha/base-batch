package com.tech.batch.demo.schedule.mapper;

import com.tech.batch.demo.schedule.model.Customer;
import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;
import org.springframework.stereotype.Component;
import org.springframework.validation.BindException;

@Component
public class CustomerFieldSetMapper implements FieldSetMapper<Customer> {

	@Override
	public Customer mapFieldSet(FieldSet fieldSet) throws BindException {
		if(fieldSet==null)
			return null;
		Customer customer=new Customer();
		customer.setId(fieldSet.readInt("id"));
		customer.setName(fieldSet.readString("name"));
		customer.setCity(fieldSet.readString("city"));
		return customer;
	}

}
