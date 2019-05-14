import java.nio.ByteBuffer;

public class ByteUtils {
    //long in Java represents uint_32 from C
    public int bytes_to_int_32_le(byte[] b){
        String hexcode = bytes_to_HexCode(reverse_byte(b));
        return HexCodeIntArray_to_int(String_to_int_array_with_HexCode(hexcode,b.length));
    }

    public byte[] int_32_le_to_bytes(int i){
        ByteBuffer buffer = ByteBuffer.allocate(Integer.BYTES);
        buffer.putInt(i);
        return buffer.array();
    }

    public String bytes_to_HexCode(byte[] b){
        String str = "";
        for (int i=b.length-1; i >= 0;i--) {
            String temp = Integer.toHexString(b[i] & 0xFF);
            if (temp.equals("0")){
                str = "00" +str;
            } else {
                if (temp.length()==1){
                    str = "0" + temp + str;
                } else {
                    str = temp + str;
                }
            }

        }
        return str;
    }

    public byte[] get_bytes(byte[] b, int start_index, int end_index){
        byte[] newbytes = new byte[end_index-start_index+1];
        for (int i=start_index; i <= end_index; i++){
            newbytes[i-start_index] = b[i];
        }
        return newbytes;
    }

    public byte[] reverse_byte(byte b[])
    {
        for (int left = 0, right = b.length - 1; left < right; left++, right--) {
            // swap the values at the left and right indices
            byte temp = b[left];
            b[left]  = b[right];
            b[right] = temp;
        }

        return b;
    }

    //***************Private******************

    private int[] String_to_int_array_with_HexCode(String str, int length){
        int[] hex = new int[2*length];
        for (int i=0; i< length*2; i++){
            hex[i] = Integer.parseInt(hex_letters_to_StringNum(str.charAt(i)));
        }
        return hex;
    }

    private int HexCodeIntArray_to_int(int[] hexcode){
        int val = 0;
        for (int i=hexcode.length-1; i>=0; i--){
            val = val + (int) (hexcode[i] * Math.pow(16,Math.abs(hexcode.length-1-i)));
        }
        return val;
    }

    private String hex_letters_to_StringNum(char c){
        switch (c){
            case 'a' : return "10";
            case 'b' : return "11";
            case 'c' : return "12";
            case 'd' : return "13";
            case 'e' : return "14";
            case 'f' : return "15";
        }
        return String.valueOf(c);
    }

}
