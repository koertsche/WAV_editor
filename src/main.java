import java.io.IOException;

public class main {
    static final int OFFSET_FMT = 16;
    static final int OFFSET_DATA = 44;

    public static void main(String[] args) throws IOException{
         WaveFile w = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile v = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m8_music___techno___Scooter-HowMuchIsTheFish.wav");

         if (w.read() && v.read()){
                 w = binary_Subtraction(w,v);
         }

         w.write("test");


    }

    private static WaveFile binary_Subtraction(WaveFile minuend, WaveFile subtrahend){
        byte[] differenz = minuend.get_binaryexpression();

        if(minuend.get_binaryexpression().length == subtrahend.get_binaryexpression().length){
            for (int i=OFFSET_DATA; i < minuend.get_binaryexpression().length-1; i++) {
                int  _i = (int) minuend.get_binaryexpression()[i] - (int) subtrahend.get_binaryexpression()[i];
                differenz[i] = (byte) _i ;
            }
        } else {
            System.out.println("Error:Unterschiedlich langer Datenblock!");
        }
        return new WaveFile(null, differenz);
    }


    private static WaveFile add_fingerprint(WaveFile src, WaveFile fingerprint){
         //TODO
        return new WaveFile();
    }








}
