package guru.springframework.web.services;

import java.util.UUID;

import guru.springframework.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID id);

	BeerDto saveNewBeer(BeerDto beerDto);

	void updateBeer(UUID beerId, BeerDto beerDto);

	void deleteById(UUID beerId);

}
