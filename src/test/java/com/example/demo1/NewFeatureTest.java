package com.example.demo1;

import com.example.demo1.domain.Employee;
import com.example.demo1.domain.Product;
import org.junit.Before;
import org.junit.Test;

import java.sql.SQLOutput;
import java.time.DayOfWeek;
import java.time.Duration;
import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.Period;
import java.time.ZoneId;
import java.time.ZoneOffset;
import java.time.ZonedDateTime;
import java.time.format.DateTimeFormatter;
import java.time.temporal.TemporalAdjusters;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.Set;
import java.util.function.BiFunction;
import java.util.function.Consumer;
import java.util.function.Function;
import java.util.function.Predicate;
import java.util.function.Supplier;
import java.util.stream.Collectors;
import java.util.stream.Stream;

import static org.junit.Assert.*;

public class NewFeatureTest {

    NewFeature newFeature = new NewFeature();
    List<Product> productList = new ArrayList<>();
    List<Employee> emps = new ArrayList<>();

    @Before
    public void init(){
        Product product = new Product();
        product.setColor(Product.RED);
        product.setPrice(23.55);
        productList.add(product);

        product = new Product();
        product.setColor(Product.RED);
        product.setPrice(50.46);
        productList.add(product);

        product = new Product();
        product.setColor(Product.GREEN);
        product.setPrice(99.09);
        productList.add(product);


        Employee employee = new Employee();
        employee.setSalary(12.13);
        employee.setStatus("0");
        employee.setAge(34);
        emps.add(employee);

        employee = new Employee();
        employee.setAge(35);
        employee.setSalary(23.13);
        employee.setStatus(Employee.Status.BUSY);
        emps.add(employee);

        employee = new Employee();
        employee.setSalary(100.13);
        employee.setAge(36);
        employee.setStatus(Employee.Status.BUSY);
        emps.add(employee);
    }

    @Test
    public void filterProductByColor() {
//        List<Product> retProductList = newFeature.filterProductByColor(this.productList);
//        System.out.println(retProductList);
        this.productList.stream()
                .filter(product -> Product.RED.equals(product.getColor()))
//                .limit(1)

        .forEach(product -> {
            System.out.println(product.toString());
            String color = product.getColor();
            System.out.println(color);
        });



    }

    @Test
    public void filterProductByPrice() {
        List<Product> retProductList = newFeature.filterProductByPrice(this.productList);
        System.out.println(retProductList);
    }

    @Test
    public void functionTest() {
        /**
         *注意：
         *   1.lambda体中调用方法的参数列表与返回值类型，要与函数式接口中抽象方法的函数列表和返回值类型保持一致！
         *   2.若lambda参数列表中的第一个参数是实例方法的调用者，而第二个参数是实例方法的参数时，可以使用ClassName::method
         *
         */
        Consumer<Integer> con = (x) -> System.out.println(x);
        con.accept(100);

        // 方法引用-对象::实例方法
        Consumer<Integer> con2 = System.out::println;
        con2.accept(200);

        // 方法引用-类名::静态方法名
        BiFunction<Integer, Integer, Integer> biFun = (x, y) -> Integer.compare(x, y);
        BiFunction<Integer, Integer, Integer> biFun2 = Integer::compare;
        Integer result = biFun2.apply(100, 200);

        // 方法引用-类名::实例方法名
        BiFunction<String, String, Boolean> fun1 = (str1, str2) -> str1.equals(str2);
        BiFunction<String, String, Boolean> fun2 = String::equals;
        Boolean result2 = fun2.apply("hello", "world");
        System.out.println(result2);

    }

    @Test
    public void functionTest2() {
        // 构造方法引用  类名::new
        Supplier<Employee> sup = () -> new Employee();
        System.out.println(sup.get());
        Supplier<Employee> sup2 = Employee::new;
        System.out.println(sup2.get());

        Supplier <String > sup3 =
            Employee::getStr
        ;



        // 构造方法引用 类名::new （带一个参数）
        Function<Integer, Employee> fun = (x) -> new Employee(x);
        System.out.println(fun.apply(89));
        Function<Integer, Employee> fun2 = Employee::new;
        System.out.println(fun2.apply(100));

        // 数组引用
        Function<Integer, String[]> arrfun = (x) -> {
            String[] t = new String[x];
//            Arrays.stream(t).forEach(y->y="23");
//            for(int i=0;i < x;i++){
//                t[i] = i+"";
//            }
            return t;
        };
        Function<Integer, String[]> arrfun2 = String[]::new;
        String[] strArray = arrfun.apply(10);
        Arrays.stream(strArray).forEach(System.out::println);

    }

