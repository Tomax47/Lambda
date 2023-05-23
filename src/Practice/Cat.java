package Practice;

public class Cat implements Printable{

    private String name;
    private int age;


    @Override
    public void print(String thing) {
        System.out.println("Meow "+thing);
    }
}
