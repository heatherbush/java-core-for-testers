import java.io.*;
import java.util.ArrayList;

public class AppData {
    private String[] header;
    private Integer[][] data;

    public AppData(String[] header, Integer[][] data) {
        this.header = header;
        this.data = data;
    }

    public AppData() {
    }

    public String[] getHeader() {
        return header;
    }

    public void setHeader(String[] header) {
        this.header = header;
    }

    public Integer[][] getData() {
        return data;
    }

    public void setData(Integer[][] data) {
        this.data = data;
    }

    public void save(String fileName) {
        try (BufferedWriter writer = new BufferedWriter(new FileWriter(fileName))) {

            String headerString = new String("");
            for (int i = 0; i < header.length; i++) {
                headerString += header[i];
                if (i != header.length - 1) {
                    headerString += ";";
                } else {
                    headerString += "\n";
                }
            }
            writer.write(headerString);

            for (Integer[] dataString : data) {
                String dataDividedString = new String("");
                for (int i = 0; i < dataString.length; i++) {
                    dataDividedString += dataString[i].toString();
                    if (i != dataString.length - 1) {
                        dataDividedString += ";";
                    } else {
                        dataDividedString += "\n";
                    }
                }
                writer.write(dataDividedString);
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void load(String fileName) {
        try (BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName))) {
            header = bufferedReader.readLine().split(";");

            ArrayList<Integer[]> dataFromStrings = new ArrayList<>();
            String nextString = new String();
            while ((nextString = bufferedReader.readLine()) != null) {
                String[] workString = nextString.split(";");
                Integer[] numbers = new Integer[workString.length];
                for (int i = 0; i < workString.length; i++) {
                    numbers[i] = Integer.parseInt(workString[i]);
                }
                dataFromStrings.add(numbers);
            }
            data = dataFromStrings.toArray(new Integer[][]{{}});
        } catch (NumberFormatException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


}
