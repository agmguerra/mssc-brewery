package guru.springframework.web.controller;


import java.util.UUID;

import javax.validation.Valid;

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

import guru.springframework.web.model.BeerDto;
import guru.springframework.web.services.BeerService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;


@RestController
@RequestMapping("/api/v1/beer")
@Api(tags = "Beer")
public class BeerController {

	private BeerService beerService;


	public BeerController(BeerService beerService) {
		super();
		this.beerService = beerService;
	}


	@ApiOperation(value = "This endpoint is used to get the all beer info.")
	@ApiResponses(value = {
		    @ApiResponse(code = 400, message =
		        "10010 - Beer Id not found\n " +
		        "10011 - Beer Id is empty\n" , response = Error.class),
		    @ApiResponse(code = 500, message = "10000 - Server error", response = Error.class)
	})
	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@ApiOperation(value = "This endpoint is used create a new beer instance.")
	@ApiResponses(value = {
			@ApiResponse(code = 400, message = " Bad request is received", response = Error.class),
			@ApiResponse(code = 500, message = " Server error", response = Error.class)
	})
	@PostMapping
	public ResponseEntity<?> createBeer(@Valid @RequestBody BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);

		HttpHeaders headers = new HttpHeaders();

		//TODO add a hostname here
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

	@PutMapping("/{beerId}")
	public ResponseEntity<?> updateBeer(@PathVariable("beerId") UUID beerId, @Valid @RequestBody BeerDto beerDto) {

		beerService.updateBeer(beerId, beerDto);

		return new ResponseEntity<>(HttpStatus.NO_CONTENT);
	}

	@DeleteMapping("/{beerId}")
	@ResponseStatus(HttpStatus.NO_CONTENT)
	public void deleteBeer(@PathVariable("beerId") UUID beerId) {
		beerService.deleteById(beerId);
	}


}
