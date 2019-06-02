

public class ByteUtils {


    public int bytes_to_int_32_le(byte[] b){
        String hexcode = bytes_to_HexCode(b);
        return HexCodeIntArray_to_int(String_to_int_array_with_HexCode(hexcode,b.length));
    }

    public byte[] int_32_le_to_bytes(int value){
        return hexStringToByteArray(int_to_HexCode(value));
    }

    public String bytes_to_HexCode(byte[] b){
        String str = "";
        for (int i=b.length-1; i >= 0;i--) {
            String temp = Integer.toHexString(b[i] & 0xFF);
            if (temp.length()==1){
                str = "0" + temp + str;
            } else {
                str = temp + str;
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

        int[] final_hex = new int[hex.length];

        for (int i=0; i < hex.length; i=i+2 ){
            final_hex[hex.length-i-1] = hex[i+1];
            final_hex[hex.length-i-2] = hex[i];
        }

        return final_hex;
    }

    private int HexCodeIntArray_to_int(int[] hexcode){
        int val = 0;
        for (int i=0; i < hexcode.length; i++){
            val = val + (int) (hexcode[i] * Math.pow(16,Math.abs(hexcode.length-1-i)));
        }
        return val;
    }

    public String int_to_HexCode(int d){
        String digits = "0123456789ABCDEF";
        if (d <= 0) return "0000";
        int base = 16;   // flexible to change in any base under 16
        String hex = "";
        while (d > 0) {
            int digit = d % base;              // rightmost digit
            hex = digits.charAt(digit) + hex;  // string concatenation
            d = d / base;
        }

        while (hex.length() < 4){
            hex = "0" + hex;
        }
        return hex;
    }

    public static byte[] hexStringToByteArray(String s) {
        byte[] b = new byte[s.length() / 2];

        for (int i = 0; i < b.length; i++) {
            int index = i * 2;
            int v = Integer.parseInt(s.substring(index, index + 2), 16);
            b[i] = (byte) v;
        }

        byte[] convert = new byte[b.length];

        for (int i=0; i < convert.length; i=i+2 ){
            convert[b.length-i-1] = b[i];
            convert[b.length-i-2] = b[i+1];
        }

        return convert;
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