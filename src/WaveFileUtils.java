import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class WaveFileUtils {

    //Import ByteUtils to use them in this scope
    ByteUtils util = new ByteUtils();

    public  WaveFile binary_Operation(WaveFile waveFile_1, WaveFile waveFile_2, char operation) throws IOException {
        //Get the WaveFile with the smaller size to terminate the loop for copy the samples
        WaveFile smaller_WaveFile = get_the_WaveFile_with_the_smaller_size(waveFile_1,waveFile_2);
        WaveFile bigger_WaveFile = get_the_WaveFile_with_the_bigger_size(waveFile_1,waveFile_2);
        //Generate the first byte for the new WaveFile (0-44), just copy from one file (first)
        byte[] header_of_WaveFile = smaller_WaveFile.get_header();
        //Initialise an array of Samples to save the result of the subtraction
        Sample[] result = new Sample[smaller_WaveFile.get_samples().length];
        //define -/+
        int operator = operator_(operation);


        for (int i=0; i < smaller_WaveFile.get_samples().length; i++){
            int sample1_i = util.bytes_to_int_32_le(smaller_WaveFile.get_samples()[i].get_data());
            int sample2_i = util.bytes_to_int_32_le(bigger_WaveFile.get_samples()[i].get_data());
            byte[] difference_data_in_byte;

            if (sample1_i > sample2_i){
                difference_data_in_byte = util.int_32_le_to_bytes( sample1_i + operator * sample2_i );
            } else {
                difference_data_in_byte = util.int_32_le_to_bytes( sample2_i + operator * sample1_i );
            }

            result[i] = new Sample(difference_data_in_byte.length,difference_data_in_byte);
        }

        return new WaveFile(waveFile_1.get_file().getName().substring(0,5) + "_" + waveFile_2.get_file().getName().substring(0,5), header_of_WaveFile, result, (int) smaller_WaveFile.get_Framesize());
    }





    //*********Private Declarations*********

    private WaveFile get_the_WaveFile_with_the_smaller_size(WaveFile w1, WaveFile w2){
        if (w1.get_samples().length <= w2.get_samples().length){
            return w1;
        } else {
            return w2;}
    }

    private WaveFile get_the_WaveFile_with_the_bigger_size(WaveFile w1, WaveFile w2){
        if (w1.get_samples().length > w2.get_samples().length){
            return w1;
        } else {
            return w2;}
    }

    private int operator_ (char operation) {
        int operator = 0;
        switch (operation) {
            case '-': {
                operator = -1;
                break;
            }
            case '+': {
                operator = 1;
                break;
            }
            default: {
                System.out.println("Keine gültige Operation: " + operation);
                return 0;
            }
        }
        return operator;
    }

}
