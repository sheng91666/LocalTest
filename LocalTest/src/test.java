import com.alibaba.fastjson.JSON;
import org.apache.http.HttpResponse;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.math.BigDecimal;
import java.util.*;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static sun.misc.Version.print;


public class test {
    private static final Logger logger = LoggerFactory.getLogger(test.class);


    static class student {
        private String name;
        private String age;

        public String getName() {
            return name;
        }

        public String age() {
            return age;
        }

        public void setName(String name) {
            this.name = name;
        }

        public void setAge(String age) {
            this.age = age;
        }

    }

    private static class ChuLi implements Runnable {

        private List dataList;

        public ChuLi(List list) {
            dataList = list;
        }

        public static void test(List list) {
            ExecutorService exec = Executors.newCachedThreadPool();
            exec.execute(new ChuLi(list));
            exec.shutdown();
        }

        @Override
        public void run() {
            try {
                if (dataList.size() > 0) {
                    //分批处理
                    int limit = 5;//限制条数
                    Integer size = dataList.size();
                    //判断是否有必要分批
                    if (limit < size) {
                        int part = size / limit;//分批数
                        System.out.println("共有 ： " + size + "条，！" + " 分为 ：" + part + "批");

                        for (int i = 0; i < part; i++) {
                            //1000条
                            List<Integer> listPage = dataList.subList(0, limit);
                            Thread.sleep(10000);
                            System.out.println(listPage);
                            //剔除
                            dataList.subList(0, limit).clear();
                        }
                    }
                    if (!dataList.isEmpty()) {
                        System.out.println(dataList);//表示最后剩下的数据
                    }

                    System.out.println(dataList.size() / 100 + 1);
                }
            } catch (InterruptedException e) {
                System.out.println("error");
            }
        }
    }

    public static void test2() {
        String zhu = "T20433";
        String zi = "T20433";
        if (!zi.contains(zhu) && !zhu.contains(zi)) {
            System.out.println("error");
        } else {
            System.out.println("yes");
        }
    }

    public static void test1() {
        student student = new student();
        student student1 = new student();
        student.setName("xiaoMing");
        student.setAge("1");
        student1.setAge("2");
        ArrayList<test.student> students = new ArrayList<>();
        students.add(student);
        students.add(student1);

        List<String> collect = students.stream().map(test.student::getName).collect(Collectors.toList());
        collect.forEach(x -> {
            System.out.println(x);
        });
    }

    public static void test5() {
        if (!("44".equals("10") && "10".equals("10"))) {
            System.out.println("aaaaaaaaaaa");
        } else {
            System.out.println("bbbbbbbbbbbb");
        }

        if (!"44".equals("10") && !"10".equals("10")) {
            System.out.println("ccccccccccc");
        } else {
            System.out.println("ddddddddddd");
        }
        Integer integer = Integer.valueOf("5");
        if (integer != 1) {
            System.out.println("xxxxxxxxx");
        } else {
            System.out.println("yyyyyyyyyy");
        }
    }

    public static void test6() {
        String str = "12*11";
        String msg = "^/d.*";
        if (!Pattern.matches(msg, str)) {
            System.out.println("xxxxxxxxxxxxxxxxx");
        } else {
            System.out.println("yyyyyyyyyyyyyyy");
        }
    }

    public static void test7() {
        List<Long> longs = new ArrayList<>();
        longs.add(1111111l);
        longs.add(222222l);
        longs.add(333333l);
        longs.add(444444l);
        longs.add(555555l);

        int splitNum = 2;
        for (int i = 0; i < longs.size(); ) {
            Long[] id;
            if (longs.size() - i >= splitNum) {
                id = new Long[splitNum];
            } else {
                id = new Long[longs.size() - i];
            }
            for (int j = 0; j < splitNum && i < longs.size(); j++) {
                id[j] = longs.get(i);
                i++; //注意 i++，通过这里控制第一个for的下标
            }
            System.out.println(JSON.toJSONString(id));
        }

        Long num = 0l;
        for (int i = 0; i < 10; i++) {
            num += Long.valueOf(i);
        }
        System.out.println(num);

    }

