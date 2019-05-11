import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WaveFile {
    private final String _WAV = ".wav";

    private File _file;
    private byte[] _binaryexpression;

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

    public File get_file() {
        return this._file;
    }

    public byte[] get_binaryexpression() {
        return this._binaryexpression;
    }

    public void set_file(File file){
        this._file = file;
    }

    public void set_binaryexpression(byte[] binaryexpression){
        this._binaryexpression = binaryexpression;
    }

    public boolean read() throws IOException {
        /** CONSOLE oder UI CODE**/
        if (_file == null) {
            System.out.println("Input path of WAVE-file:");
            Scanner scanner = new Scanner(System.in);
            this._file = new File(scanner.nextLine());
        }

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

    public byte[] get_bytes(int start_index, int end_index){
        byte[] newbytes = new byte[end_index-start_index+1];
        for (int i=start_index; i <= end_index; i++){
            newbytes[i-start_index] = this._binaryexpression[i];
        }
        return newbytes;
    }





}
