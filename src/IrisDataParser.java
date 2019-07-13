import java.io.BufferedReader;
import java.io.FileReader;
import java.util.ArrayList;
import java.util.List;


public class IrisDataParser {

    public List<Iris> load(String fileName) {

        String line;
        List<Iris> irisData = new ArrayList<>();

        try {
            BufferedReader bufferedReader = new BufferedReader(new FileReader(fileName));

            while ((line = bufferedReader.readLine()) != null) {
                String[] data = line.split(",");
                irisData.add(new Iris(Double.parseDouble(data[0]), Double.parseDouble(data[1]), Double.parseDouble(data[2]), Double.parseDouble(data[3]), data[4]));
            }
            bufferedReader.close();
        } catch (Exception e) {
            System.out.println("Failed to parse Iris data with error: " + e.getMessage());
        }
        return irisData;
    }
}
