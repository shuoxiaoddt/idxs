package com.micro.service.webmvc.entity;

import lombok.Data;

import javax.validation.constraints.NotNull;

/**
 * 写点注释吧!!
 *
 * @author xiaos
 * @date : 2018/5/9
 */
@Data
public class Person {

    @NotNull
    private String name;

    private int age;

    private String address;

    private String phone;

}
