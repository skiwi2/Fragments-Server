package com.skiwi.fragments.server

import org.java_websocket.server.WebSocketServer
import org.springframework.beans.factory.annotation.Autowired

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * @author Frank van Heeswijk
 */
class FragmentsServer {
    @Autowired
    private ServerMessageResolver serverMessageResolver

    private String host
    private int port

    private WebSocketServer webSocketServer

    FragmentsServer(String host, int port) {
        this.host = host
        this.port = port
    }

    @PostConstruct
    void init() {
        webSocketServer = new FragmentsWebSocketServer(serverMessageResolver, new InetSocketAddress(host, port))
        webSocketServer.start()
    }

    @PreDestroy
    void cleanUp() {
        webSocketServer.stop()
    }
}
