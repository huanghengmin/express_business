import java.io.*;

/**
 * Created by Administrator on 16-2-18.
 */
public class CreateSQLDemo {
    public static void main(String args[])throws Exception{
        StringBuilder sb = new StringBuilder();
        sb.append("INSERT INTO company VALUES ");
        FileReader reader = new FileReader("D://express.txt");
        BufferedReader br = new BufferedReader(reader);
        String str = null;
        while((str = br.readLine()) != null) {
            String[] split = str.split("\\s+");
            sb.append("("+split[0]+",'"+split[1]+"','"+split[2]+"'),"+"\n");
        }
        br.close();
        reader.close();

        FileWriter writer = new FileWriter("D://expressConvert.txt");
        BufferedWriter bw = new BufferedWriter(writer);
        bw.write(sb.toString());

        bw.close();
        writer.close();
    }
}
