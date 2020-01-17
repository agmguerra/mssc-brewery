package guru.springframework.web.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.web.model.CustomerDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class CustomerServiceImpl implements CustomerService {

	@Override
	public CustomerDto getCustomerById(UUID id) {
		return CustomerDto.builder()
					.id(UUID.randomUUID())
					.name("Alexandre Guerra")
					.build();
	}

	@Override
	public CustomerDto saveNewCustomer(CustomerDto customerDto) {

		return CustomerDto.builder().id(UUID.randomUUID()).name(customerDto.getName()).build();

	}

	@Override
	public void updateCustomer(UUID customerId, CustomerDto customerDto) {
		log.debug("updating a beer instance");

	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("deleting a beer instance");
	}


}
