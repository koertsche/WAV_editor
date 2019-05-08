import java.io.IOException;
import java.nio.ByteBuffer;

public class main {

    public static void main(String[] args) throws IOException{
         WaveFile w = readin_WAVE();
         
         byte[] b1 = w.get_bytes(0,3);
         System.out.println(new String(b1)); // Prints RIFF

    }

    private static WaveFile readin_WAVE() throws IOException{
        WaveFile w = new WaveFile();
        BinaryReader r = new BinaryReader();

        if(w._readin_filepath()){
            w.set_binaryexpression(r.readBinaryFile(w.get_file().getAbsolutePath()));
        } else {
            w.output_latesterror();
            w = null;
        }

        return w;
    }

    private static WaveFile binary_Subtraction(WaveFile minuend, WaveFile subtrahend){
        /**
         * *** TODO ***
         **/
        return new WaveFile();
    }

    private static WaveFile add_fingerprint(WaveFile src, WaveFile fingerprint){
        /**
         * *** TODO ***
         **/
        return new WaveFile();
    }







}
