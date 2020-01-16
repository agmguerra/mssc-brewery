package guru.springframework.web.controller;


import java.util.UUID;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import guru.springframework.web.model.BeerDto;
import guru.springframework.web.services.BeerService;

@RestController
@RequestMapping("/api/v1/beer")
public class BeerController {

	private BeerService beerService;


	public BeerController(BeerService beerService) {
		super();
		this.beerService = beerService;
	}



	@GetMapping("/{beerId}")
	public ResponseEntity<BeerDto> getBeer(@PathVariable("beerId") UUID beerId) {

		return new ResponseEntity<>(beerService.getBeerById(beerId), HttpStatus.OK);
	}

	@PostMapping
	public ResponseEntity<?> handlePost(BeerDto beerDto) {

		BeerDto savedDto = beerService.saveNewBeer(beerDto);

		HttpHeaders headers = new HttpHeaders();

		//TODO add a hostname here
		headers.add("Location", "/api/v1/beer/" + savedDto.getId().toString());

		return new ResponseEntity<>(headers, HttpStatus.CREATED);

	}

}
