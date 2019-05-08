import java.io.File;
import java.io.IOException;
import java.util.Scanner;

public class WaveFile {
    private final String _WAV = ".wav";

    private File _file;
    private byte[] _binaryexpression;
    private String _latesterror = "No error.";

    public void WavFile(File file, byte[] binaryexpression){
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

    public void output_latesterror(){
        System.out.println(_latesterror);
    }

    public void set_binaryexpression(byte[] binaryexpression){
        this._binaryexpression = binaryexpression;
    }

    public boolean readin() throws IOException {
        System.out.println("Input path of WAVE-file:");
        Scanner scanner = new Scanner(System.in);
        this._file = new File(scanner.nextLine());
        if (_is_wav_file(this._file)){
            BinaryReader r = new BinaryReader();
            this._binaryexpression = r.readBinaryFile(_file.getAbsolutePath());
            return true;
        } else {
            return false;
        }
    }

    public byte[] get_bytes(int start_index, int end_index){
        byte[] newbytes = new byte[end_index-start_index+1];
        for (int i=start_index; i <= end_index; i++){
            newbytes[i-start_index] = this._binaryexpression[i];
        }
        return newbytes;
    }

    public String ToString(){
        return this._file.getAbsolutePath();
    }


    /*
     * Private Class decleration
     */

    private boolean _is_wav_file(File file){
        if (file.exists()){
            if (file.getName().endsWith(_WAV)){
                return true;
            }
            _latesterror = "Error: No WAV file!";
            return false;
        }
        _latesterror = "Error: No such File in directory. File not exists!";
        return false;
    }



}
