package guru.springframework.web.controller;

import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.web.model.CustomerDto;
import guru.springframework.web.services.CustomerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;

@RestController
@RequestMapping("/api/v1/customer")
@Api(tags = "Customer")
public class CustomerControler {

	private CustomerService customerService;

	public CustomerControler(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@ApiOperation(value = "This endpoint is used to get the all customer info.")
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {

		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createCustomer(@RequestBody CustomerDto customerDto) {

		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

		HttpHeaders headers = new HttpHeaders();

		//TODO add a hostname here
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") UUID customerId, @RequestBody CustomerDto customerDto) {

		customerService.updateCustomer(customerId, customerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteById(customerId);
	}


}
