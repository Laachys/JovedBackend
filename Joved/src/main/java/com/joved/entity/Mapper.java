package com.joved.entity;

import org.mapstruct.factory.Mappers;

public interface Mapper {
	Mapper mapper = Mappers.getMapper(Mapper.class);
}
