package com.addressbook.dto;

import java.io.Serializable;

public interface IdentifiedDTO extends Serializable {

    Long getId();

    void setId(Long id);
}