    // 将字符串转成hash值
    public static int toHash(String key) {
        int arraySize = 11113; // 数组大小一般取质数
        int hashCode = 0;
        for (int i = 0; i < key.length(); i++) { // 从字符串的左边开始计算
            int letterValue = key.charAt(i) - 96;// 将获取到的字符串转换成数字，比如a的码值是97，则97-96=1
            hashCode = ((hashCode << 5) + letterValue) % arraySize;// 防止编码溢出，对每步结果都进行取模运算
        }
        System.out.println(hashCode);
        return hashCode;
    }

    public static void test8() {
        String str = "[{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"2592042681154132b51fd72a906ef048\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"d9d1f53c470e459dada645d8dd8eb082\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"ea69e91f29ad4ddba6203abe7d213f10\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"09e7ce501a564e42921a4a1a726572bd\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"b0d079adc4e64c58a9e6df35107584a7\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"0a2c56b8be5c4ce1ba65e902e2bd113c\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"d41770fc7b12434bb102690d1564bc43\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"1756be4828534d85be5f8909a937e7de\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"eff9c094691e402e8316f9a47f03d789\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"dbe34a7062af4ecc82eb9d9ea289094a\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"d5b2681227b0459d80af9ee294951e70\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"e16ef703bf6146bcb94edcef6972424c\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"459f015c62f84f318f586c5513ac7821\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"e1c10721f22f471ba7c4b5504da73291\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"e0bf33d4b083466c9ba419d3ab36ed7b\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"ac7af49f9550499d9e7788e675c55912\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"10aa1f873d4f413da6a2d0fbbe030e42\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"efaaa5d8869e466991eaa014d2c08bd7\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"0aa8ebc57097482585137d030f888f04\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"不锈钢\",\"producer\":\"宝钢\",\"UUID\":\"c7dea9e8ee49462786422ae8f6f9b9b7\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"2*1250*C\"},{\"variety\":\"彩涂尾板卷\",\"producer\":\"本钢\",\"UUID\":\"67865faaa890470fbe8fde4d8239d140\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"3*3*3\"},{\"variety\":\"彩涂尾板卷\",\"producer\":\"本钢\",\"UUID\":\"0fba029f175441d1a5ac36839f28185e\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"3*3*3\"},{\"variety\":\"彩涂尾板卷\",\"producer\":\"本钢\",\"UUID\":\"71d966433a0544c9a73d0878fb5ab95c\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"3*3*3\"},{\"variety\":\"彩涂尾板卷\",\"producer\":\"本钢\",\"UUID\":\"c23eb79cf6bf4bcd926671e3b53ed474\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"3*3*3\"},{\"variety\":\"彩涂尾板卷\",\"producer\":\"本钢\",\"UUID\":\"afaebf2eb0ac4cfd9787e01228a18db4\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"3*3*3\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"eaf9240b1c764dbba817b371899bd740\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"bdac85f6c5f34f4db8fff9a2c6e95aaa\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"89252e5a247f445c8f05feac1d2637bc\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"d74d459a9a094f2db5d963dcb8d7b89b\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"c597c37feae14bfe989af7c935395253\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"4837526db22e4908a8adfc2a7faf46e7\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"779bb4d5208c4d22a90cab5a2ab1b810\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"b04902fd13de460d8321097262518c9a\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"54ccaf7a311f4e6ba7e4544d4d7e8071\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"925367e2380049eca2a2823c9affae89\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"6cf830e91dff432ca423646999aee4eb\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"3101141d930547a58416fdd31fa04a0c\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"取向电工钢\",\"producer\":\"宝钢\",\"UUID\":\"c9113feb7a2e40bebf4e00ca145ad0ed\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.5*2000*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"0aacfc7b66054e00a135f58deeaf35e6\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"d4ab361444aa4fcc882d11bf7c86399d\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"53b8c6d5fa8f4004a5a5ba0b0d4b9202\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"75e756e0141942a4bd0dbdb25cda74f2\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"2b4594055c774e32bdeef079a6e676d3\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"6e8400147a7049eb8d73ba634ececd73\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"38319e74cbf3411badfcd4fa89100a62\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"f9cbafdcf3a940df9416c6bfc56855ed\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"01ec7ee185974c0db0d6bed90c882b05\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"da094274d1d8491eac62d84f5f9efbb5\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"9827029de58c479ba3fa1b0c9d7cc362\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"},{\"variety\":\"冷轧卷\",\"producer\":\"宝钢\",\"UUID\":\"4b11db2a38b74aca8901639be2fc0812\",\"warehouse\":\"上海欧珏供应链管理有限公司宝杨库\",\"spec\":\"1.2*1250*C\"}]";
        int length = str.length();
        System.out.println(length);

    }

