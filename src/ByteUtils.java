public class ByteUtils {
    //long in Java represents uint_32 from C
    public long bytes_to_uint_32_le(byte[] b){
        String hexcode = bytes_to_HexCode(reverse_byte(b));
        return HexCodeIntArray_to_long(String_to_int_array_with_HexCode(hexcode,b.length));
    }

    public String bytes_to_HexCode(byte[] b){
        String str = "";
        for (int i=b.length-1; i >= 0;i--) {
            String temp = Integer.toHexString(b[i] & 0xFF);
            if (temp.equals("0")){
                str = "00" +str;
            } else {
                if (temp.length()==1){
                    str = "0" + temp +str;
                } else {
                    str = temp +str;
                }
            }

        }
        return str;
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

    private long HexCodeIntArray_to_long(int[] hexcode){
        long val = 0;
        for (int i=hexcode.length-1; i>=0; i--){
            val = val + (long) (hexcode[i] * Math.pow(16,Math.abs(hexcode.length-1-i)));
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
