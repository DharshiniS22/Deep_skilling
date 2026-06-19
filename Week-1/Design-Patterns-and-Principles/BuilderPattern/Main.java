public class Main {

    public static void main(String[] args) {

        Computer computer = new Computer.Builder()
                .setCPU("Intel i7")
                .setRAM(16)
                .setStorage(512)
                .build();

        computer.display();
    }
}