    public static void test9(ResourceTemp resourceTemp) {
        if (!"".equals(resourceTemp.getSpec1()) && !"0".equals(resourceTemp.getSpec1())
                && !"".equals(resourceTemp.getSpec3()) && !"0".equals(resourceTemp.getSpec3())
                && !"".equals(resourceTemp.getSpec5()) && !"0".equals(resourceTemp.getSpec5())) {
            String str = "";
            str += resourceTemp.getSpec1();
            str += "*" + resourceTemp.getSpec3();
            str += "*" + resourceTemp.getSpec5();
            resourceTemp.setSpec(str);
        } else {
            //规格
            String[] specArray = resourceTemp.getSpec().split("\\*");
            List<String> rangeMaxSpec = new ArrayList<>();
            for (int i = 0; i < specArray.length; i++) {
                String[] specRange = specArray[i].split("\\~|\\-");
                //每次取规格范围的最大值
                rangeMaxSpec.add(specRange[specRange.length - 1]);
            }
            resourceTemp.setSpec1(rangeMaxSpec.get(0).replace("Ф", "").replace("Φ", "").replace("φ", ""));

            if (rangeMaxSpec.size() == 2) {
                resourceTemp.setSpec5(rangeMaxSpec.get(1));
            }
//            if(Objects.){
//
//            }
//            resourceTemp.setSpec1("0");
//            resourceTemp.setSpec3("0");
//            resourceTemp.setSpec5("0");
            resourceTemp.setSpec(resourceTemp.getSpec1() + "*" + resourceTemp.getSpec3() + "*" + resourceTemp.getSpec5());
            System.out.println("1111111111");

        }


        System.out.println(JSON.toJSONString(resourceTemp));
    }

    //正则
    public static void test10(ResourceTemp resourceTemp) {
        String str = resourceTemp.getSpec();
        String regex = "\\*";
        Pattern expression = Pattern.compile(regex);
        Matcher matcher = expression.matcher(str);
        int n = 0;
        while (matcher.find()) {
            n++;
        }
        System.out.println(n);
        if (n == 3) {
            System.out.println("aaa");
        } else if (resourceTemp.getSpec().length() != 0) {
            System.out.println("bbb");
        }
    }

    public static void test11() {
        ResourceTemp resourceTemp = new ResourceTemp();

        String spec = "3.00 * 1250.00 * C";
        String trim = spec.replaceAll(" ", "");


        String[] specArray = trim.split("\\*");
        List<String> rangeMaxSpec = new ArrayList<>();
        for (int i = 0; i < specArray.length; i++) {
            String[] specRange = specArray[i].split("\\~|\\-");
            //每次取规格范围的最大值
            rangeMaxSpec.add(specRange[specRange.length - 1]);
        }
        resourceTemp.setSpec1(rangeMaxSpec.get(0).replace("Ф", "").replace("Φ", "").replace("φ", ""));

        if (rangeMaxSpec.size() == 2) {
            resourceTemp.setSpec5(rangeMaxSpec.get(1));
        }
        if (rangeMaxSpec.size() >= 3) {
            resourceTemp.setSpec3(rangeMaxSpec.get(1));
            if (!rangeMaxSpec.get(rangeMaxSpec.size() - 1).equalsIgnoreCase("C")) {
                resourceTemp.setSpec5(rangeMaxSpec.get(rangeMaxSpec.size() - 1));
            } else {
                resourceTemp.setSpec5("0");
            }
        }
        System.out.println(trim);
        System.out.println(JSON.toJSONString(resourceTemp));

    }

    public static void test12() {
        String beginTime = "2019-03-28";

        String s = beginTime.substring(5, 9).replaceAll("-", "/");
        System.out.println(s);

        String firstDay = "2019-04-15";
        if (firstDay.compareTo(beginTime) > 0) {
            System.out.println("aaaaaaaaa");
        } else {
            System.out.println("bbbbbbb");
        }
    }

    public static void test13() {
        BigDecimal allPrice = new BigDecimal(10);
        int allDays = 10;
        int avgNum = allPrice.divide(new BigDecimal(allDays), 2, BigDecimal.ROUND_HALF_UP).subtract(new BigDecimal(45.2)).intValue();
        System.out.println(avgNum);
    }

