package com.addressbook.utils.mapper;

import java.io.Serializable;

public interface EntityMapper <Entity extends Serializable, Dto extends Serializable> {

    Dto toDto(Entity source);

    Entity toEntity(Dto source);
}
