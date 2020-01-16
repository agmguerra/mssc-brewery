package guru.springframework.web.services;

import java.util.UUID;

import guru.springframework.web.model.CustomerDto;

public interface CustomerService {

	CustomerDto getCustomerById(UUID id);

}
