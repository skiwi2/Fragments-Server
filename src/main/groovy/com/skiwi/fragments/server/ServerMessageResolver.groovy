package com.skiwi.fragments.server

import org.java_websocket.WebSocket
import org.springframework.beans.factory.annotation.Autowired

/**
 * @author Frank van Heeswijk
 */
class ServerMessageResolver {
    @Autowired
    ClientHandler clientHandler

    @Autowired
    UserHandler userHandler

    Runnable resolveMessage(String message, WebSocket connection, User user) {
        def command
        def rawArguments = []
        if (message.contains("|")) {
            def parts = message.split("\\|")
            command = parts[0].trim()
            rawArguments = parts[1].trim().split(" ").toList()
        }
        else {
            command = message.trim()
        }
        def arguments = rawArguments.collect { coerce(it) }
        return resolveDecomposedMessage(command, arguments, connection, user)
    }

    private Runnable resolveDecomposedMessage(String command, List<Object> arguments, WebSocket connection, User user) {
        if (userHandler.class.declaredMethods.findAll { !it.synthetic }.name.contains(command)) {
            if (user == null) {
                println "Warning: Attempted to execute command $command with arguments $arguments without a valid user"
                return { }
            }
            def methodArguments = ([connection, user] + arguments).toArray()
            return { userHandler.invokeMethod(command, methodArguments) }
        }
        else if (clientHandler.class.declaredMethods.findAll { !it.synthetic }.name.contains(command)) {
            def methodArguments = ([connection] + arguments).toArray()
            return { clientHandler.invokeMethod(command, methodArguments) }
        }
        else {
            println "Warning: No handler found for command $command with arguments $arguments"
            return { }
        }
    }

    private static Object coerce(String value) {
        if (value.isInteger()) {
            return value.toInteger()
        }
        return value
    }
}
