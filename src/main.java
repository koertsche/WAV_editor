import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
         //insert Toolbox to use them in main
         WaveFileUtils wave_util = new WaveFileUtils();
         //Create instance of each Wave-File
         WaveFile R2_m1_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m1_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile R2_m2_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/R2_m2_music___techno___Scooter-HowMuchIsTheFish.wav");
         WaveFile Reference_scooter = new WaveFile("/Users/nkoertge/Desktop/AAFE_v-3.0.2-deploy/music___techno___Scooter-HowMuchIsTheFish_mono.wav");
         WaveFile R2_m1_sound_silenc = new WaveFile("/Users/nkoertge/Downloads/R2_m1_sounds___silence___silence.wav");
         WaveFile R2_m1_U2 = new WaveFile("/Users/nkoertge/Downloads/R2_m1_music___pop___U2-BeautifulDay.wav");
         WaveFile R2_m1_U2_kris = new WaveFile("/Users/nkoertge/Downloads/R2_m1_music___pop___U2-BeautifulDay-_manipulated.wav");

         /*
         //Read in the binary expression of each Wave-File
         if (R2_m1_scooter.read() && R2_m2_scooter.read() && Reference_scooter.read()){ //to check if teh read in works and especially check if the file exists
                //use the BINARY_OPERATION from the WaveFileUtils_Class
                WaveFile scooter_R2_m1_MINUS_Reference = wave_util.binary_Operation(R2_m1_scooter,Reference_scooter, '-');
                WaveFile scooter_R2_m2_MINUS_R2_m1 = wave_util.binary_Operation(R2_m2_scooter, R2_m1_scooter, '-');
                WaveFile scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference = wave_util.binary_Operation(Reference_scooter, scooter_R2_m1_MINUS_Reference, '+');
                //Export the new Wave-Files
                scooter_R2_m1_MINUS_Reference.write("scooter_R2_m1_MINUS_Reference");
                scooter_R2_m1_MINUS_Reference.create_CSV_with_SampleData();
                scooter_R2_m1_MINUS_Reference.create_patternfile();
                scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference.write("scooter_Reference_PLUS_scooter_R2_m1_MINUS_Reference");
                scooter_R2_m2_MINUS_R2_m1.write("scooter_R2_m2_MINUS_R2_m1");
         }*/

         if (R2_m1_sound_silenc.read() && R2_m1_U2.read()){
             WaveFile U2_minus_Silence = wave_util.binary_Operation(R2_m1_U2, R2_m1_sound_silenc,'-');
             U2_minus_Silence.create_CSV_with_SampleData();
             U2_minus_Silence.write("U2_minus_Silence");
         }
    }








}
