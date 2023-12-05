package ArrayOpener;

import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public class Opener {
    public static int[] openArchive(String path){
        List<Integer> list = new ArrayList<Integer>();
        try{
            ClassLoader classLoader = Opener.class.getClassLoader();
            InputStreamReader inputStreamReader = new InputStreamReader(classLoader.getResourceAsStream(path));
            BufferedReader bufferedReader = new BufferedReader(inputStreamReader);
            String line;
            
            while((line = bufferedReader.readLine()) != null){
                String[] numbers = line.split(", ");
                for(String number : numbers){
                    list.add(Integer.parseInt(number));
                }
            }
            bufferedReader.close();

        }   catch(IOException e){
            e.printStackTrace();
        }
        int[] array = new int[list.size()];
        for(int i = 0; i < list.size(); i++){
            array[i] = list.get(i);
        }
        return array;
    }
}