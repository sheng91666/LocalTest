package WorkTest;

import com.alibaba.fastjson.JSON;
import org.apache.http.HttpEntity;
import org.apache.http.HttpHost;
import org.apache.http.HttpStatus;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.CloseableHttpResponse;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.entity.StringEntity;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.util.EntityUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.*;

public class HttpTest {

    /**
     * 调用对方接口方法
     *
     * @param path 对方或第三方提供的路径
     * @param data 向对方或第三方发送的数据，大多数情况下给对方发送JSON数据让对方解析
     */
    public static void interfaceUtil(String path, String data) {
        try {
            URL url = new URL(path);
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();
            conn.setRequestMethod("POST");
            //设置通用的请求属性
            conn.setRequestProperty("Content-Type", "application/json");
            conn.setRequestProperty("cache-control", "no-cache");
            conn.setRequestProperty("Authorization", "5X#jr9T7bz");
            conn.setRequestProperty("request_id", "11112222333344445555666677778888");
            //设置是否向httpUrlConnection输出，设置是否从httpUrlConnection读入，此外发送post请求必须设置这两个
            conn.setDoOutput(true);
            conn.setDoInput(true);

            PrintWriter out = new PrintWriter(conn.getOutputStream());
            //发送请求参数即数据
            out.print(data);

            out.flush();
            InputStream is = conn.getInputStream();


            BufferedReader br = new BufferedReader(new InputStreamReader(is));
            String str = "";
            while ((str = br.readLine()) != null) {
                System.out.println(str);
            }

            is.close();//关闭流 断开连接，最好写上，disconnect是在底层tcp socket链接空闲时才切断。如果正在被其他线程使用就不切断。
            conn.disconnect();//固定多线程的话，如果不disconnect，链接会增多，直到收发不出信息。写上disconnect后正常一些。
            System.out.println("完整结束");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    public static void main(String[] args) {
        vo vo1 = new vo();
        vo1.setProducer("宝特长材");
        vo1.setSpec("8.5");
        vo1.setWarehouse("上海欧珏供应链管理有限公司三冠库");
        vo1.setVariety("盘条");

        vo vo2 = new vo();
        vo2.setVariety("冷轧卷");
        vo2.setWarehouse("天津汇金金属材料加工有限公司");
        vo2.setSpec("8.5");
        vo2.setProducer("宝钢");

        List<vo> voList = new ArrayList<>();
        voList.add(vo1);
        voList.add(vo2);

        List<Map<String, String>> list2 = new ArrayList<>();
        Map<String, String> map1 = new HashMap<>();
        map1.put("warehouse", "天津汇金金属材料加工有限公司");
        map1.put("producer", "宝钢");
        map1.put("variety", "冷轧卷");
        map1.put("spec", "8.5");
        map1.put("UUID", "abc");
        Map<String, String> map2 = new HashMap<>();
        map2.put("warehouse", "上海欧珏供应链管理有限公司三冠库");
        map2.put("producer", "宝特长材");
        map2.put("variety", "盘条");
        map2.put("spec", "8.5*b");
        map2.put("UUID", "abc");

        list2.add(map1);
        list2.add(map2);
        String url = "https://yunzhi.baidu.com/api/ouyeel/text2record/v1/analysis/db";
        String data = JSON.toJSONString(list2);

//        interfaceUtil("https://yunzhi.baidu.com/api/ouyeel/text2record/v1/analysis/db", JSON.toJSONString(voList));
        final String encoding = "UTF-8";
        String uuid = UUID.randomUUID().toString().replace("-", "");
        try {
            HttpClient httpClient = new HttpClient();
            httpClient.queryBaiDuApi(url, data, uuid, encoding);
        } catch (Exception e) {
            System.out.println("HttpTest error");
        }
    }
}

class HttpClient {
    private Logger log = LoggerFactory.getLogger(HttpClient.class);

    /**
     * HttpClient
     *
     * @param url
     * @param jsonStr
     * @param uuid
     * @param encoding
     * @return
     * @throws IOException
     */
    public String queryBaiDuApi(String url, String jsonStr, String uuid, String encoding) throws Exception {
        RequestConfig.Builder builder = RequestConfig.custom().setConnectTimeout(3000).setSocketTimeout(300000);

        //10.60.164.32  443
        log.info(" ----- 代理服务器配置 ------");
        log.info(" ----- IP {}", "10.60.164.32");
        log.info(" ----- Port {}", "443");
        HttpHost proxy = new HttpHost("10.60.164.32", Integer.valueOf("443"));
        builder.setProxy(proxy);

        HttpPost httpPost = new HttpPost(url);
        CloseableHttpClient client = HttpClients.createDefault();

        httpPost.addHeader("Content-Type", "application/json");
        httpPost.setHeader("cache-control", "no-cache");
        httpPost.setHeader("Authorization", "5X#jr9T7bz");
        httpPost.setHeader("request_id", uuid);

        httpPost.setConfig(builder.build());
        StringEntity entity = new StringEntity(jsonStr, encoding);
        httpPost.setEntity(entity);
        CloseableHttpResponse httpResponse = client.execute(httpPost);

        String response;
        int statusCode = httpResponse.getStatusLine().getStatusCode();
        httpPost.abort();//手动关闭连接
        if (HttpStatus.SC_OK == statusCode) {
            HttpEntity httpEntity = httpResponse.getEntity();
            response = EntityUtils.toString(httpEntity, encoding);
            System.out.println(JSON.toJSONString(response));
        } else {
            Map statusCodeMap = new HashMap<String, String>() {
                private static final long serialVersionUID = 1L;

                {
                    put("400", "请求参数错误");
                    put("401", "未通过权限校验");
                    put("500", "内部错误");
                }
            };

            if (!statusCodeMap.containsKey(statusCode)) {
                log.info("调百度api返回状态--{}", statusCode);
                log.info(EntityUtils.toString(httpResponse.getEntity(), encoding));
                throw new Exception(String.format("调百度api返回值 %s from server %s .", statusCode, url));
            } else {
                log.info("调百度api返回状态--{}", statusCodeMap.get(statusCode).toString());
                throw new Exception(String.format("调百度api返回值 %s from server %s .", statusCode, url));
            }
        }
        return response;
    }
}

class vo {
    private String warehouse;
    private String producer;
    private String variety;
    private String spec;

    public String getWarehouse() {
        return warehouse;
    }

    public void setWarehouse(String warehouse) {
        this.warehouse = warehouse;
    }

    public String getProducer() {
        return producer;
    }

    public void setProducer(String producer) {
        this.producer = producer;
    }

    public String getVariety() {
        return variety;
    }

    public void setVariety(String variety) {
        this.variety = variety;
    }

    public String getSpec() {
        return spec;
    }

    public void setSpec(String spec) {
        this.spec = spec;
    }

}
