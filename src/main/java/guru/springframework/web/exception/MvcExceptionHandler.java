package guru.springframework.web.exception;

import java.util.ArrayList;
import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindException;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

@ControllerAdvice
public class MvcExceptionHandler {

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
			errors.add(error.getField() + ": " + error.getDefaultMessage());
		});
		return new ResponseEntity<>(errors, HttpStatus.BAD_REQUEST);
	}

	@ExceptionHandler(BindException.class)
	public ResponseEntity<List<ObjectError>> bindException(BindException ex) {

		return new ResponseEntity<>(ex.getAllErrors(), HttpStatus.BAD_REQUEST);
	}

}
