import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

public class BinaryWriter {

    boolean writeBinaryFile(byte[] bytes, String fileName) throws IOException {
        Path path = Paths.get(fileName);
        Files.write(path, bytes); //creates, overwrites
        return Files.exists(path);
    }

}
