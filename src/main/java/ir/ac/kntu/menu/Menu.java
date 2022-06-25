package ir.ac.kntu.menu;

import ir.ac.kntu.util.ScannerWrapper;

public interface Menu {

    void handleMenu();

    void showOptions();

    default <T extends Enum<T>> T scanTheEnum(T[] options) {
        int userInput = ScannerWrapper.getInstance().nextInt();
        userInput--;
        if (userInput >= 0 && userInput < options.length) {
            return options[userInput];
        }
        System.out.println("Invalid option!");
        return scanTheEnum(options);
    }

    <T extends Enum<T>> void handleTheOption(T option);

}
