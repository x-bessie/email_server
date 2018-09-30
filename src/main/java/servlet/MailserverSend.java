package servlet;

import io.netty.buffer.ByteBuf;
import io.netty.buffer.Unpooled;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.DefaultFullHttpResponse;
import io.netty.handler.codec.http.FullHttpResponse;
import io.netty.handler.codec.http.HttpRequest;
import io.netty.handler.codec.http.QueryStringDecoder;
import utils.Emailutil;
import utils.JSONSerializer;

import java.io.UnsupportedEncodingException;
import java.util.HashMap;
import java.util.Map;

import static io.netty.handler.codec.http.HttpResponseStatus.OK;
import static io.netty.handler.codec.http.HttpVersion.HTTP_1_1;

public class MailserverSend {

    public void hand(HttpRequest request, ChannelHandlerContext ctx) throws Exception {
        Map<String, String> parmMap = new HashMap<>();
        QueryStringDecoder decoder = new QueryStringDecoder(request.uri());
        decoder.parameters().entrySet().forEach(entry -> {
            parmMap.put(entry.getKey(), entry.getValue().get(0));
        });
        String address = parmMap.get("address");
        String text = parmMap.get("text");
        String title = parmMap.get("title");
        String msg = "发送失败";
        Emailutil ds = new Emailutil();
        if (ds.sendMail(address, text, title)) {
            msg = "发送成功";
        }
        FullHttpResponse response = new DefaultFullHttpResponse(HTTP_1_1, OK, getSendByteBuf(msg));
        response.headers().set("Content-Type", "text/json;charset=UTF-8");
        response.headers().setInt("Content-Length", response.content().readableBytes());
        ctx.writeAndFlush(response);
    }

    private ByteBuf getSendByteBuf(String message) throws UnsupportedEncodingException {

        byte[] req = message.getBytes("UTF-8");
        ByteBuf pingMessage = Unpooled.buffer();
        pingMessage.writeBytes(req);

        return pingMessage;
    }


}
