package com.skiwi.fragments.server

import groovy.transform.InheritConstructors
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer
import org.springframework.context.ApplicationContext
import server.User

/**
 * @author Frank van Heeswijk
 */
@InheritConstructors
class FragmentsWebSocketServer extends WebSocketServer {
    //TODO fix Spring Beans
    ServerMessageResolver serverMessageResolver = new ServerMessageResolver()

    private Map<WebSocket, User> webSocketUsers = [:]

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
