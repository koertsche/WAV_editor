public class Sample {
    private int _channel;
    private byte[] _data;
    private int _size;

    public Sample(int size, int is_channel){
        this._size = size;
        this._channel = is_channel;
        this._data = new byte[size];
    }

    public Sample(int size, int is_channel, byte[] data){
        this._size = size;
        this._channel = is_channel;
        this._data = data;
    }

    public int get_size() {
        return _size;
    }

    public int get_channel() {
        return _channel;
    }

    public byte[] get_data() {
        return _data;
    }

}