    public static void test14() {
        final String url = "https://yunzhi.baidu.com/api/ouyeel/text2record/v1/analysis/excel";
        RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(10000).setSocketTimeout(30000).setConnectionRequestTimeout(20000);
//        MultipartEntityBuilder multipartEntityBuilder = MultipartEntityBuilder.create().setMode(HttpMultipartMode.RFC6532);


        FileInputStream inputStream = null;
        try {
            inputStream = new FileInputStream(new File("C:\\Users\\CS\\Downloads\\板材类模板_不锈钢.xls"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }


        File file = new File("C:\\Users\\CS\\Downloads\\板材类模板_不锈钢.xls");
//        multipartEntityBuilder.addBinaryBody("file", inputStream, ContentType.DEFAULT_BINARY, "板材类模板_不锈钢.xls");
//        multipartEntityBuilder.addBinaryBody("file", inputStream, ContentType.APPLICATION_OCTET_STREAM, "板材模板.xls");
//        HttpEntity entity = multipartEntityBuilder.build();

        HttpPost httpPost = new HttpPost(url);
        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setHeader("cache-control", "no-cache");
        httpPost.setHeader("Authorization", "5X#jr9T7bz");
        httpPost.setHeader("request_id", "11112222333344445555666677778888");
        httpPost.setConfig(builder.build());
//        httpPost.setEntity(entity);

        CloseableHttpClient client = HttpClientBuilder.create().build();
        try {
            HttpResponse httpResponse = client.execute(httpPost);
            System.out.println(JSON.toJSONString(httpResponse));
            httpPost.releaseConnection();
            client.close();
        } catch (Exception e) {
            System.out.println(e);
        }

    }

    public static void test15() {
        String s1 = "∮∮1 * 100*0";
        String s2 = "∮Φ2 * 200*0";
        String s3 = "∮φ3 * 300*0";

        String s4 = "ΦΦ4 * 400*0";
        String s5 = "Φ∮5 * 500*0";
        String s6 = "Φφ6 * 600*0";

        String s7 = "φφ7 * 700*0";
        String s8 = "φ∮8 * 800*0";
        String s9 = "φΦ9 * 900*0";
        List<String> list = new ArrayList<>();
        list.add(s1);
        list.add(s2);
        list.add(s3);
        list.add(s4);
        list.add(s5);
        list.add(s6);
        list.add(s7);
        list.add(s8);
        list.add(s9);
        Set<String> strings = new HashSet<>();
        strings.add("∮");
        strings.add("Φ");
        strings.add("φ");

        for (String str : list) {
            String sub1 = str.substring(0, 1);
            String sub2 = str.substring(1, 2);
            if (strings.contains(sub1) && strings.contains(sub2)) {
                str = "φ" + str.substring(2, str.length());
                str = str.substring(str.length()-1,str.length());
                System.out.println(str);
            }


        }





    }

    static class InitialValue{
        boolean bo;
        char c;
        byte b;
        short s;
        int i;
        long l;
        float f;
        double d;
        InitialValue reference;
        void printInitialValue(){
            System.out.println("Data type        Initial value");
            System.out.println("boolean     "+bo);
            System.out.println("char     "+c);
            System.out.println("byte     "+b);
            System.out.println("short     "+s);
            System.out.println("int     "+i);
            System.out.println("long     "+l);
            System.out.println("float     "+f);
            System.out.println("double     "+d);
        }
    }

    public static void main(String[] args) {
        //        List<String> list = Arrays.asList("1", "2", "3", "4");
//
//        List<Integer> dataList = new ArrayList<Integer>();
//        for (int i = 1; i < 18; i++) {
//            dataList.add(i);
//        }
//        ChuLi.test(dataList);
        Map<String,String> hashMap = new HashMap();
        String s = hashMap.get("1111");
        System.out.println(s);

    }

}



class ResourceTemp{
    private String spec;

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

    public String getSpec1() {
        return spec1;
    }

    public void setSpec1(String spec1) {
        this.spec1 = spec1;
    }

    public String getSpec3() {
        return spec3;
    }

    public void setSpec3(String spec3) {
        this.spec3 = spec3;
    }

    public String getSpec5() {
        return spec5;
    }

    public void setSpec5(String spec5) {
        this.spec5 = spec5;
    }

    private String spec1;
    private String spec3;
    private String spec5;
}