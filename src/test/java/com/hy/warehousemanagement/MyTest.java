package com.hy.warehousemanagement;

import com.alibaba.fastjson.JSONObject;
import com.hy.warehousemanagement.pojo.GoodsManagement;
import org.apache.commons.httpclient.HttpClient;
import org.apache.commons.httpclient.methods.InputStreamRequestEntity;
import org.apache.commons.httpclient.methods.PostMethod;
import org.apache.commons.httpclient.methods.RequestEntity;
import org.junit.jupiter.api.Test;

import java.io.*;

public class MyTest {

    @Test
    public void test2() throws IOException {
        String filePath = "C:\\Users\\EDZ\\Desktop\\1.txt";
        String encoding = "UTF-8";
        int count = 0;
        GoodsManagement goodsManagement = new GoodsManagement();
        File file = new File(filePath);
        if (file.isFile() && file.exists()) { //判断文件是否存在
            InputStreamReader read = new InputStreamReader(new FileInputStream(file), encoding);//考虑到编码格式
            BufferedReader bufferedReader = new BufferedReader(read);
            String lineTxt = null;
            while ((lineTxt = bufferedReader.readLine()) != null) {
                long l = new Double((int) (Math.random() * 1000)).longValue();
                count ++;
                if(count % 5 == 0 || count % 3 == 0) {
                    l = l + 500;
                }
                goodsManagement.setGoodsName(lineTxt);
                goodsManagement.setQuantityCeiling(1000L);
                goodsManagement.setQuantityFloor(0L);
                goodsManagement.setGoodsNumber(l);
            }
            read.close();
        } else {
            System.out.println("找不到指定的文件");
        }
    }

    public static void post(GoodsManagement goodsManagement,String url) throws IOException {
        // 服务端通常是根据请求头（headers）中的 Content-Type 字段来获知请求中的消息主体是用何种方式编码，再对主体进行解析。所以说到 POST 提交数据方案，包含了 Content-Type 和消息主体编码方式两部分
        HttpClient client = new HttpClient(); // 客户端实例化
        PostMethod postMethod = new     PostMethod(url); // 请求方法post，可以将请求路径传入构造参数中
        postMethod.addRequestHeader("Content-type", "application/json; charset=utf-8");

        byte[] requestBytes = JSONObject.toJSONString(goodsManagement).getBytes("utf-8"); // 将参数转为二进制流
        InputStream inputStream = new ByteArrayInputStream(requestBytes, 0, requestBytes.length);
        RequestEntity requestEntity = new InputStreamRequestEntity(inputStream, requestBytes.length, "application/json; charset=utf-8"); // 请求体
        postMethod.setRequestEntity(requestEntity);   // 将参数放入请求体

        int i = client.executeMethod(postMethod);  // 执行方法
        System.out.println("请求状态"+i);
        // 这里因该有判断的，根据请求状态判断请求是否成功，然后根据第三方接口返回的数据格式，解析出我们需要的数据

        byte[] responseBody = postMethod.getResponseBody(); // 得到相应数据
        String s = new String(responseBody);
        System.out.println(s);

    }

    @Test
    public void test1() throws IOException {
        GoodsManagement goodsManagement = new GoodsManagement();
        goodsManagement.setGoodsName("变形金刚");
        goodsManagement.setQuantityCeiling(1000L);
        goodsManagement.setQuantityFloor(0L);
        goodsManagement.setGoodsNumber(500L);
        post(goodsManagement,"http://localhost:8081/warehouse/goods-add");
    }
}
