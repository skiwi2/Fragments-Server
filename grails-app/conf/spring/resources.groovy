import com.skiwi.fragments.server.ClientHandler
import com.skiwi.fragments.server.FragmentsServer
import com.skiwi.fragments.server.ServerMessageResolver
import com.skiwi.fragments.server.UserHandler

// Place your Spring DSL code here
beans = {
    fragmentsServer(FragmentsServer, '${webSocketServer.host}', '${webSocketServer.port}') { bean ->
        bean.autowire = "byName"
    }

    serverMessageResolver(ServerMessageResolver) { bean ->
        bean.autowire = "byName"
    }

    clientHandler(ClientHandler) { bean ->
        bean.autowire = "byName"
    }

    userHandler(UserHandler) { bean ->
        bean.autowire = "byName"
    }
}
