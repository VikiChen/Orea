package com.chinadep.oreo.handler;

import com.chinadep.oreo.util.FileUploadFile;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.*;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import io.netty.handler.codec.serialization.ClassResolvers;
import io.netty.handler.codec.serialization.ObjectDecoder;
import io.netty.handler.codec.serialization.ObjectEncoder;
import org.springframework.stereotype.Component;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.HashMap;
import java.util.Map;

@Component
@ChannelHandler.Sharable
public class FileUploadClientHandler extends ChannelInboundHandlerAdapter {
    int lastLength = 1000;
    long start=0;
    long end=0;
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("active");
        try {
            Map map =new HashMap();
            byte[] bytes = new byte[0];
            map.put("byte",bytes);
            map.put("start",start);
            map.put("end",end);
            map.put("flag",false);
            ctx.writeAndFlush(map);
        } catch (Exception e) {
            e.getStackTrace();
        }
//            super.channelActive(ctx);
    }

    @Override
    public void channelInactive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("Inactieve");
        super.channelInactive(ctx);
    }

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        start=(long)msg;
        System.out.println("从这里开始"+start);
        RandomAccessFile randomAccessFile = new RandomAccessFile("D:\\test\\1.jpg",
                "r");
        randomAccessFile.seek(start);
        byte[] bytes = new byte[lastLength];
        int hasRead=randomAccessFile.read(bytes);
        Map map= new HashMap();
        if(hasRead==-1){
            map.put("flag",true);
        }else{
            map.put("flag",false);
        }
        end=start+hasRead;
        map.put("byte",bytes);
        map.put("start",start);
        map.put("end",end);
        if (hasRead!= -1) {
            System.out.println(hasRead);
            ctx.writeAndFlush(map);
        } else {
            ctx.close();
        }

//            super.channelRead(ctx, msg);
    }

}
