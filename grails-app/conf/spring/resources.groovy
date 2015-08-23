import com.skiwi.fragments.server.FragmentsServer

// Place your Spring DSL code here
beans = {
    fragmentsServer(FragmentsServer, "localhost", "6644") { }
}
