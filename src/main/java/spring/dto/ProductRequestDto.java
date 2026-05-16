package spring.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import spring.model.Firm;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProductRequestDto {

	private String name;
	private Integer price;
	private Firm firm;
	private String imageUrl;
	private boolean available;
	private LocalDateTime localDateTime;

}
