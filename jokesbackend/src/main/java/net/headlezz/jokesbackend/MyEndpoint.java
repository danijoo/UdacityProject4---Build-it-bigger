/*
   For step-by-step instructions on connecting your Android application to this backend module,
   see "App Engine Java Endpoints Module" template documentation at
   https://github.com/GoogleCloudPlatform/gradle-appengine-templates/tree/master/HelloEndpoints
*/

package net.headlezz.jokesbackend;

import com.google.api.server.spi.config.Api;
import com.google.api.server.spi.config.ApiMethod;
import com.google.api.server.spi.config.ApiNamespace;

import net.headlezz.jokeprovider.JokeProvider;

/**
 * An endpoint class we are exposing
 */
@Api(
        name = "myApi",
        version = "v1",
        namespace = @ApiNamespace(
                ownerDomain = "jokesbackend.headlezz.net",
                ownerName = "jokesbackend.headlezz.net",
                packagePath = ""
        )
)
public class MyEndpoint {

    JokeProvider jp = new JokeProvider();

    /**
     * A simple endpoint method that takes a name and says Hi back
     */
    @ApiMethod(name = "tellJoke")
    public Joke tellJoke () throws InterruptedException {
        return new Joke(jp.getRandomJoke());
    }

}
