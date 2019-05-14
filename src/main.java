import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
         //insert Toolbox to use them in main
         WaveFileUtils wave_util = new WaveFileUtils();
         //Create instance of each Wave-File
         WaveFile R2_m1_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile R2_m2_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m2_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile Reference_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/music___techno___Scooter-HowMuchIsTheFish_mono.wav");

         //Read in the binary expression of each Wave-File
         if (R2_m1_scooter.read() && R2_m2_scooter.read() && Reference_scooter.read()){ //to check if teh read in works and especially check if the file exists
                //convert the data part from the binary expression in Samples with all information
                R2_m1_scooter.read_Samples();
                R2_m2_scooter.read_Samples();
                Reference_scooter.read_Samples();
                //use the BINARY_OPERATION from the WaveFileUtils_Class
                WaveFile scooter_R2_m1_MINUS_Reference = wave_util.binary_Operation(R2_m1_scooter,Reference_scooter, '-');
                WaveFile scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference = wave_util.binary_Operation(Reference_scooter, scooter_R2_m1_MINUS_Reference, '+');
                //Export the new Wave-Files
                scooter_R2_m1_MINUS_Reference.write("scooter_R2_m1_MINUS_Reference");
                scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference.write("scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference");

         }
    }








}
