package com.skiwi.fragments.server

import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Frank van Heeswijk
 */
class FragmentsWebSocketServer extends WebSocketServer {
    @Autowired
    private ServerMessageResolver serverMessageResolver

    private Map<WebSocket, User> webSocketUsers = [:]

    FragmentsWebSocketServer(ServerMessageResolver serverMessageResolver, InetSocketAddress address) {
        super(address)
        this.serverMessageResolver = serverMessageResolver
    }

    @Override
    void onOpen(final WebSocket connection, final ClientHandshake handshake) {
        println "Remote $connection.remoteSocketAddress connected"
    }

    @Override
    void onClose(final WebSocket connection, final int code, final String reason, final boolean remote) {
        println "Remote $connection.remoteSocketAddress disconnected, reason: $reason"
    }

    @Override
    void onMessage(final WebSocket connection, final String message) {
        def user = webSocketUsers[connection]
        def runnable = serverMessageResolver.resolveMessage(message, connection, user)
        runnable.run()
    }

    @Override
    void onError(final WebSocket connection, final Exception ex) {
        println "Remote $connection.remoteSocketAddress caused an error"
        ex.printStackTrace()
    }
}
