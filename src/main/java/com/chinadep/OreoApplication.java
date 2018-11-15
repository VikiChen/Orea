package com.chinadep;

import com.chinadep.oreo.socket.NettyServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class OreoApplication {

    public static void main(String[] args) {
        SpringApplication.run(OreoApplication.class, args);
    }

}
