import java.io.*;
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
}
