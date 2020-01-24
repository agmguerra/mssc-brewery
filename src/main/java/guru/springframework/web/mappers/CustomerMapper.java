package guru.springframework.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.web.domain.Customer;
import guru.springframework.web.model.CustomerDto;

@Mapper
public interface CustomerMapper {

	CustomerDto customerToCustomerDto(Customer beer);

	Customer customerDtoDtoCustomr(CustomerDto customerDto);
}
