package com.github.hemanthsridhar;

import java.io.BufferedReader;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class CSVLibrary {

    public Iterator<Object[]> parseCSVData(String fileName) throws IOException
    {
        BufferedReader input;
        File file = new File(fileName);
        input = new BufferedReader(new FileReader(file));
        String line = null;
        ArrayList<Object[]> data = new ArrayList<>();
        input.readLine();
        while ((line = input.readLine()) != null)
        {
            String in = line.trim();
            String[] temp = in.split(",");
            List<Object> arrray = new ArrayList<>();
            for(String s : temp)
            {
                arrray.add(s);
            }
            data.add(arrray.toArray());
        }
        input.close();
        return data.iterator();
    }
}
