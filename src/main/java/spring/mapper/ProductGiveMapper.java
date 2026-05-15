package spring.mapper;

import org.mapstruct.Mapper;

import spring.dto.ProductGiveDto;
import spring.model.Product;

@Mapper(componentModel = "spring")
public interface ProductGiveMapper {

	ProductGiveDto toDto(Product entity);

}
