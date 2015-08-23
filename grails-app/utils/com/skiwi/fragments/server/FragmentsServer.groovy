package com.skiwi.fragments.server

import org.java_websocket.server.WebSocketServer

import javax.annotation.PostConstruct
import javax.annotation.PreDestroy

/**
 * @author Frank van Heeswijk
 */
class FragmentsServer {
    private String host
    private int port

    private WebSocketServer webSocketServer

    FragmentsServer(String host, int port) {
        this.host = host
        this.port = port

        webSocketServer = new FragmentsWebSocketServer(new InetSocketAddress(host, port))
    }

    @PostConstruct
    void init() {
        webSocketServer.start()
    }

    @PreDestroy
    void cleanUp() {
        webSocketServer.stop()
    }
}
