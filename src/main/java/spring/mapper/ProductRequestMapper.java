package spring.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;

import spring.dto.ProductRequestDto;
import spring.model.Product;

@Mapper(componentModel = "spring")
public interface ProductRequestMapper {

	@Mapping(target = "id", ignore = true)
	Product toEntity(ProductRequestDto dto);

}
