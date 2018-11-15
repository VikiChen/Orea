package com.chinadep.oreo.init;

import com.chinadep.oreo.socket.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Component
public class InitRunner implements CommandLineRunner {
    @Autowired
    NettyServer nettyServer;
    @Value("${orea.socket.port:0}")
    public Integer serverPort;

    @Override
    public void run(String... args) throws Exception {
        ExecutorService executorService = Executors.newCachedThreadPool();
        executorService.execute(new Runnable() {
            @Override
            public void run() {
                try {
                    nettyServer.bind(9990);
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        });
    }
}
