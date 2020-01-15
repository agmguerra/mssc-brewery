package guru.springframework.web.services;

import java.util.UUID;

import guru.springframework.web.model.BeerDto;

public interface BeerService {

	BeerDto getBeerById(UUID id);

}
