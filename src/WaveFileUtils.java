public class WaveFileUtils {

    public  WaveFile binary_Operation(WaveFile waveFile_1, WaveFile waveFile_2, char operation){
        //Generate the first byte for the new WaveFile (0-44), just copy from one file (first)
        byte[] header_of_WaveFile = waveFile_1.get_header(); //hier muss man vielleicht mehr machen
        //Import ByteUtils to use them in this scope
        ByteUtils util = new ByteUtils();
        //Initialise an array of Samples to save the result of the subtraction
        Sample[] result = new Sample[waveFile_1.get_samples().length]; //TODO -> I just take the length of the first File, but this do not work for every case -> need to manage that

        /*
         * TODO not both WaveFiles include the same count of Smaples : need to correct that - if not: IndexOutOfBounds
         * TODO when we have a WaveFile contains just one channel (mono) and the other has two or more channels (stereo, etc.) -> for each sample u can get the bytes of each channel.
         */


        for (int i=0; i < waveFile_1.get_samples().length-1; i++){ //TODO -> because of the array size I defined for the sample array, I use this length do go threw BOTH sample arrays
            switch (operation){
                case '-' : {
                    byte[] difference_data_in_byte = util.int_32_le_to_bytes(util.bytes_to_int_32_le(waveFile_1.get_samples()[i].get_bytes_of_channel_i(1, (int) waveFile_1.get_NumChannels())) - util.bytes_to_int_32_le(waveFile_2.get_samples()[i].get_bytes_of_channel_i(1, (int) waveFile_1.get_NumChannels())));
                    result[i] = new Sample(difference_data_in_byte.length,difference_data_in_byte,1);
                    break;
                }
                case '+' : {
                    byte[] difference_data_in_byte = util.int_32_le_to_bytes(util.bytes_to_int_32_le(waveFile_1.get_samples()[i].get_bytes_of_channel_i(1, (int) waveFile_1.get_NumChannels())) + util.bytes_to_int_32_le(waveFile_2.get_samples()[i].get_bytes_of_channel_i(1, (int) waveFile_1.get_NumChannels())));
                    result[i] = new Sample(difference_data_in_byte.length,difference_data_in_byte,1);
                    break;
                }
                default: {System.out.println("Keine gültige Operation: " + operation); return null;}
            }
        }

        return new WaveFile(header_of_WaveFile, result, (int) waveFile_2.get_Framesize());

        //Was passiert wenn ich Pro sample bei der einen Datei zwei channels habe und bei der anderen nur einen, wie subrahiere ich?
        //Gleicher Startpunkt des Sounds? Korrigieren?
    }

    //*********Private Declarations*********




}
