package guru.springframework.web.services;

import java.util.UUID;

import org.springframework.stereotype.Service;

import guru.springframework.web.model.BeerDto;
import lombok.extern.slf4j.Slf4j;

@Service
@Slf4j
public class BeerServiceImpl implements BeerService {

	@Override
	public BeerDto getBeerById(UUID id) {
		return BeerDto.builder().id(UUID.randomUUID())
				.beerName("Galaxy Cat")
				.beerStyle("Pale Ale")
				.build();
	}

	@Override
	public BeerDto saveNewBeer(BeerDto beerDto) {

		return BeerDto.builder().id(UUID.randomUUID()).beerName(beerDto.getBeerName()).build();

	}

	@Override
	public void updateBeer(UUID beerId, BeerDto beerDto) {
		log.debug("updating a beer instance");

	}

	@Override
	public void deleteById(UUID beerId) {
		log.debug("deleting a beer instance");
	}

}
