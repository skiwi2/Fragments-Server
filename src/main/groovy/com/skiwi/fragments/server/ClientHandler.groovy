package com.skiwi.fragments.server

import org.java_websocket.WebSocket

/**
 * @author Frank van Heeswijk
 */
class ClientHandler {
    def ping(WebSocket connection) {
        connection.send("pong")
    }
}
