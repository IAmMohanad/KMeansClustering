public class Main {

    public static void main(String[] args) {
        IrisDataParser irisDataParser = new IrisDataParser();
        KMeansClustering kMeans = new KMeansClustering(irisDataParser.load("Data/iris.data"), 3);
        kMeans.cluster();
        kMeans.print();

    }
}
