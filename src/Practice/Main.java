package Practice;

import java.util.function.Function;

public class Main {
    public static void main(String[] args) {

        Cat cat = new Cat();
        cat.print("s");

        //Same function tho here we define the method within the printIt() method using lambda
        printIt(
                //In the brackets here we can add parameters if there r any, fe the String "s"
                (s) -> {
            System.out.println("Meow"+s);
        }

        //As it's a single implementation, we can write it in one line as :
                //  ||||printIt(() -> System.out.println("Meow"));|||||
        );

        //Other implementation
        Printable lambdaCat = (s) -> System.out.println("Meow"+s);
        printIt(lambdaCat);


        //Implementation with a return type
        DogPrintable lambdaDog = (Dogo) -> "Bark "+ Dogo;
        printDog(lambdaDog);


    }

    public static void printIt(Printable thing) {
        thing.print("s");
    }

    public static void printDog(DogPrintable thing) {
        System.out.println(thing.print("Dogo"));;
    }
}
