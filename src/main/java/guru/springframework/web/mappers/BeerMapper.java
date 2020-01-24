package guru.springframework.web.mappers;

import org.mapstruct.Mapper;

import guru.springframework.web.domain.Beer;
import guru.springframework.web.model.BeerDto;


@Mapper(uses= {DateMapper.class})
public interface BeerMapper {

	BeerDto beerToBeerDto(Beer beer);

	Beer beerDtoToBeer(BeerDto beerDto);
}
