package com.micro.service.common.utils;

import com.micro.service.common.entity.Dish;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

/**
 * Created by xiaos 2018/6/4
 * java8 Stream工具类
 */
public class StreamUtil {
    public static void main(String[] args) {
        List<Dish> menu = Arrays.asList(
        new Dish("pork", false, 800, Dish.Type.MEAT),
        new Dish("beef", false, 700, Dish.Type.MEAT),
        new Dish("chicken", false, 400, Dish.Type.MEAT),
        new Dish("french fries", true, 530, Dish.Type.OTHER),
        new Dish("rice", true, 350, Dish.Type.OTHER),
        new Dish("season fruit", true, 120, Dish.Type.OTHER),
        new Dish("pizza", true, 550, Dish.Type.OTHER),
        new Dish("prawns", false, 300, Dish.Type.FISH),
        new Dish("salmon", false, 450, Dish.Type.FISH));
        //选出热量>300的
        List<String> names = menu
                .stream()
                .filter(x -> x.getCalories() > 300)
                .map(Dish::getName)
                .collect(Collectors.toList());
        System.out.println(names);
         menu
                .stream()
                .filter(Dish::isVegetarian)
                .forEach(System.out :: println);
         menu
                 .stream()
                 .distinct()
                 .forEach(System.out :: println);


    }


}
