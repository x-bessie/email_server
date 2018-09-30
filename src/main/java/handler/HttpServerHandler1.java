package handler;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.ChannelInboundHandlerAdapter;
import io.netty.handler.codec.http.*;
import io.netty.util.CharsetUtil;
import utils.Emailutil;

import java.io.UnsupportedEncodingException;
import java.lang.reflect.Method;
import java.net.InetAddress;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class HttpServerHandler1 extends ChannelInboundHandlerAdapter {
    private String result = "";

    @Override
    public void channelRead(ChannelHandlerContext ctx, Object msg) throws Exception {
        if (!(msg instanceof FullHttpRequest)) {
            result = "未知请求!";
            send(ctx, result, HttpResponseStatus.BAD_REQUEST);
            return;
        }
        FullHttpRequest httpRequest = (FullHttpRequest) msg;
        try {
            String url = httpRequest.uri();          //获取路径
            //  HttpMethod method=httpRequest.method();//获取请求方法

            if (url.equals("/favicon.ico")) {
                ctx.close();
                return;
            }

            String text = getBody(httpRequest);
            Map<String, String> parmMap = new HashMap<>();
            QueryStringDecoder decoder = new QueryStringDecoder(httpRequest.uri());
            decoder.parameters().entrySet().forEach(entry -> {
                parmMap.put(entry.getKey(), entry.getValue().get(0));
            });
            String address = parmMap.get("address");//邮箱地址
            String title = "数据采漏测试";//邮件标题
            String msgs = "发送失败";
            Emailutil ds = new Emailutil();
            if (ds.sendMail(address, text, title)) {
                msgs = "发送成功";
            }
            FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, getSendByteBuf(msgs));
            response.headers().set("Content-Type", "text/json;charset=UTF-8");
//            response.headers().set("Content-Type", "multipart/form-data;charset=UTF-8");
            response.headers().setInt("Content-Length", response.content().readableBytes());
            ctx.writeAndFlush(response);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            //释放请求
            httpRequest.release();
        }
    }


    private ByteBuf getSendByteBuf(String message) throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }

    /**
     * 获取http请求body参数
     *
     * @param request
     * @return
     */
    private String getBody(FullHttpRequest request) {
        ByteBuf buf = request.content();
        return buf.toString(CharsetUtil.UTF_8);
    }

    /**
     * 发送的返回值
     *
     * @param ctx     返回
     * @param context 消息
     * @param status  状态
     */
    private void send(ChannelHandlerContext ctx, String context, HttpResponseStatus status) {
        FullHttpResponse response = new DefaultFullHttpResponse(HttpVersion.HTTP_1_1, status, Unpooled.copiedBuffer(context, CharsetUtil.UTF_8));
        response.headers().set(HttpHeaderNames.CONTENT_TYPE, "text/plain; charset=UTF-8");
        ctx.writeAndFlush(response).addListener(ChannelFutureListener.CLOSE);
    }

    /*
     * 建立连接时，返回消息
     */
    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        System.out.println("连接的客户端地址:" + ctx.channel().remoteAddress());
        ctx.writeAndFlush("客户端" + InetAddress.getLocalHost().getHostName() + "成功与服务端建立连接！ ");
        super.channelActive(ctx);
    }
}