    @Test
    public void getStreamTest(){
        // 1，校验通过Collection 系列集合提供的stream()或者paralleStream()
        List<String> list = new ArrayList<>();
        Stream<String> stream1 = list.stream();

        // 2.通过Arrays的静态方法stream()获取数组流
        String[] str = new String[10];
        Stream<String> stream2 = Arrays.stream(str);

        // 3.通过Stream类中的静态方法of
        Stream<String> stream3 = Stream.of("aa","bb","cc");

        // 4.创建无限流
        // 迭代
        Stream<Integer> stream4 = Stream.iterate(0,(x) -> x+2);

        //生成
        Stream<Double> generate = Stream.generate(() -> Math.random());

    }

    @Test
    public void oprationStreamTest(){
        /**
         * 筛选 过滤  去重
         */
        productList.stream()
                .filter(e -> e.getPrice() > 10)
                .limit(4)
                .skip(4)
                // 需要流中的元素重写hashCode和equals方法
                .distinct()
                .forEach(System.out::println);


        /**
         *  生成新的流 通过map映射
         */
        productList.stream()
                .map((e) -> e.getPrice())
                .forEach(System.out::println);


        /**
         *  自然排序  定制排序
         */
        productList.stream()
                .sorted((e1 ,e2) -> {
                    if (e1.getPrice().equals(e2.getPrice())){
                        return e1.getColor().compareTo(e2.getColor());
                    } else{
                        return e1.getPrice().compareTo(e2.getPrice());
                    }
                })
                .forEach(System.out::println);
    }

    @Test
    public void endStreamTest(){
        /**
         *      查找和匹配
         *          allMatch-检查是否匹配所有元素
         *          anyMatch-检查是否至少匹配一个元素
         *          noneMatch-检查是否没有匹配所有元素
         *          findFirst-返回第一个元素
         *          findAny-返回当前流中的任意元素
         *          count-返回流中元素的总个数
         *          max-返回流中最大值
         *          min-返回流中最小值
         */

        /**
         *  检查是否匹配元素
         */
        boolean b1 = emps.stream()
                .allMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b1);

