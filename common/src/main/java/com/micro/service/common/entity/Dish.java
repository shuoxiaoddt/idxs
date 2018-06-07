package com.micro.service.common.entity;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.NoArgsConstructor;

/**
 * Created by xiaos 2018/6/4
 * //TODO 写点注释吧
 */
@Data
@AllArgsConstructor
@EqualsAndHashCode(of = {"name"})
public class Dish {
    private final String name;
    private final boolean vegetarian;
    private final int calories;
    private final Type type;
    public enum Type { MEAT, FISH, OTHER }

}
