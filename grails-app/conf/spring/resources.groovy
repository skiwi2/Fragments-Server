import com.skiwi.fragments.server.FragmentsServerService

// Place your Spring DSL code here
beans = {
    fragmentsServerService(FragmentsServerService, "localhost", "6644") { }
}
