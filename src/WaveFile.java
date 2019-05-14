import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WaveFile {
    private static final String _WAV = ".wav";
    private static final int OFFSET_DATA = 44;

    private File _file;
    private Sample[] _samples;
    private byte[] _binaryexpression;

    private ByteUtils _ByteUtil = new ByteUtils();

    public WaveFile(){
        _file = null;
        _binaryexpression = null;
    }

    public WaveFile(String path){
        _file = new File(path);
        _binaryexpression = null;
    }

    public WaveFile(File file, byte[] binaryexpression){
        _file = file;
        _binaryexpression = binaryexpression;
    }

    public WaveFile(byte[] header, Sample[] samples, int framesize){
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

    public WaveFile(File file, Sample[] samples){
        _file = file;
        _samples = samples;

        int framesize = (int) get_Framesize();

        for (int i=OFFSET_DATA; i < get_binaryexpression().length-1; i=i+framesize){
            for (int j=i; j < i+framesize; j++){
                _binaryexpression[j] = _samples[i].get_data()[j];
            }
        }
    }

    public byte[] get_header(){
        return _ByteUtil.get_bytes(this._binaryexpression,0,43);
    }

    public void set_header(byte[] _header){
        if (_header.length==44 && _binaryexpression!=null) {
            for (int i = 0; i < 44; i++) {
                _binaryexpression[i] = _header[i];
            }
        } else {
            System.out.println("Kein gültiger Header!");
        }
    }

    public int get_DataOffset() {
        return OFFSET_DATA;
    }

    public File get_file() {
        return this._file;
    }

    public Sample[] get_samples() {
        return _samples;
    }

    public byte[] get_binaryexpression() {
        return this._binaryexpression;
    }

    public void set_file(File file){
        this._file = file;
    }

    public void set_samples(Sample[] _samples) {
        this._samples = _samples;
    }

    public void set_binaryexpression(byte[] binaryexpression){
        this._binaryexpression = binaryexpression;
    }

    public boolean read() throws IOException {
        //If there is no file linked
        if (_file == null) {
            System.out.println("Input path of WAVE-file:");
            Scanner scanner = new Scanner(System.in);
            this._file = new File(scanner.nextLine());
        }
        //If there is a file linked to this object
        if (_file.exists()){
            BinaryReader r = new BinaryReader();
            this._binaryexpression = r.readBinaryFile(_file.getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public boolean write(String filename) throws IOException{
        BinaryReader r = new BinaryReader();
        return r.writeBinaryFile(this._binaryexpression,filename + _WAV);
    }

    public boolean read_Samples(){
        int bytes_per_sample = (int) get_Framesize();
        int channels = (int) get_NumChannels();
        ByteUtils util = new ByteUtils();
        int count = 0;

        if (binaryexpression_exists()) {

            Sample[] samples = new Sample[((get_binaryexpression().length - OFFSET_DATA) / bytes_per_sample)];

            for (int i = OFFSET_DATA; i < get_binaryexpression().length; i = i + bytes_per_sample) {
                int index = i - count - OFFSET_DATA;
                //Sind in einem Smaple immer beide channels hintereinander abgespeichert oder erst ale Samples_left und danach alle Samples_right
                samples[index] = new Sample(bytes_per_sample, _ByteUtil.get_bytes(this._binaryexpression, i, i + (bytes_per_sample - 1)), (i % 2)+1);
                count = count + (bytes_per_sample-1);
            }
            _samples = samples;
            return true;
        } else {
            return false;
        }
    }

    //***** WaveFile Operations

    public String get_chunkID(){
        if (binaryexpression_exists()){
            return new String(_ByteUtil.get_bytes(this._binaryexpression,0,3));
        } else {return null;}
    }

    public long get_Chuncksize(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,4,7))+8;
        } else {return -1;}
    }

    public String get_Format(){
        if (binaryexpression_exists()){
            return new String(_ByteUtil.get_bytes(this._binaryexpression,8,11));
        } else {return null;}
    }

    public String get_FormatTagID(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_HexCode(_ByteUtil.get_bytes(this._binaryexpression,20,21));
        } else {return null;}
    }

    public long get_NumChannels(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,22,23));
        } else {return -1;}
    }

    public long get_SampleRate(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,24,27));
        } else {return -1;}
    }

    public long get_ByteRate(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,28,31));
        } else {return -1;}
    }

    public long get_Framesize(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,32,33));
        } else {return -1;}
    }

    public long get_BitsPerSample(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_int_32_le(_ByteUtil.get_bytes(this._binaryexpression,34,35));
        } else {return -1;}
    }


    private boolean binaryexpression_exists(){
        if (_binaryexpression == null){ System.out.println("Error: Keine Datei eingelesen!"); return false;} else {return true;}
    }







}
