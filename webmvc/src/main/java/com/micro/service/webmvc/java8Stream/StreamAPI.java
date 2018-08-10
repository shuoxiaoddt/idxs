package com.micro.service.webmvc.java8Stream;

import java.io.File;
import java.nio.charset.Charset;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.*;
import java.util.stream.Collectors;
import java.util.stream.IntStream;
import java.util.stream.Stream;

/**
 * Created by xiaos 2018/6/21
 * //TODO 写点注释吧
 */
public class StreamAPI {
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

        List<Dish> fiters = menu.stream().filter(Dish::isVegetarian).collect(Collectors.toList());
        // System.out.println(fiters);

        List<Integer> numbers = Arrays.asList(1, 2, 1, 3, 3, 2, 4);
        numbers.stream()
                .filter(i -> i % 2 == 0)
                .distinct()
                .forEach(System.out::println);

        menu.stream().filter(d -> d.getCalories() > 300).limit(3).collect(Collectors.toList());

        menu.stream().filter(d -> d.getCalories() > 300).skip(2).collect(Collectors.toList());

        menu.stream().map(Dish::getName).map(String::length).collect(Collectors.toList());

        List<String> words = Arrays.asList("Goodbye", "World");

        List<String> collect = words.stream().map(w -> w.split("")).flatMap(Arrays::stream).distinct().collect(Collectors.toList());
        System.out.println(collect);

        List<Integer> list1 = Arrays.asList(1, 2, 3);
        List<Integer> list2 = Arrays.asList(3, 4);
        Stream<int[]> streamStream = list1.stream().flatMap(i -> list2.stream().map(j -> new int[]{i, j}));

        Optional<Dish> any = menu.stream().filter(Dish::isVegetarian).findAny();

        Optional<Dish> first = menu.stream().filter(Dish::isVegetarian).findFirst();

        Integer sum = numbers.stream().reduce(0, (a, b) -> a + b);

        System.out.println("sum:" + sum);

        Optional<Integer> reduce = numbers.stream().reduce(Integer::max);

        menu.stream().sorted(Comparator.comparing(Dish::getCalories).reversed());

        menu.stream().map(Dish::getName).sorted(Comparator.comparing(x -> {
            return x.substring(0, 1);
        }));

        menu.stream().map(Dish::getCalories).reduce(0, Integer::sum);

        OptionalInt max = menu.stream().mapToInt(Dish::getCalories).max();
        max.orElse(1);
        IntStream.rangeClosed(1, 100).filter(x -> x % 2 == 0);

        //方法一
        IntStream.rangeClosed(1, 100).boxed().flatMap(a ->
                IntStream
                        .rangeClosed(a, 100)
                        .filter(b -> Math.sqrt(a * a + b * b) % 1 == 0)
                        .mapToObj(b -> new int[]{a, b, (int) Math.sqrt(a * a + b * b)}));
        //方法二
        IntStream.rangeClosed(1,100).boxed().flatMap(a ->
            IntStream.rangeClosed(a,100)
                    .mapToObj(b -> new double[] { a * a,b * b,Math.sqrt(a * a + b * b)})
                    .filter(t -> t[2] % 1 == 0));
        //查看一个文件中由多少个不同的单词
        long  uniqueWords = 0;
        try(Stream<String> lines = Files.lines(Paths.get("C:\\Users\\lenovo\\Desktop\\临时资料\\caogao.txt"), Charset.defaultCharset());){
            uniqueWords = lines.flatMap(line -> Arrays.stream(line.split(" ")))
                    .distinct()
                    .count();
        }catch (Exception e){
        }
        Stream.iterate(new int[]{0,1},t -> new int[]{t[1],t[0]+t[1]})
                .limit(10)
                .map(t -> t[0])
                .forEach(System.out::println);

        
    }
}
