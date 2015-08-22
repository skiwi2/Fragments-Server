package com.skiwi.fragments.server

import groovy.transform.InheritConstructors
import org.java_websocket.WebSocket
import org.java_websocket.handshake.ClientHandshake
import org.java_websocket.server.WebSocketServer

/**
 * @author Frank van Heeswijk
 */
@InheritConstructors
class FragmentsWebSocketServer extends WebSocketServer {
    @Override
    void onOpen(final WebSocket connection, final ClientHandshake handshake) {
        println "open"
    }

    @Override
    void onClose(final WebSocket connection, final int code, final String reason, final boolean remote) {
        println "close"
    }

    @Override
    void onMessage(final WebSocket connection, final String message) {
        println "message " + message + " from " + connection.remoteSocketAddress
        connection.send(message)
    }

    @Override
    void onError(final WebSocket connection, final Exception ex) {
        println "error"
    }
}
