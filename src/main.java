import java.io.IOException;
import java.nio.ByteBuffer;

public class main {

    public static void main(String[] args) throws IOException{
         WaveFile w = new WaveFile();

         if (w.readin()){
             byte[] b1 = w.get_bytes(0,3);
             System.out.println(new String(b1)); // Prints RIFF
         }

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
