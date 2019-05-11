public class Sample {
    private byte[] _data;
    private int _size;

    public Sample(int size){
        this._size = size;
        this._data = new byte[size];
    }

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

    public String ToString(){
        String str = "sample: [";
        for (int i=0; i < _data.length-1; i++){
            str = str + _data[i] + ",";
        }
        return str + _data[_data.length-1] +  "]";
    }

}
