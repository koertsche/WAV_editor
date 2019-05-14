public class WaveFileUtils {

    public  WaveFile binary_Operation(WaveFile waveFile_1, WaveFile waveFile_2, char operation){
        //Get the WaveFile with the smaller size to terminate the loop for copy the samples
        WaveFile smaller_WaveFile = get_the_WaveFile_with_the_smaller_size(waveFile_1,waveFile_2);
        WaveFile bigger_WaveFile = get_the_WaveFile_with_the_bigger_size(waveFile_1,waveFile_2);
        //Generate the first byte for the new WaveFile (0-44), just copy from one file (first)
        byte[] header_of_WaveFile = smaller_WaveFile.get_header();
        //Import ByteUtils to use them in this scope
        ByteUtils util = new ByteUtils();
        //Initialise an array of Samples to save the result of the subtraction
        Sample[] result = new Sample[smaller_WaveFile.get_samples().length];

        for (int i=0; i < smaller_WaveFile.get_samples().length-1; i++){
            switch (operation){
                case '-' : {
                    byte[] difference_data_in_byte = util.int_32_le_to_bytes(util.bytes_to_int_32_le(smaller_WaveFile.get_samples()[i].get_bytes_of_channel_i(1, (int) smaller_WaveFile.get_NumChannels())) - util.bytes_to_int_32_le(bigger_WaveFile.get_samples()[i].get_bytes_of_channel_i(1, (int) bigger_WaveFile.get_NumChannels())));
                    result[i] = new Sample(difference_data_in_byte.length,difference_data_in_byte,1);
                    break;
                }
                case '+' : {
                    byte[] difference_data_in_byte = util.int_32_le_to_bytes(util.bytes_to_int_32_le(smaller_WaveFile.get_samples()[i].get_bytes_of_channel_i(1, (int) smaller_WaveFile.get_NumChannels())) + util.bytes_to_int_32_le(bigger_WaveFile.get_samples()[i].get_bytes_of_channel_i(1, (int) bigger_WaveFile.get_NumChannels())));
                    result[i] = new Sample(difference_data_in_byte.length,difference_data_in_byte,1);
                    break;
                }
                default: {System.out.println("Keine gültige Operation: " + operation); return null;}
            }
        }

        return new WaveFile(header_of_WaveFile, result, (int) smaller_WaveFile.get_Framesize());
        //Gleicher Startpunkt des Sounds? Korrigieren?
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


}
