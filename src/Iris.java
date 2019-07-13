import java.util.Objects;

public class Iris {

    private final double sepalLength;
    private final double sepalWidth;
    private final double petalLength;
    private final double petalWidth;

    private final String name;

    public Iris(double sepalLength, double sepalWidth, double petalLength, double petalWidth, String name) {
        this.sepalLength = sepalLength;
        this.sepalWidth = sepalWidth;
        this.petalLength = petalLength;
        this.petalWidth = petalWidth;
        this.name = name;
    }

    public Iris(Iris iris) {
        this.sepalLength = iris.sepalLength;
        this.sepalWidth = iris.sepalWidth;
        this.petalLength = iris.petalLength;
        this.petalWidth = iris.petalWidth;
        this.name = iris.name;
    }

    public double getSepalLength() {
        return sepalLength;
    }

    public double getSepalWidth() {
        return sepalWidth;
    }

    public double getPetalLength() {
        return petalLength;
    }

    public double getPetalWidth() {
        return petalWidth;
    }

    @Override
    public String toString() {
        return "Iris: {sepal_length: " + sepalLength + ", sepal_width: " + sepalWidth + ", petal_length: " + petalLength + ", petal_width: " + petalWidth + ", species: " + name + "}";
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        Iris iris = (Iris) o;
        return Double.compare(iris.sepalLength, sepalLength) == 0 &&
                Double.compare(iris.sepalWidth, sepalWidth) == 0 &&
                Double.compare(iris.petalLength, petalLength) == 0 &&
                Double.compare(iris.petalWidth, petalWidth) == 0 &&
                Objects.equals(name, iris.name);
    }
}