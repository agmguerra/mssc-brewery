package guru.springframework.web.domain;

import java.sql.Timestamp;
import java.util.UUID;

import guru.springframework.web.model.v2.BeerStyleEnum;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class Beer {

	private UUID id;

	private String beerName;

	private BeerStyleEnum beerStyle;

	private Long UPC;

	private Timestamp createdDate;
	private Timestamp lastUpdatedDate;

}
