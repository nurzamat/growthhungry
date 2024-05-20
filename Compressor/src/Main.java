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
    }
}