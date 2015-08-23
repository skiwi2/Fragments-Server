import com.skiwi.fragments.server.FragmentsServer

// Place your Spring DSL code here
beans = {
    fragmentsServer(FragmentsServer, '${webSocketServer.host}', '${webSocketServer.port}') { }
}
