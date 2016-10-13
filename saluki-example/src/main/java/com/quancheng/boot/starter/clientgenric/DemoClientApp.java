package com.quancheng.boot.starter.clientgenric;

import org.lognet.springboot.grpc.proto.GreeterGrpc;
import org.lognet.springboot.grpc.proto.GreeterOuterClass;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import com.quancheng.boot.saluki.starter.SalukiReference;
import com.quancheng.saluki.core.grpc.service.GenericService;

@SpringBootApplication
public class DemoClientApp implements CommandLineRunner {

    @SalukiReference(service = "com.quancheng.boot.starter.server.GreeterService", group = "default", version = "1.0.0")
    private GenericService genricService;

    @Override
    public void run(String... args) throws Exception {
        String serviceName = "com.quancheng.boot.starter.server.GreeterService";
        String method = "SayHello";
        String[] parameterTypes = new String[] { "com.quancheng.boot.starter.model.HelloRequest",
                                                 "com.quancheng.boot.starter.model.HelloReply" };
        com.quancheng.boot.starter.model.HelloRequest request = new com.quancheng.boot.starter.model.HelloRequest();
        request.setName("joe");
        Object[] args1 = new Object[] { request };

        com.quancheng.boot.starter.model.HelloReply reply = (com.quancheng.boot.starter.model.HelloReply) genricService.$invoke(serviceName,
                                                                                                                                method,
                                                                                                                                parameterTypes,
                                                                                                                                args1);

        System.out.println(reply.getMessage());

    }

    public static void main(String[] args) {

        SpringApplication.run(DemoClientApp.class, args);
    }
}
