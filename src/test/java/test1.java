import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.http.FullHttpRequest;

import java.awt.event.ActionListener;
import java.lang.reflect.Method;

public class test1 {

    public static void main(String[] arsg) throws Exception{
        Class<?> clazz = Class.forName("servlet.MailserverSend");
        Method methods= clazz.getDeclaredMethod("hand", FullHttpRequest.class, ChannelHandlerContext.class);
    }
}
