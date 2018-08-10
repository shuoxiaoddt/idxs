package com.micro.service.webmvc.java8Stream;

import java.util.*;
import java.util.stream.Collectors;

/**
 * Created by xiaos 2018/7/9
 * //TODO 写点注释吧
 */
public class StreamCollectorAPI {
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

        Long howManyDishes = menu.stream().collect(Collectors.counting());
        long count = menu.stream().count();
        Optional<Dish> collect = menu.stream().collect(Collectors.maxBy(Comparator.comparing(Dish::getCalories)));
        Integer collect1 = menu.stream().collect(Collectors.summingInt(Dish::getCalories));
        IntSummaryStatistics intSummaryStatistics = menu.stream().collect(Collectors.summarizingInt(Dish::getCalories));
        String collect2 = menu.stream().map(Dish::getName).collect(Collectors.joining(","));
        menu.stream().collect(Collectors.reducing(0,Dish::getCalories,Integer::sum));

    }
}
