import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
         //insert Toolbox to use them in main
         WaveFileUtils wave_util = new WaveFileUtils();
         //Create instance of each Wave-File
         WaveFile R2_m1_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_music___techno___Scooter-HowMuchIsTheFish_16.wav");
         WaveFile Reference_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/music___techno___Scooter-HowMuchIsTheFish_mono_aligned_16.wav");
         WaveFile R2_m1_silence = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_sounds___silence___silence.wav");

         //Read in the binary expression of each Wave-File
         if (Reference_scooter.read() && R2_m1_scooter.read()){ //to check if teh read in works and especially check if the file exists
                    R2_m1_scooter.create_CSV_with_SampleData();
                    Reference_scooter.create_CSV_with_SampleData();

                //use the BINARY_OPERATION from the WaveFileUtils_Class
                    WaveFile scooter_R2_m1_MINUS_Reference = wave_util.binary_Operation(R2_m1_scooter,Reference_scooter, '-');

                //Export the new Wave-Files
                    scooter_R2_m1_MINUS_Reference.write();
                    scooter_R2_m1_MINUS_Reference.create_CSV_with_SampleData();

         }
    }

}
