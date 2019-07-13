import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class KMeansClustering {
    private List<Iris> irises;
    private List<Iris> centroids = new ArrayList<>();
    private List<List<Iris>> clusters = new ArrayList<>();
    private List<Iris> previousCentroids = new ArrayList<>();

    public KMeansClustering(List<Iris> irises, int k) {
        this.irises = irises;

        for (int i = 0; i < k; ++i) {
            centroids.add(new Iris(irises.get(new Random().nextInt(irises.size() / k) * (i + 1))));
            previousCentroids.add(new Iris(centroids.get(i)));

            clusters.add(new ArrayList<>());
        }
    }

    public void cluster() {
        boolean haveCentroidsMoved = true;
        while (haveCentroidsMoved) {
            clearClusters();
            addIrisToCluster();
            moveCentroids();
            haveCentroidsMoved = centroidsMoved();
        }
    }

    private boolean centroidsMoved() {
        for (int i = 0; i < centroids.size(); ++i) {
            if (!centroids.get(i).equals(previousCentroids.get(i))) {
                return true;
            }
        }
        return false;
    }

    //Find closest centroid to given iris
    private int findClosestCentroid(Iris iris) {
        double minDistance = distance(iris, centroids.get(0));
        double distance;
        int closestCentroid = 0;

        for (int i = 1; i < centroids.size(); ++i) {
            distance = distance(iris, centroids.get(i));

            if (distance < minDistance) {
                minDistance = distance;
                closestCentroid = i;
            }
        }

        return closestCentroid;
    }

    // Euclidean distance
    private double distance(Iris a, Iris b) {
        return Math.sqrt(Math.pow(a.getSepalLength() - b.getSepalLength(), 2) + Math.pow(a.getSepalWidth() - b.getSepalWidth(), 2) +
                Math.pow(a.getPetalLength() - b.getPetalLength(), 2) + Math.pow(a.getPetalWidth() - b.getPetalWidth(), 2));
    }

    // Calculates the mean for all iris' in each cluster and moves centroid to the mean position
    private void moveCentroids() {
        double sepalLength;
        double sepalWidth;
        double petalLength;
        double petalWidth;
        int clusterSize;
        List<Iris> cluster;

        for (int i = 0; i < clusters.size(); ++i) {
            cluster = clusters.get(i);
            clusterSize = cluster.size();
            sepalLength = 0.0;
            sepalWidth = 0.0;
            petalLength = 0.0;
            petalWidth = 0.0;

            for (int j = 0; j < clusterSize; ++j) {
                sepalLength += cluster.get(j).getSepalLength();
                sepalWidth += cluster.get(j).getSepalWidth();
                petalLength += cluster.get(j).getPetalLength();
                petalWidth += cluster.get(j).getPetalWidth();
            }

            sepalLength /= clusterSize;
            sepalWidth /= clusterSize;
            petalLength /= clusterSize;
            petalWidth /= clusterSize;

            previousCentroids.set(i, new Iris(centroids.get(i)));
            centroids.set(i, new Iris(sepalLength, sepalWidth, petalLength, petalWidth, "Centroid"));
        }
    }

    private void clearClusters() {
        for (List<Iris> cluster : clusters) {
            cluster.clear();
        }
    }

    private void addIrisToCluster() {
        for (Iris iris : irises) {
            clusters.get(findClosestCentroid(iris)).add(iris);
        }
    }

    public void print() {
        for (int i = 0; i < clusters.size(); ++i) {
            System.out.println("\nCluster " + (i + 1));

            for (int j = 0; j < clusters.get(i).size(); ++j) {
                System.out.println(clusters.get(i).get(j));
            }
        }
    }
}