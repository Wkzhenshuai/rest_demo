package cn.tianyu.controller;

import org.apache.catalina.Manager;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.HashMap;

@RestController
public class HelloController {
    @Autowired
    private RestTemplate restTemplate;

    private final String PUBLIC_URL = "http://SERVICE-PROVIDER:8080/service/provider";

    @RequestMapping("/service/consumer/hello")
    public String hello() {
        String url = PUBLIC_URL + "/hello";//服务提供者接口
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }

    @RequestMapping("/service/consumer/test")
    public String test() {
        String url = PUBLIC_URL + "/test";//服务提供者接口
        String result = restTemplate.getForObject(url, String.class);
        return result;
    }
    /*
     * getForEntity()三种用法：
     * 不带参
     * 带参：Array
     * 带参：Map
     * */

    @RequestMapping("/service/consumer/test1") //映射的地址
    public String test1() {
        String url = PUBLIC_URL + "/test";//服务者提供者接口
        ResponseEntity<String> responseEntity = restTemplate.getForEntity(url, String.class);
        int code = responseEntity.getStatusCodeValue(); //状态信息
        HttpStatus httpStatus = responseEntity.getStatusCode(); //状态码
        HttpHeaders httpHeaders = responseEntity.getHeaders(); //请求头 响应信息
        String body = responseEntity.getBody(); //内容
        System.out.println(code);
        System.out.println(httpHeaders);
        System.out.println(httpStatus);
        System.out.println(body);
        return responseEntity.getBody();
    }

    @RequestMapping("/service/consumer/manager")
    public Manager tomanager() {
        String url = PUBLIC_URL + "/manager";
        ResponseEntity<Manager> responseEntity = restTemplate.getForEntity(url, Manager.class);
        Manager manager = responseEntity.getBody();
        return manager;
    }

    @RequestMapping("/service/consumer/testParamString")
    public String testParamString() {
        String uname = "王阔";
        String url = PUBLIC_URL + "/testParam?uname={uname}";
        String result = restTemplate.getForObject(url, String.class, uname);
        return result;
    }

    //    数组为参数
    @RequestMapping("/service/consumer/managerParam")
    public Manager managerParam() {
        String[] paramArray = {"1", "里斯", "lisi"};
        String url = PUBLIC_URL + "/managerParam?id={0}&username={1}&password={2}";
        ResponseEntity<Manager> responseEntity = restTemplate.getForEntity(url, Manager.class, paramArray);
        return responseEntity.getBody();
    }


    //    map为参数
    @RequestMapping("/service/consumer/managerParamMap")
    public Manager managerParamMap() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "张三");
        map.put("password", "zhangsan");
        String url = PUBLIC_URL + "/managerParam?id={id}&username={username}&password={password}";
        ResponseEntity<Manager> responseEntity = restTemplate.getForEntity(url, Manager.class, map);
        return responseEntity.getBody();
    }


    /*getForObject()方法*/
    @RequestMapping("/service/consumer/manager1")
    public Manager getmanager() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "技术的");
        map.put("password", "大叔");
        String url = PUBLIC_URL + "/managerParam?id={id}&username={username}&password={password}";
        Manager manager = restTemplate.getForObject(url, Manager.class, map);
        return manager;
    }


    //数组
    @RequestMapping("/service/consumer/managerParam2")
    public Manager managerParam2() {
        String[] paramArray = {"1", "里斯", "lisi"};
        String url = PUBLIC_URL + "/managerParam?id={0}&username={1}&password={2}";
        Manager manager = restTemplate.getForObject(url, Manager.class, paramArray);
        return manager;
    }

    //map为参数
    @RequestMapping("/service/consumer/managerParamMap2")
    public Manager managerParamMap2() {
        HashMap<String, String> map = new HashMap<>();
        map.put("id", "1");
        map.put("username", "张三");
        map.put("password", "zhangsan");
        String url = PUBLIC_URL + "/managerParam?id={id}&username={username}&password={password}";
        Manager manager = restTemplate.getForObject(url, Manager.class, map);
        return manager;
    }


    /*post*/
    @RequestMapping("/service/consumer/manager3")
    public Manager tomanager3() {
        String url = PUBLIC_URL + "/manager";
        ResponseEntity<Manager> responseEntity = restTemplate.postForEntity(url, false, Manager.class);
        Manager manager = responseEntity.getBody();
        return manager;
    }


    //数组
    @RequestMapping("/service/consumer/managerParam3")
    public Manager managerParam3() {
        String[] paramArray = {"1", "按设计大师金克拉大", "lisi"};
        String url = PUBLIC_URL + "/managerParam?id={0}&username={1}&password={2}";
        ResponseEntity<Manager> postForEntity = restTemplate.postForEntity(url, true, Manager.class, paramArray);
        return postForEntity.getBody();
    }

    //    Map Post
    @RequestMapping("/service/consumer/managerParamMap3")
    public Manager managerParamMap3() {
        MultiValueMap<String, String> map = new LinkedMultiValueMap<>();
        map.add("id", "1");
        map.add("username", "张三");
        map.add("password", "zhangsan");
        String url = PUBLIC_URL + "/managerParam";
        ResponseEntity<Manager> responseEntity = restTemplate.getForEntity(url, Manager.class, map);
        return responseEntity.getBody();
    }

    //    PUT
    @RequestMapping("/service/consumer/updatemanager")
    public String updateManager() {
        MultiValueMap map = new LinkedMultiValueMap();
        map.add("id", "123");
        map.add("username", "123");
        map.add("password", "123");
        restTemplate.put(PUBLIC_URL + "/updatemanager", map);
        return "执行成功";
    }

//    Delete

    @DeleteMapping("/service/consumer/delete1")
    public String tomanager2() {
        String url = PUBLIC_URL + "/manager";
        restTemplate.delete(url, String.class);
        return "执行成功";
    }

    @DeleteMapping("/delete/consumer/deletemanager")
    public String deleteManager() {
        HashMap<String, Object> map = new HashMap<>();
        map.put("id", "121");
        map.put("username", "zhangsan");
        map.put("password", "zhangsan123");
        restTemplate.delete(PUBLIC_URL + "/deleteManager?id={id}&username={username}&password={password}", map);
        return "执行成功!";
    }
}