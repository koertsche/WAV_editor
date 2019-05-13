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

    public WaveFile(File file, Sample[] samples, byte[] binaryexpression){
        _file = file;
        _samples = samples;
        _binaryexpression = binaryexpression;
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

    public void read_Samples(){
        int counter = 0;
        int framesize = (int) get_Framesize();
        int count_channels = (int) get_NumChannels();

        if (binaryexpression_exists()) {
            Sample[] samples = new Sample[((get_binaryexpression().length - OFFSET_DATA) / framesize)];
            for (int i = OFFSET_DATA; i < get_binaryexpression().length; i = i + framesize) {
                samples[i - counter  - OFFSET_DATA] = new Sample(framesize, ((counter+1) % count_channels)+1 , get_bytes(i, i + framesize - 1));
                counter++;
            }
            _samples = samples;
        }
    }

    //***** WaveFile Operations

    public String get_chunkID(){
        if (binaryexpression_exists()){
            return new String(get_bytes(0,3));
        } else {return null;}
    }

    public long get_Chuncksize(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(4,7))+8;
        } else {return -1;}
    }

    public String get_Format(){
        if (binaryexpression_exists()){
            return new String(get_bytes(8,11));
        } else {return null;}
    }

    public String get_FormatTagID(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_HexCode(get_bytes(20,21));
        } else {return null;}
    }

    public long get_NumChannels(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(22,23));
        } else {return -1;}
    }

    public long get_SampleRate(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(24,27));
        } else {return -1;}
    }

    public long get_ByteRate(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(28,31));
        } else {return -1;}
    }

    public long get_Framesize(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(32,33));
        } else {return -1;}
    }

    public long get_BitsPerSample(){
        if (binaryexpression_exists()){
            return _ByteUtil.bytes_to_uint_32_le(get_bytes(34,35));
        } else {return -1;}
    }


    //***** Private Declarations

    private byte[] get_bytes(int start_index, int end_index){
        byte[] newbytes = new byte[end_index-start_index+1];
        for (int i=start_index; i <= end_index; i++){
            newbytes[i-start_index] = this._binaryexpression[i];
        }
        return newbytes;
    }

    private boolean binaryexpression_exists(){
        if (_binaryexpression == null){ System.out.println("Error: Keine Datei eingelesen!"); return false;} else {return true;}
    }







}
