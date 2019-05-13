package com.crud.sample.restful.vo;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.Collection;
import java.util.Date;

@Data
@Builder
@AllArgsConstructor(access = AccessLevel.PUBLIC)
public class AccountVO {
    private Long id;
    private String name;
    private String email;
    private Integer age;
    private Date created;
    private Date updated;
    private Collection<AddressVO> addresses;
}
