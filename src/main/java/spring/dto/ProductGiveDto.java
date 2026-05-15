package spring.dto;

import java.time.LocalDateTime;
import java.util.UUID;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.model.Firm;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductGiveDto {

	private UUID id;
	private String name;
	private Integer price;
	private Firm firm;
	private boolean available;
	private LocalDateTime localDateTime;
}
