package com.winterframework.logistics.device.server;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.winterframework.logistics.device.server.protocol.Delimiters;

import io.netty.bootstrap.ServerBootstrap;
import io.netty.buffer.PooledByteBufAllocator;
import io.netty.channel.AdaptiveRecvByteBufAllocator;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.SocketChannel;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import io.netty.handler.codec.DelimiterBasedFrameDecoder;
import io.netty.handler.codec.string.StringDecoder;
import io.netty.handler.codec.string.StringEncoder;
import io.netty.handler.logging.LoggingHandler;
import io.netty.handler.timeout.IdleStateHandler;

public class DeviceServer implements Runnable {
	protected Logger log = LoggerFactory.getLogger(DeviceServer.class);
	
	private int port;  

	public DeviceServer(int port) {
		this.port = port;
	}
	@Override
	public void run(){
		EventLoopGroup bossGroup = new NioEventLoopGroup(); // (1)	--NioEventLoopGroup(线程数) 默认值：CPU内核数 × 2
		EventLoopGroup workerGroup = new NioEventLoopGroup();
		try {
			ServerBootstrap b = new ServerBootstrap(); // (2)
			b.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class) // (3)
					.childHandler(new ChannelInitializer<SocketChannel>() { // (4)
								@Override                                                                                                                                      
								public void initChannel(SocketChannel ch) throws Exception {
									ch.pipeline().addFirst("frameDecoder", new DelimiterBasedFrameDecoder(Integer.MAX_VALUE,false,Delimiters.protocolDelimiter()));
									//ch.pipeline().addLast("stringDecoder",new StringDecoder(CharsetUtil.UTF_8));
									ch.pipeline().addLast(new LoggingHandler());
									ch.pipeline().addLast("decoder",new StringDecoder());
									ch.pipeline().addLast("encoder", new StringEncoder());
									ch.pipeline().addLast(new IdleStateHandler(420, 0, 0));	//12mins(大于两个心跳周期)
									ch.pipeline().addLast(new ServerHandler());
								}
								
							}).option(ChannelOption.SO_BACKLOG, 128) // (5)
							  //.option(ChannelOption.TCP_NODELAY, true) //启用或关于Nagle算法 实时性要求高则true 减少发送次数（流量）则false  
					.childOption(ChannelOption.SO_KEEPALIVE, true) // (6)
					.childOption(ChannelOption.RCVBUF_ALLOCATOR, AdaptiveRecvByteBufAllocator.DEFAULT) // (7)	合理设置接收和发送缓冲区容量
					.childOption(ChannelOption.ALLOCATOR, PooledByteBufAllocator.DEFAULT);	//内存池:完成ByteBuf的解码工作之后必须显式的调用ReferenceCountUtil.release(msg)对接收缓冲区ByteBuf进行内存释放，否则它会被认为仍然在使用中，这样会导致内存泄露

			// Bind and start to accept incoming connections.

			ChannelFuture f = b.bind(port).sync(); // (7) 
			//ChannelFuture fb=b.bind(80).sync();
			// Wait until the server socket is closed.
			// In this example, this does not happen, but you can do that to gracefully
			// shut down your server.
			f.channel().closeFuture().sync(); 
			//fb.channel().closeFuture().sync(); 
		} catch (InterruptedException e) {
			log.error("平台 服务 中断:", e);
		} finally {
			workerGroup.shutdownGracefully();
			bossGroup.shutdownGracefully(); 
			
		}
	}

	public static void main(String[] args) throws Exception {
		 
		int port;
		if (args.length > 0) {
			port = Integer.parseInt(args[0]);
		} else {
			port = 6090;
		}
		new DeviceServer(port).run();
	}
}