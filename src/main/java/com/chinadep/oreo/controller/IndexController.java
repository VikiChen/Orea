package com.chinadep.oreo.controller;

import com.chinadep.oreo.socket.NettyClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;


@RestController
public class IndexController {  // implements InitializingBean

    @Autowired
    NettyClient nettyClient;

    @RequestMapping("/")
    public String index(){
        return "Orea started!";
    }

    @RequestMapping("/start")
    public String start(Integer port,String ipAddr) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    nettyClient.connect(port,ipAddr,null);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });

        return "Data exchange is started";
    }
}

