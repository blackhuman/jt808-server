package org.yzh.web.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.yzh.framework.TCPServer;
import org.yzh.framework.codec.MessageDecoder;
import org.yzh.framework.codec.MessageEncoder;
import org.yzh.framework.mapping.HandlerMapper;
import org.yzh.framework.spring.SpringHandlerMapper;
import org.yzh.web.component.WebLogger;
import org.yzh.web.jt808.codec.JT808MessageDecoder;
import org.yzh.web.jt808.codec.JT808MessageEncoder;

@Configuration
public class NettyConfig {

    @Bean
    public TCPServer TCPServer(WebLogger webLogger) {
        TCPServer server = new TCPServer(9502, (byte) 0x7e, handlerMapper(), messageDecoder(), messageEncoder(), webLogger);
        server.startServer();
        return server;
    }

    @Bean
    public HandlerMapper handlerMapper() {
        return new SpringHandlerMapper("org.yzh.web.endpoint");
    }

    @Bean
    public MessageDecoder messageDecoder() {
        return new JT808MessageDecoder(Charsets.GBK);
    }

    @Bean
    public MessageEncoder messageEncoder() {
        return new JT808MessageEncoder(Charsets.GBK);
    }
}