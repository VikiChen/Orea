package com.chinadep.oreo.handler;

import java.io.File;
import java.io.RandomAccessFile;
import java.util.Map;
import io.netty.channel.ChannelHandler;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import org.springframework.stereotype.Component;

@Component
@ChannelHandler.Sharable
public class FileUploadServerHandler extends ChannelInboundHandlerAdapter {
        @Override
        public void channelActive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("active");
            super.channelActive(ctx);
        }

        @Override
        public void channelInactive(ChannelHandlerContext ctx) throws Exception {
            System.out.println("Inactieve");
            ctx.flush();
            ctx.close();
            super.channelInactive(ctx);
        }

        @Override
        public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
            long start=0;
            Map map = (Map) msg;
            String path = "D:\\test\\2.jpg";
            File file = new File(path);
            if(!file.exists()){  //如果文件不存在 文件句柄从0开始
                start=0;
            }
            RandomAccessFile randomAccessFile = new RandomAccessFile(file, "rw");
            System.out.println("开始位置"+(Long) map.get("start"));
            randomAccessFile.seek((Long) map.get("start"));
            if(!(boolean)map.get("flag")){
                System.out.println("写之前大小"+randomAccessFile.length());
                byte[]  btye =(byte[]) map.get("byte");
                randomAccessFile.write(btye,0, (int) ((Long) map.get("end")-(Long) map.get("start")));
                System.out.println("写完大小"+randomAccessFile.length());
            }
            //ctx.writeAndFlush((Long) map.get("end"));
            long back=(Long) randomAccessFile.length();
            randomAccessFile.close();
            ctx.writeAndFlush(back);
//            randomAccessFile.close();
            // channelInactive(ctx);
            if((boolean)map.get("flag")){
                ctx.close();
            }


            //  super.channelRead(ctx, msg);
        }
}

