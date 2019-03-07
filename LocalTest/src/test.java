import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.stream.Collectors;


public class test {
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

    public static void test2(){
        String zhu = "T20433";
        String zi = "T20433";
        if(!zi.contains(zhu) && !zhu.contains(zi)){
            System.out.println("error");
        }else {
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

    public static void main(String[] args) {
        //        List<String> list = Arrays.asList("1", "2", "3", "4");
//
//        List<Integer> dataList = new ArrayList<Integer>();
//        for (int i = 1; i < 18; i++) {
//            dataList.add(i);
//        }
//        ChuLi.test(dataList);

        test2();
    }

}
