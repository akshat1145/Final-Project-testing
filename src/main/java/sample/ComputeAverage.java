package sample;

import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.InputStreamReader;
import java.util.HashMap;

public class ComputeAverage {

    public HashMap<String, int[]>  monthlyAvg(String month, String id, boolean isMonth) {

        HashMap<String, int[]> map = new HashMap<>();
        try {
            FileInputStream fstream = new FileInputStream("src/main/java/sample/data.csv");
            BufferedReader br = new BufferedReader(new InputStreamReader(fstream));

            String line = "";

            int tokenNumber = 1;
            if(!isMonth) tokenNumber = 3;

            int keyIndex = 3;
            if(!isMonth) keyIndex = 1;

            while ((line = br.readLine()) != null) {
                String[] arr = line.split(",");
                if(arr[tokenNumber].equals(month)) {
                    if(map.containsKey(arr[keyIndex])) {
                        int[] arr2 = map.get(arr[keyIndex]);
                        arr2[0] = arr2[0] + Integer.parseInt(arr[4]);
                        arr2[1] = arr2[1] + 1;
                        arr2[2] = Math.max(arr2[2], Integer.parseInt(arr[4]));
                        arr2[2] = Math.min(arr2[2], Integer.parseInt(arr[4]));
                    } else {
                        int[] arr2 = new int[4];
                        arr2[0] =  Integer.parseInt(arr[4]);
                        arr2[1] =  1;
                        arr2[2] = Integer.parseInt(arr[4]);
                        arr2[3] = Integer.parseInt(arr[4]);
                        map.put(arr[keyIndex], arr2);
                    }
                }
            }
            fstream.close();
        } catch (Exception e) {
            System.out.println(e);
        }
        return map;
    }

}
