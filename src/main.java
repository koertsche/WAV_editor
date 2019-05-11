import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
         WaveFile w = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile v = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m8_music___techno___Scooter-HowMuchIsTheFish.wav");

         if (w.read() && v.read()){
                v.read_Samples();
                System.out.println(v.get_samples()[1].ToString());
         }
         w.write("test");
    }

    private static WaveFile binary_Subtraction(WaveFile minuend, WaveFile subtrahend){
       //TODO
        return new WaveFile();
    }


    private static WaveFile add_fingerprint(WaveFile src, WaveFile fingerprint){
         //TODO
        return new WaveFile();
    }








}
