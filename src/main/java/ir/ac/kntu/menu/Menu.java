package ir.ac.kntu.menu;

public interface Menu {

    void handleMenu();

    <T extends Enum<T>> void handleTheOption(T option);

}
