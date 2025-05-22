package Lambda.filter;

import java.util.*;
import java.util.stream.Collectors;

public class FilteringEvenOdd {
    public static void main(String[] args) {
        List<Integer> number = Arrays.asList(1,2,3,4,5,6,7,8,9,10);

        //filtering only even number
        List<Integer> even = number.stream().filter(x-> x%2==0).collect(Collectors.toList());
        System.out.println(even);
    }
}
