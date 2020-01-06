import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

class WaveFile {
    //Konstanten der Klasse
    private static final String _WAV = ".wav";
    private static final int OFFSET_DATA = 44;

    //Membervariablen
    private File _file;
    private Sample[] _samples;
    private byte[] _binaryexpression;

    //Include Utils
    private ByteUtils util = new ByteUtils();
    private BinaryWriter binaryWriter = new BinaryWriter();
    private BinaryReader binaryReader = new BinaryReader();


    private WaveFile(){
        _file = null;
        _binaryexpression = null;
    }

    WaveFile(String path){
        _file = new File(path);
        _binaryexpression = null;
    }

    WaveFile(String filename, byte[] header, Sample[] samples, int framesize){
        _file = new File(filename);
        _samples = samples;
        _binaryexpression = new byte[(_samples.length * framesize)+44];
        set_header(header);

        for (int i=OFFSET_DATA; i < get_binaryexpression().length/framesize; i++){
            for (int j=framesize-1; j >= 0; j--){
                if (_samples[i-OFFSET_DATA]==null){System.out.println(i-OFFSET_DATA);}
                _binaryexpression[2*(i)-j] = _samples[i-OFFSET_DATA].get_data()[j];
            }
        }
    }



    byte[] get_header(){
        return util.get_bytes(this._binaryexpression,0,43);
    }

    File get_file() {
        return this._file;
    }

    Sample[] get_samples() {
        return _samples;
    }

    byte[] get_binaryexpression() {
        return this._binaryexpression;
    }

    public String get_chunkID(){
        if (binaryexpression_exists()){
            return new String(util.get_bytes(this._binaryexpression,0,3));
        } else {return null;}
    }

    public long get_chunckSize(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,4,7))+8;
        } else {return -1;}
    }

    public String get_format(){
        if (binaryexpression_exists()){
            return new String(util.get_bytes(this._binaryexpression,8,11));
        } else {return null;}
    }

    public String get_formatTagID(){
        if (binaryexpression_exists()){
            return util.bytes_to_HexCode(util.get_bytes(this._binaryexpression,20,21));
        } else {return null;}
    }

    public long get_Channels(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,22,23));
        } else {return -1;}
    }

    public long get_sampleRate(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,24,27));
        } else {return -1;}
    }

    public long get_byteRate(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,28,31));
        } else {return -1;}
    }

    //Bytes per Sample
    public long get_framesize(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,32,33));
        } else {return -1;}
    }

    public long get_bitsPerSample(){
        if (binaryexpression_exists()){
            return util.bytes_to_int_32_le(util.get_bytes(this._binaryexpression,34,35));
        } else {return -1;}
    }



    void set_header(byte[] header){
        if (header.length==44 && _binaryexpression!=null) {
            for (int i = 0; i < 44; i++) {
                _binaryexpression[i] = header[i];
            }
        } else {
            System.out.println("Kein gültiger Header!");
        }
    }

    public void set_file(File file){
        this._file = file;
    }

    public void set_samples(Sample[] samples) {
        this._samples = samples;
    }

    public void set_binaryexpression(byte[] binaryexpression){
        this._binaryexpression = binaryexpression;
    }



    public boolean read() throws IOException {
        //If there is a file linked to this object
        if (_file.exists()){
            System.out.println("Found " + _file.getName());
            this._binaryexpression = binaryReader.readBinaryFile(_file.getAbsolutePath());
            return read_Samples();
        } else {
            return false;
        }
    }

    public boolean write() throws IOException{
        System.out.println("Write " + _file.getName() + _WAV);
        return binaryWriter.writeBinaryFile(this._binaryexpression,_file.getName() + _WAV);
    }


    //*************Private Declerations****************************

    private boolean binaryexpression_exists(){
        if (_binaryexpression == null){ System.out.println("Error: Keine Datei eingelesen!"); return false;} else {return true;}
    }

    private boolean read_Samples(){
        System.out.println("Reading Sample Date...");

        int bytes_per_sample = (int) get_framesize();
        int count = 0;

        if (binaryexpression_exists()) {

            Sample[] samples = new Sample[((get_binaryexpression().length - OFFSET_DATA) / bytes_per_sample)];

            for (int i = OFFSET_DATA; i < get_binaryexpression().length; i = i + bytes_per_sample) {
                int index = i - count - OFFSET_DATA;
                samples[index] = new Sample(bytes_per_sample, this.util.get_bytes(this._binaryexpression, i, i + (bytes_per_sample - 1)));
                count = count + (bytes_per_sample-1);
            }
            _samples = samples;
            System.out.println("Finished reading.");
            return true;
        } else {
            System.out.println("Finished with Error: No binary Expression from that file.");
            return false;
        }
    }
}
