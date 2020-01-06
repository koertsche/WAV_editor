import java.io.IOException;

public class main {

    public static void main(String[] args) throws IOException{
         //insert Toolbox to use them in main
         WaveFileUtils wave_util = new WaveFileUtils();
         //Wave-File Array
         WaveFile[] wave_files = new WaveFile[args.length];
         //Add Wave-Files from Arguments
         for(int i=0; i < args.length; i++){
                wave_files[i] = new WaveFile(args[i]);
                wave_files[i].read();
         }

         if (wave_files.length == 2){
             WaveFile minus = wave_util.WaveFile_Operation(wave_files[0], wave_files[1], '+');
             minus.write();
         } else {
             System.out.println("Wrong count of arguments. Except 2 but found " + args.length + ".");
         }
    }

}
