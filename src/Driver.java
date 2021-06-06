public class Driver {

    public static void main(String[] args) {
        Grid test = new Grid(10, 8, 10);
        System.out.println(test.getNeighbors(4, 3));
    }
}
