package Tasks;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Random;
import java.util.function.DoublePredicate;
import java.util.function.DoubleUnaryOperator;

public class Main {
    public static void main(String[] args) {

        //[-2PI, 2PI] , step PI/6
        System.out.println("1] y = cosX : \n");
        Functional f = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                System.out.println(Math.cos(i));
            }
        };
        calculateF(f);

        System.out.println("\n2] 2*sqrt(abs(x - 1)) + 1 : \n");
        f = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                double y = 2*Math.sqrt(Math.abs(i - 1)) + 1;
                System.out.println(y);
            }
        };
        calculateF(f);

        System.out.println("\n3] -(x/pi)^2 -2x + 5pi : \n");
        f = () -> {
            for (double i = -2*Math.PI; i <= 2*Math.PI; i += Math.PI/6) {
                double y = -1*Math.pow(i/Math.PI,2) -2*i + 5*Math.PI;
                System.out.println(y);
            }
        };
        calculateF(f);

        System.out.println("\n4] (x/pi*k - 1)^2 for k=1 to k=100 : \n");
        f = () -> {
            double y =0;
            double i = -2*Math.PI;
            for (int k = 1; i <= 100; i++) {
                if (i > 2*Math.PI) {
                    break;
                }
                y += Math.pow((i/(Math.PI * k)) - 1,2);
                System.out.println(y);
                System.out.println("i = "+i);
                i += Math.PI/6;
            }
        };
        calculateF(f);


        System.out.println("\n5] y = 1/4sin^2x + 1 if x < 0, else then y = 1/2cosx - 1 : \n");
        f = () -> {
          double y;
            for (double i = -2*Math.PI; i <2*Math.PI ; i+= Math.PI/6) {
                if (i < 0) {
                    y = (1.0/4.0)*Math.pow(Math.sin(i),2) + 1;
                    System.out.println(y);
                } else {
                    y = (1.0/2.0)*Math.cos(i) -1;
                    System.out.println(y);
                }
            }
        };
        calculateF(f);



        //Second part
        System.out.println("\nSecond part : \n");

        ArrayList<ArrayList<Double>> equations = new ArrayList<>();

        //Randomly generated points arrayList
        ArrayList<Double> points = generateRandomPoints(10);

        DoubleUnaryOperator[] functions = new DoubleUnaryOperator[]{
          i -> Math.cos(i),
          i -> 2 * Math.sqrt(Math.abs(i - 1)) + 1,
          i -> -1*Math.pow(i/Math.PI,2) -2*i + 5*Math.PI,
          i -> {
              double y = 0;
              i = -2 * Math.PI;
              for (int k = 1; i <= 100; i++) {
                  if (i > 2 * Math.PI) {
                      break;
                  }
                  y += Math.pow((i / (Math.PI * k)) - 1, 2);
                  i += Math.PI / 6;
              }
              return y;
          },
          i -> {
              double y =0;
              for (i = -2*Math.PI; i <2*Math.PI ; i+= Math.PI/6) {
                  if (i < 0) {
                      y = (1.0/4.0)*Math.pow(Math.sin(i),2) + 1;
                  } else {
                      y = (1.0/2.0)*Math.cos(i) -1;
                  }
              }
              return y;
          }
        };

        //Printing the set of randomly generated pts
        System.out.println("Randomly generated arrayList : ");
        for (Double point : points) {
            System.out.print(point+" ");
        }
        System.out.println();
        //Finding the min/max values for each of the functions
        int i =1;
        for (DoubleUnaryOperator function : functions) {
            double minValue = Double.POSITIVE_INFINITY;
            double maxValue = Double.NEGATIVE_INFINITY;


            for (double point : points) {
                double result = function.applyAsDouble(point);
                minValue = Math.min(minValue,result);
                maxValue = Math.max(maxValue,result);
            }

            System.out.println("\nFunction: "+ i);
            System.out.println("Minimum value: " + minValue);
            System.out.println("Maximum value: " + maxValue+"\n");
            i++;
        }

        //The rest of the tasks, except the last
        doFunctions(equations,functions);

    }


    public static void calculateF(Functional f) {
        f.calculate();
    }

    public static void doFunctions(ArrayList<ArrayList<Double>> equations, DoubleUnaryOperator[] functions) {
        int negativeNumbers = 0;
        int inRangeNumbers = 0;
        DoublePredicate isNegative = value -> value < 0;
        DoublePredicate isInRange = value -> (value > -1 && value < 1);

        for (double l = -2*Math.PI; l <= 2*Math.PI; l += Math.PI/6) {
            ArrayList<Double> results = new ArrayList<>();
            for (DoubleUnaryOperator function : functions) {
                double result = function.applyAsDouble(l);
                System.out.println("\n"+result);
                if (isNegative.test(result)) {
                    System.out.println("Negative");
                    negativeNumbers++;
                }
                if (isInRange.test(result)) {
                    System.out.println("In range"+"\n");
                    inRangeNumbers++;
                }
                results.add(result);
            }
            equations.add(results);
        }

        for (ArrayList<Double> equation : equations) {
            System.out.println("\n"+equation);
        }

        System.out.println("\nNegative numbers count : "+negativeNumbers);
        System.out.println("In range [-1,1] numbers count : "+inRangeNumbers);

        ArrayList<ArrayList<Double>> arrangedList = arrangeArray(equations);
        System.out.println("\nArranged array :");
        for (ArrayList<Double> array : arrangedList) {
            System.out.println("\n"+array);
        }
    }



    //Arranging the arrayList to have the values of each function in a separate subList
    public static ArrayList<ArrayList<Double>> arrangeArray(ArrayList<ArrayList<Double>> arrayList) {

        int maxArraySize = getMaxSize(arrayList);
        ArrayList<ArrayList<Double>> arrangedArrayList = new ArrayList<>();

        for (int i = 0; i < maxArraySize; i++) {
            ArrayList<Double> subArrayList = new ArrayList<>();
            for (ArrayList<Double> list : arrayList) {
                if (i < list.size()) {
                    subArrayList.add(list.get(i));
                }
            }
            arrangedArrayList.add(subArrayList);
        }
        return arrangedArrayList;
    }

    public static int getMaxSize(ArrayList<ArrayList<Double>> arrayList) {
        int maxSize = 0;

        for (ArrayList<Double> sublist : arrayList) {
            if (sublist.size() > maxSize) {
                maxSize = sublist.size();
            }
        }
        return maxSize;
    }

    public static ArrayList<Double> generateRandomPoints(int n) {
        Random rnd = new Random();
        ArrayList<Double> points = new ArrayList<>();
        for (int i = 0; i < n; i++) {
            double point = rnd.nextDouble(-10.0,10.0);
            points.add(point);
        }
        return points;
    }
}
