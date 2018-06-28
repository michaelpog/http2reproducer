import io.vertx.core.Vertx
import io.vertx.core.http.HttpMethod
import io.vertx.ext.web.Router
import io.vertx.ext.web.handler.BodyHandler

def vertx = Vertx.vertx()
def httpServer = vertx.createHttpServer()
def router = Router.router(vertx)
router.route().handler(BodyHandler.create())
def port = 8888
router.route(HttpMethod.POST, "/some_endpoint").handler({ routingContext ->
    System.out.println("REQUEST: ${routingContext.getBodyAsString()}")

    routingContext.response().end('response')
})


System.out.println("Starting HTTP server on port $port")
httpServer.requestHandler(router.&accept).listen(port)

