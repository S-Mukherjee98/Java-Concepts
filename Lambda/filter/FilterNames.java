package Lambda.filter;

import java.util.*;
import java.util.stream.Collectors;

public class FilterNames {
    public static void main(String[] args) {
        List<String> names = Arrays.asList("Shubhra","Shubha","Sonai","Aman","Arya","Anatoly","Pangoose","Poonga");
        List<String> nm = names.stream().filter(x->x.startsWith("S")).collect(Collectors.toList());
        System.out.println(nm);
    }
}
