public class Sample {
    //Membervariablen
    private byte[] _data;
    private int _size;


    public Sample(int size, byte[] data){
        this._size = size;
        this._data = data;
    }

    public int get_size() {
        return _size;
    }

    public byte[] get_data() {
        return _data;
    }

    public void set_data(byte[] data){
        this._data = data;
    }

    public void set_size(int size) { this._size = size; }
}
