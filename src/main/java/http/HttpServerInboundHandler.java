package http;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpContent;
import io.netty.handler.codec.http.HttpHeaders;
import io.netty.handler.codec.http.HttpHeaders.Values;

import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.HttpResponseStatus;
import io.netty.handler.codec.http.HttpVersion;

public class HttpServerInboundHandler extends ChannelInboundHandlerAdapter{

	private HttpRequest request;
	
	@Override
	public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception{
		if(msg instanceof HttpRequest){
			request = (HttpRequest) msg;
			String uri = request.getUri();
			System.out.println("URI"+ uri);
		}
		if(msg instanceof HttpContent){
			HttpContent content = (HttpContent) msg;
			ByteBuf buf = content.content();
			System.out.println(buf.toString());
			buf.release();
			
			String res = "I AM OK";
			FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1,
					HttpResponseStatus.OK, Unpooled.wrappedBuffer(res.getBytes()));
			response.headers().set(HttpHeaders.Names.CONTENT_TYPE, "text/plain");
            response.headers().set(HttpHeaders.Names.CONTENT_LENGTH,response.content().readableBytes());
            if(HttpHeaders.isKeepAlive(request)){
            	response.headers().set(HttpHeaders.Names.CONNECTION,Values.KEEP_ALIVE);
            }
            ctx.write(response);
            ctx.flush();
		}
	}
	
	@Override
	public void channelReadComplete(ChannelHandlerContext ctx)throws Exception{
		ctx.flush();
	}
	
	@Override
	public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause){
		ctx.close();
	}
}
