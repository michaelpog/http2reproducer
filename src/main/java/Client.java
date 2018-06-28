

import io.vertx.core.AbstractVerticle;
import io.vertx.core.Vertx;
import io.vertx.core.http.HttpClient;
import io.vertx.core.http.HttpClientOptions;
import io.vertx.core.http.HttpClientRequest;
import io.vertx.core.http.HttpVersion;


/*
 * @author <a href="http://tfox.org">Tim Fox</a>
 */
public class Client extends AbstractVerticle {

    // Convenience method so you can run it in your IDE
    public static void main(String[] args) {
        Vertx vertx = Vertx.vertx();
        vertx.deployVerticle(new Client());
    }

    @Override
    public void start() {
        HttpClientOptions options = new HttpClientOptions().setProtocolVersion(HttpVersion.HTTP_2);

        HttpClient client = vertx.createHttpClient(options);

        HttpClientRequest request = client.post(8888, "localhost", "/some_endpoint", resp -> {
            System.out.println("Got response " + resp.statusCode() + " with protocol " + resp.version());
            resp.bodyHandler(body -> System.out.println("Got data " + body.toString("ISO-8859-1")));
        });

        request.end("hi from client - 1");


        HttpClientRequest request2 = client.post(8888, "localhost", "/some_endpoint", resp -> {
            System.out.println("Got response " + resp.statusCode() + " with protocol " + resp.version());
            resp.bodyHandler(body -> System.out.println("Got data " + body.toString("ISO-8859-1")));
        });
        request2.end("hi from client - 2");
    }
}
