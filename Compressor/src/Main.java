import java.io.*;
import java.nio.file.Files;
import java.nio.file.Path;
import java.util.*;

public class Main {
    public static void main(String[] args) {

        String inputPath = "/Users/nurzamat/Documents/input.txt";
        String compressPath = "/Users/nurzamat/Documents/compressed.sc";
        String readablePath = "/Users/nurzamat/Documents/readable.txt";

        Compressor.compress(inputPath, compressPath);
        Compressor.decompress(compressPath, readablePath);
        //compare 2 files
        try {
            Compressor.filesCompareByByte(Path.of(compressPath), Path.of(readablePath));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }
}