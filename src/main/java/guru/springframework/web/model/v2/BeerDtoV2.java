package guru.springframework.web.model.v2;

import java.util.UUID;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Positive;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class BeerDtoV2 {

	@NotNull
	private UUID id;

	@NotBlank
	private String beerName;

	@NotBlank
	private BeerStyleEnum beerStyle;

	@Positive
	private Long UPC;


}