package guru.springframework.web.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import javax.validation.ConstraintViolationException;
import javax.validation.Valid;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.ExceptionHandler;
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
public class CustomerController {

	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		super();
		this.customerService = customerService;
	}

	@ApiOperation(value = "This endpoint is used to get the all customer info.")
	@GetMapping("/{customerId}")
	public ResponseEntity<CustomerDto> getCustomer(@PathVariable("customerId") UUID customerId) {

		return new ResponseEntity<>(customerService.getCustomerById(customerId), HttpStatus.OK);

	}

	@PostMapping
	public ResponseEntity<?> createCustomer(@Valid @RequestBody CustomerDto customerDto) {

		CustomerDto savedDto = customerService.saveNewCustomer(customerDto);

		HttpHeaders headers = new HttpHeaders();

		//TODO add a hostname here
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/{customerId}")
	public ResponseEntity<?> updateCustomer(@PathVariable("customerId") UUID customerId, @Valid @RequestBody CustomerDto customerDto) {

		customerService.updateCustomer(customerId, customerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{customerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteCustomer(@PathVariable("customerId") UUID customerId) {
		customerService.deleteById(customerId);
	}


//	//Nao funcionou desse jeito a exception disparada não é essa
//	@ExceptionHandler(ConstraintViolationException.class)
//	public ResponseEntity<List<String>> validationErrorHandler(ConstraintViolationException ex) {
//		List<String> errors = new ArrayList<>(ex.getConstraintViolations().size());
//		ex.getConstraintViolations().forEach(constraintViolation -> {
//			System.out.println(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
//			errors.add(constraintViolation.getPropertyPath() + " : " + constraintViolation.getMessage());
//		});
//			return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
//	}

	@ExceptionHandler(MethodArgumentNotValidException.class)
	public ResponseEntity<List<String>> validationMethodErrorHandler(MethodArgumentNotValidException ex) {
		BindingResult result = ex.getBindingResult();
		List<String> errors = new ArrayList<>(ex.getBindingResult().getErrorCount());
		result.getFieldErrors().forEach(error -> {
			System.out.println(error.getField() + ": " + error.getDefaultMessage());
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}



}
