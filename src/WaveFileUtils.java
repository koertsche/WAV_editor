import java.io.IOException;

class WaveFileUtils {

    //Import ByteUtils to use them in this scope
    private ByteUtils util = new ByteUtils();
    private VisualUtil visualUtil = new VisualUtil();

    WaveFile WaveFile_Operation(WaveFile waveFile_1, WaveFile waveFile_2, char operation) throws IOException {
        //Get the WaveFile with the smaller size to terminate the loop for copy the samples
        WaveFile smaller_WaveFile = get_the_WaveFile_with_the_smaller_size(waveFile_1, waveFile_2);
        WaveFile bigger_WaveFile = get_the_WaveFile_with_the_bigger_size(waveFile_1, waveFile_2);
        //Generate the first byte for the new WaveFile (0-44), just copy from one file (first)
        byte[] header_of_WaveFile = smaller_WaveFile.get_header();
        //Initialise an array of Samples to save the result of the subtraction
        Sample[] result = new Sample[smaller_WaveFile.get_samples().length];
        //define -/+
        int operator = operator_(operation);
        //Console output
        System.out.println("Do binary Operation:" + smaller_WaveFile.get_file().getName() + " " + operation + " " + bigger_WaveFile.get_file().getName());


        for (int i = 0; i < smaller_WaveFile.get_samples().length; i++){
            int sample1_i = util.bytes_to_int_32_le(smaller_WaveFile.get_samples()[i].get_data());
            int sample2_i = util.bytes_to_int_32_le(bigger_WaveFile.get_samples()[i].get_data());
            byte[] difference_data_in_byte;

            /*
             *TODO edit samples to get the Fingerprint!
             */

            difference_data_in_byte = util.int_32_le_to_bytes((sample1_i + operator * sample2_i));
            result[i] = new Sample(difference_data_in_byte.length, difference_data_in_byte);

            //Progressbar in Console
            visualUtil.progressPercentage(i, smaller_WaveFile.get_samples().length-1);
        }

        System.out.println("\nFinished Operation.");
        return new WaveFile(waveFile_1.get_file().getName().substring(0,20) + "_| " + operation +  " |_" + waveFile_2.get_file().getName().substring(0,20), header_of_WaveFile, result, (int) smaller_WaveFile.get_framesize());
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

    private int distance_between_samples(int sample_1i, int sample_2i){
        if (sample_1i > sample_2i){
            return Math.abs(sample_1i - sample_2i);
        } else {
            return Math.abs(sample_2i -sample_1i);
        }
    }

}
