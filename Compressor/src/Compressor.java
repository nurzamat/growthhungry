import java.io.*;
import java.nio.file.Path;
import java.util.*;

public class Compressor {

    public static void compress(String pathToRead, String pathToWrite){
        BufferedReader br = null;
        BufferedWriter bw = null;
        File file = null;
        File fileToWrite = null;
        try{
            file = new File(pathToRead);
            fileToWrite = new File(pathToWrite);
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(fileToWrite));
            Map<String, String> map = new HashMap<>();
            List<String> lines = new ArrayList<>();
            String[] arr;
            int cnt = 0;
            String line = "";
            while ((line = br.readLine()) != null){
                StringBuilder sb = new StringBuilder();
                arr = line.split(" ");
                for (String word:arr) {
                    if(!map.containsKey(word)){
                        cnt++;
                        map.put(word,cnt+"");
                        if(cnt == 1)
                            sb.append(cnt);
                        else sb.append(" "+ cnt);
                    }
                    else{
                        sb.append(" " + map.get(word));
                    }
                }
                lines.add(sb.toString());
            }

            //save hashmap
            for (Map.Entry<String, String> entry :
                    map.entrySet()) {
                // put key and value separated by a colon
                bw.write(entry.getValue() + ":" + entry.getKey());
                // new line
                bw.newLine();
            }
            //save codes
            for (String l:lines) {
                bw.write(l);
                // new line
                bw.newLine();
            }

            bw.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                br.close();
                bw.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }


    public static void decompress(String pathToRead, String pathToWrite){
        BufferedReader br = null;
        BufferedWriter bw = null;
        File file = null;
        File fileToWrite = null;
        try{
            file = new File(pathToRead);
            fileToWrite = new File(pathToWrite);
            br = new BufferedReader(new FileReader(file));
            bw = new BufferedWriter(new FileWriter(fileToWrite));
            Map<String, String> map = new HashMap<>();
            String[] arr;
            String line = "";
            while ((line = br.readLine()) != null){
                //restore map
                if(line.contains(":")){
                    int index =  line.indexOf(":");
                    map.put(line.substring(0, index), line.substring(index+1));
                }
                else {
                    StringBuilder sb = new StringBuilder();
                    arr = line.split(" ");
                    for (String code:arr) {
                        sb.append(" " + map.get(code));
                    }

                    bw.write(sb.toString());
                    // new line
                    bw.newLine();
                }
            }

            bw.flush();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            try{
                br.close();
                bw.close();
            }
            catch (Exception e){
                e.printStackTrace();
            }
        }
    }

    //If we find a discrepancy, we return the byte position of the mismatch. Otherwise, the files are identical and the method returns -1L.
    public static long filesCompareByByte(Path path1, Path path2) throws IOException {
        try (BufferedInputStream fis1 = new BufferedInputStream(new FileInputStream(path1.toFile()));
             BufferedInputStream fis2 = new BufferedInputStream(new FileInputStream(path2.toFile()))) {

            int ch = 0;
            long pos = 1;
            while ((ch = fis1.read()) != -1) {
                if (ch != fis2.read()) {
                    return pos;
                }
                pos++;
            }
            if (fis2.read() == -1) {
                return -1;
            }
            else {
                return pos;
            }
        }
    }
}