        boolean b2 = emps.stream()
                .anyMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b2);

        boolean b3 = emps.stream()
                .noneMatch((e) -> e.getStatus().equals(Employee.Status.BUSY));
        System.out.println(b3);

        Optional<Employee> opt = emps.stream()
                .findFirst();
        System.out.println(opt.get());

        // 并行流
        Optional<Employee> opt2 = emps.parallelStream()
                .findAny();
        System.out.println(opt2.get());

        long count = emps.stream()
                .count();
        System.out.println(count);

        Optional<Employee> max = emps.stream()
                .max((e1, e2) -> -(-Double.compare(e1.getSalary(), e2.getSalary())));
        System.out.println(max.get());

        Optional<Employee> min = emps.stream()
                .min((e1, e2) -> Double.compare(e1.getSalary(), e2.getSalary()));
        System.out.println(min.get());



    }

    @Test
    public void reduceTest(){
        //reduce操作： reduce:(T identity,BinaryOperator)/reduce(BinaryOperator)-可以将流中元素反复结合起来，得到一个值
        /**
         *  reduce ：规约操作
         */
        List<Integer> list = Arrays.asList(1,2,3,4,5,6,7,8,9,10);
        Integer count2 = list.stream()
                .reduce(0, Integer::sum);
        System.out.println(count2);

        Optional<Double> sum = emps.stream()
                .map(Employee::getSalary)
                .reduce(Double::sum);
        System.out.println(sum);


        //collect操作：Collect-将流转换为其他形式，接收一个Collection接口的实现，用于给Stream中元素做汇总的方法
        /**
         *  collect：收集操作
         */
        List<Integer> ageList = emps.stream()
                .map(Employee::getAge)
                .collect(Collectors.toList());

        ageList.stream().forEach(System.out::println);


    }

    /**
     * 并行流和串行流
     * 在jdk1.8新的stream包中针对集合的操作也提供了并行操作流和串行操作流。并行流就是把内容切割成多个数据块，并且使用多个线程分别处理每个数据块的内容。Stream api中声明可以通过parallel()与sequential()方法在并行流和串行流之间进行切换。
     * jdk1.8并行流使用的是fork/join框架进行并行操作
     */



    @Test
    public void test(){
        /**
         *      Optional.of(T t); // 创建一个Optional实例
         *      Optional.empty(); // 创建一个空的Optional实例
         *      Optional.ofNullable(T t); // 若T不为null，创建一个Optional实例，否则创建一个空实例
         *      isPresent();    // 判断是够包含值
         *      orElse(T t);   //
         *      orElseGet(Supplier s);  // 如果调用对象包含值，返回该值，否则返回s中获取的值
         *      map(Function f): // 如果有值对其处理，并返回处理后的Optional，否则返回Optional.empty();
         *      flatMap(Function mapper);// 与map类似。返回值是Optional
         *
         *      总结：Optional.of(null)  会直接报NPE
         */

        Optional<Employee> op = Optional.of(new Employee("zhansan", 11, 12.32, Employee.Status.BUSY));
        System.out.println(op.get());

        // NPE
        Optional<Employee> op2 = Optional.of(null);
        System.out.println(op2);
    }


    @Test
    public void test2(){
        Optional<Object> op = Optional.empty();
        System.out.println(op);

        // No value present
        System.out.println(op.get());
    }
    @Test
    public void test3(){
        Optional<Employee> op = Optional.ofNullable(new Employee("lisi", 33, 131.42, Employee.Status.FREE));
        System.out.println(op.get());

        Optional<Object> op2 = Optional.ofNullable(null);
        System.out.println(op2);
        // System.out.println(op2.get());
    }
    @Test
    public void test5(){
        Optional<Employee> op1 = Optional.ofNullable(new Employee("张三", 11, 11.33, Employee.Status.VOCATION));
        System.out.println(op1.orElse(new Employee()));
        System.out.println(op1.orElse(null));
    }

    @Test
    public void test6(){
        Optional<Employee> op1 = Optional.of(new Employee("田七", 11, 12.31, Employee.Status.BUSY));
        op1 = Optional.empty();
        Employee employee = op1.orElseGet(() -> new Employee());
        System.out.println(employee);
    }

    @Test
    public void test7(){
        Optional<Employee> op1 = Optional.of(new Employee("田七", 11, 12.31, Employee.Status.BUSY));
        System.out.println(op1.map( (e) -> e.getSalary()).get());
    }

    @Test
    public void test8(){
        // 从默认时区的系统时钟获取当前的日期时间。不用考虑时区差
        LocalDateTime date = LocalDateTime.now();
        //2018-07-15T14:22:39.759
        System.out.println(date);

        System.out.println(date.getYear());
        System.out.println(date.getMonthValue());
        System.out.println(date.getDayOfMonth());
        System.out.println(date.getHour());
        System.out.println(date.getMinute());
        System.out.println(date.getSecond());
        System.out.println(date.getNano());

        // 手动创建一个LocalDateTime实例
        LocalDateTime date2 = LocalDateTime.of(2017, 12, 17, 9, 31, 31, 31);
        System.out.println(date2);
        // 进行加操作，得到新的日期实例
        LocalDateTime date3 = date2.plusDays(12);
        System.out.println(date3);
        // 进行减操作，得到新的日期实例
        LocalDateTime date4 = date3.minusYears(2);
        System.out.println(date4);

    }

    @Test
    public void test9(){
        // 时间戳  1970年1月1日00：00：00 到某一个时间点的毫秒值
        // 默认获取UTC时区
        Instant ins = Instant.now();
        System.out.println(ins);

        System.out.println(LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli());
        System.out.println(System.currentTimeMillis());

        System.out.println(Instant.now().toEpochMilli());
        System.out.println(Instant.now().atOffset(ZoneOffset.ofHours(8)).toInstant().toEpochMilli());
    }

    @Test
    public void test10(){
        // Duration:计算两个时间之间的间隔
        // Period：计算两个日期之间的间隔

        Instant ins1 = Instant.now();

        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        Instant ins2 = Instant.now();
        Duration dura = Duration.between(ins1, ins2);
        System.out.println(dura);
        System.out.println(dura.toMillis());

        System.out.println("======================");
        LocalTime localTime = LocalTime.now();
        LocalDate localDateTime = LocalDate.now();
        try {
            Thread.sleep(1000);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        LocalTime localTime2 = LocalTime.now();
        LocalDate localDateTime2 = LocalDate.of(2019,12,25);
        Duration du2 = Duration.between(localTime, localTime2);
        Period period = Period.between(localDateTime, localDateTime2);
        System.out.println(du2);
        System.out.println(du2.toMillis());
        System.out.println(period);
        System.out.println(period.getDays());
    }

    @Test
    public void test11(){
        // temperalAdjust 时间校验器
        // 例如获取下周日  下一个工作日
        LocalDateTime ldt1 = LocalDateTime.now();
        System.out.println(ldt1);

        // 获取一年中的第一天
        LocalDateTime ldt2 = ldt1.withDayOfYear(1);
        System.out.println(ldt2);
        // 获取一个月中的第一天
        LocalDateTime ldt3 = ldt1.withDayOfMonth(1);
        System.out.println(ldt3);

        LocalDateTime ldt4 = ldt1.with(TemporalAdjusters.next(DayOfWeek.FRIDAY));
        System.out.println(ldt4);

        // 获取下一个工作日
        LocalDateTime ldt5 = ldt1.with((t) -> {
            LocalDateTime ldt6 = (LocalDateTime)t;
            DayOfWeek dayOfWeek = ldt6.getDayOfWeek();
            if (DayOfWeek.FRIDAY.equals(dayOfWeek)){
                return ldt6.plusDays(3);
            }
            else if (DayOfWeek.SATURDAY.equals(dayOfWeek)){
                return ldt6.plusDays(2);
            }
            else {
                return ldt6.plusDays(1);
            }
        });
        System.out.println(ldt5);
    }


    @Test
    public void test12(){
        // DateTimeFormatter: 格式化时间/日期
        // 自定义格式
        LocalDateTime ldt = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy年MM月dd日");
        String strDate1 = ldt.format(formatter);
        String strDate = formatter.format(ldt);
        System.out.println(strDate);
        System.out.println(strDate1);

        // 使用api提供的格式
        DateTimeFormatter dtf = DateTimeFormatter.ISO_DATE;
        LocalDateTime ldt2 = LocalDateTime.now();
        String strDate3 = dtf.format(ldt2);
        System.out.println(strDate3);

        // 解析字符串to时间
        DateTimeFormatter df = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        LocalDateTime time = LocalDateTime.now();
        String localTime = df.format(time);
        LocalDateTime ldt4 = LocalDateTime.parse("2017-09-28 17:07:05",df);
        System.out.println("LocalDateTime转成String类型的时间："+localTime);
        System.out.println("String类型的时间转成LocalDateTime："+ldt4);
    }

    // ZoneTime  ZoneDate       ZoneDateTime
    @Test
    public void test13(){
        LocalDateTime now = LocalDateTime.now(ZoneId.of("Asia/Shanghai"));
        System.out.println(now);

        LocalDateTime now2 = LocalDateTime.now();
        ZonedDateTime zdt = now2.atZone(ZoneId.of("US/Alaska"));
        System.out.println(zdt);

        Set<String> set = ZoneId.getAvailableZoneIds();
        set.stream().forEach(System.out::println);

        emps.stream().map(employee -> {
            String result = "";
            Integer age = employee.getAge();
            String name = employee.getName();
            result += "一个名字为："+name+"，年龄是"+age+",的工资是："+employee.getSalary();
            return result;
        }).forEach(System.out::println);
    }

    @Test
    public void test14(){
        // 获取当前日期,只含年月日 固定格式 yyyy-MM-dd    2018-05-04
        LocalDate today = LocalDate.now();

        // 根据年月日取日期，5月就是5，
        LocalDate oldDate = LocalDate.of(2018, 5, 1);

        // 根据字符串取：默认格式yyyy-MM-dd，02不能写成2
        LocalDate yesteday = LocalDate.parse("2018-05-03");

        // 如果不是闰年 传入29号也会报错
        LocalDate.parse("2018-02-29");
    }


    @Test
    public void test15(){
        // 2019-10-25
        LocalDate today = LocalDate.now();
        // 取本月第1天： 2019-10-01
        LocalDate firstDayOfThisMonth = today.with(TemporalAdjusters.firstDayOfMonth());
        // 取本月第2天：2019-10-02
        LocalDate secondDayOfThisMonth = today.withDayOfMonth(2);
        // 取本月最后一天，再也不用计算是28，29，30还是31： 2019-10-31
        LocalDate lastDayOfThisMonth = today.with(TemporalAdjusters.lastDayOfMonth());
        // 取下一天：2019-11-01
        LocalDate firstDayOf2015 = lastDayOfThisMonth.plusDays(1);
        // 取2018年10月第一个周三 so easy?：  2018-10-03
        LocalDate thirdMondayOf2018 = LocalDate.parse("2018-10-01").with(TemporalAdjusters.firstInMonth(DayOfWeek.WEDNESDAY));


        //16:25:46.448(纳秒值)
        LocalTime todayTimeWithMillisTime = LocalTime.now();
        //16:28:48 不带纳秒值
        LocalTime todayTimeWithNoMillisTime = LocalTime.now().withNano(0);
        LocalTime time1 = LocalTime.parse("23:59:59");


        //转化为时间戳  毫秒值
        long time3 = LocalDateTime.now().toInstant(ZoneOffset.of("+8")).toEpochMilli();
        long time4 = System.currentTimeMillis();

        //时间戳转化为localdatetime
        DateTimeFormatter df= DateTimeFormatter.ofPattern("YYYY-MM-dd HH:mm:ss.SSS");

        System.out.println(df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time3),ZoneId.of("Asia/Shanghai"))));
        System.out.println(df.format(LocalDateTime.ofInstant(Instant.ofEpochMilli(time4),ZoneId.of("Asia/Shanghai"))));

    }

}