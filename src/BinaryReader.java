import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryReader {

    byte[] readBinaryFile(String fileName) throws IOException {
        Path path = Paths.get(fileName);
        return Files.readAllBytes(path);
    }


    private static void log(Object msg){
        System.out.println(String.valueOf(msg));
    }

}
