package cn.tianyu.web;


import cn.tianyu.pojo.Manager;
import org.springframework.web.bind.annotation.*;

@RestController
public class HelloController {


    @RequestMapping("/service/provider/hello")
    public String hello(){
        return "hello provider 01!";
    }

    @RequestMapping("/service/provider/test")
    public String test(){
        System.out.println("服务提供者1");
        return "使用了Eureka注册中心的服务提供者01！";
    }

    @RequestMapping("/service/provider/testParam")
    public String test(@RequestParam("uname") String uname){
        return uname+"使用了Eureka注册中心的服务提供者01！";
    }

    @RequestMapping("/service/provider/manager")
    public Manager toManager(){
        Manager manager=new Manager(1,"zhangsan","zhangsan123");
        return manager;
    }
    @RequestMapping("/service/provider/managerParam")
    public Manager toManager(@RequestParam("id") int id, @RequestParam("username") String username, @RequestParam("password") String password){
        Manager manager=new Manager(id,username,password);
        return manager;
    }
    //    put
    @PutMapping("/service/provider/updatemanager")
    public Manager updateManager(@RequestParam("id") Integer id,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password) {
        Manager manager = new Manager();
        manager.setId(id);
        manager.setUsername(username);
        manager.setPassword(password);
        System.out.println(id+"-"+username+"-"+password);
        return manager;
    }


    //    Delete
    @DeleteMapping("/service/provider/deleteManager")
    public Manager deleteManager(@RequestParam("id") Integer id,
                                 @RequestParam("username") String username,
                                 @RequestParam("password") String password){
        Manager manager = new Manager();
        manager.setId(id);
        manager.setUsername(username);
        manager.setPassword(password);
        return manager;
    }


}
