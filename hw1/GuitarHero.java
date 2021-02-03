/** A client that uses the synthesizer package to replicate a plucked guitar string sound */
import synthesizer.GuitarString;

public class GuitarHero {

    public static void main(String[] args) {
        GuitarString[] pianoString = new GuitarString[37];
        String keyboard = "q2we4r5ty7u8i9op-[=zxdcfvgbnjmk,.;/' ";

        while (true) {

            /* check if the user has typed a key; if so, process it */
            if (StdDraw.hasNextKeyTyped()) {
                char key = StdDraw.nextKeyTyped();
                int keyIndex = keyboard.indexOf(key);

                if (keyIndex >= 0 && keyIndex < 37) {
                    pianoString[keyIndex] = new GuitarString(440 * Math.pow(2.0, (double) (keyIndex - 24) / 12));
                    pianoString[keyIndex].pluck();
                }
            }

            /* compute the superposition of samples */
            double sample = 0.0;
            for (int i = 0; i < pianoString.length; i += 1) {
                if (pianoString[i] != null) {
                    sample += pianoString[i].sample();
                }
            }


            /* play the sample on standard audio */
            StdAudio.play(sample);

            /* advance the simulation of each guitar string by one step */
            for (int i = 0; i < pianoString.length; i += 1) {
                if (pianoString[i] != null) {
                    pianoString[i].tic();
                }
            }
        }
    }
}
