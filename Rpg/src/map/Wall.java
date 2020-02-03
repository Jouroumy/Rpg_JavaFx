package map;

public class Wall implements Cell {
    @Override
    public void showCell() {
        System.out.print("#");
    }
}
