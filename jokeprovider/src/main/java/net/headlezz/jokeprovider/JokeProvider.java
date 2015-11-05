package net.headlezz.jokeprovider;

import java.util.Random;

/**
 * Provder class for jokes
 */
public class JokeProvider {

    Random random = new Random();

    /**
     * jokes taken from http://www.devtopics.com/best-programming-jokes/
     */
    String[] jokes = new String[] {
            "Two bytes meet.  The first byte asks, “Are you ill?”\nThe second byte replies, “No, just feeling a bit off.”",
            "Eight bytes walk into a bar.  The bartender asks, “Can I get you anything?”\n\n“Yeah,” reply the bytes.  “Make us a double.”",
            "Q. How did the programmer die in the shower?\nA. He read the shampoo bottle instructions: Lather. Rinse. Repeat.",
            "How many programmers does it take to change a light bulb?\nNone – It’s a hardware problem",
            "Why do programmers always mix up Halloween and Christmas?\nBecause Oct 31 equals Dec 25.",
            "There are only 10 kinds of people in this world: those who know binary and those who don’t.",
            "A programmer walks to the butcher shop and buys a kilo of meat.  An hour later he comes back upset that the butcher shortchanged him by 24 grams.",
            "Programming is 10% science, 20% ingenuity, and 70% getting the ingenuity to work with the science.",
            "Programming is like sex: One mistake and you have to support it for the rest of your life.",
            "All programmers are playwrights, and all computers are lousy actors."
    };

    public String getRandomJoke() {
        int randomIndex = random.nextInt(jokes.length);
        return jokes[randomIndex];
    }

}
