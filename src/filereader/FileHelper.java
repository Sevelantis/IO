package filereader;

import java.io.*;
import java.util.List;

public class FileHelper
{
    public static void readFile(String filename, List<String> data)
    {
        BufferedReader reeder;
        try
        {
            reeder = new BufferedReader(new FileReader(filename));
            String line = reeder.readLine();
            while(line != null)
            {
                data.add(line);
                line = reeder.readLine();
            }
            reeder.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }

    public static void writeFile(String filename, List<String> data)
    {
        BufferedWriter writer;
        try
        {
            writer = new BufferedWriter(new FileWriter(filename));
            for (String str : data)
            {
                writer.write(str);
                writer.newLine();
            }

            writer.close();
        } catch (IOException e)
        {
            e.printStackTrace();
        }
    }
}
