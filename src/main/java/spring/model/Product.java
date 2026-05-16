package spring.model;

import java.time.LocalDateTime;
import java.util.UUID;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "product")
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Product {

	@Id
	@GeneratedValue
	private UUID id;

	@Column(nullable = false)
	private String name;

	@Column(nullable = false)
	private Integer price = 0;

	@Enumerated(EnumType.STRING)
	private Firm firm;

	@Column(name = "image_url", nullable = false)
	private String imageUrl;

	@Column(nullable = false)
	private boolean available = false;

	@Column(name = "local_date_time", nullable = false)
	private LocalDateTime localDateTime;
}
