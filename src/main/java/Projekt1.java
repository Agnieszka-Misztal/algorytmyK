// Java program to print all
// permutations using Johnson
// and Trotter algorithm.
import java.util.*;
import java.lang.*;

public class Projekt1 {

    private final static boolean RIGHT = true;
    private final static boolean LEFT = false;


    //pozycja najwiekszego elemntu /mobilnego elementu
    public static int searchArr(int a[], int n,
                                int mobile)
    {
        for (int i = 0; i < n; i++)

            if (a[i] == mobile)
                return i + 1;

        return 0;
    }

    //znajdz najwieksza wartosc
    //musi byc wiekszy od swojego siąsiada  w ktorego strone jest skierowany
    public static int getMobile(int permutation[],
                                boolean directions[], int n)
    {
        int mobile_prev = 0, mobile = 0;

        for (int i = 0; i < n; i++)
        {
            // left - 0
            if (directions[permutation[i] - 1] == LEFT &&
                    i != 0)
            {
                if (permutation[i] > permutation[i - 1] &&
                        permutation[i] > mobile_prev)
                {
                    mobile = permutation[i];
                    mobile_prev = mobile;
                }
            }

            // right - 1
            if (directions[permutation[i] - 1] == RIGHT &&
                    i !=n - 1)
            {
                if (permutation[i] > permutation[i + 1] &&
                        permutation[i] > mobile_prev)
                {
                    mobile = permutation[i];
                    mobile_prev = mobile;
                }
            }
        }

        if (mobile == 0 && mobile_prev == 0)
            return 0;
        else
            return mobile;
    }


    public static int generateNextPermutation(int permutation[], boolean directions[],
                                              int n)
    {
        //wartosc mobilnego elemntu
        int mobile = getMobile(permutation, directions, n);
        //indeks mobilnego elemntu
        int position = searchArr(permutation, n, mobile);

        // zamien z sąsiadem w korego strone jest skierowany mobilny element
        if (directions[permutation[position - 1] - 1] == LEFT)
        {
            int temp = permutation[position - 1];
            permutation[position - 1] = permutation[position - 2];
            permutation[position - 2] = temp;
        }
        else if (directions[permutation[position - 1] - 1] == RIGHT)
        {
            int temp = permutation[position];
            permutation[position] = permutation[position - 1];
            permutation[position - 1] = temp;
        }

        //zmiana kierunkow, elemntow ktore sa wieksze od mobilnego
        for (int i = 0; i < n; i++)
        {
            if (permutation[i] > mobile)
            {
                if (directions[permutation[i] - 1] == RIGHT)
                    directions[permutation[i] - 1] = LEFT;

                else if (directions[permutation[i] - 1] == LEFT)
                    directions[permutation[i] - 1] = RIGHT;
            }
        }

        for (int i = 0; i < n; i++)
            System.out.print(permutation[i]);

        System.out.print(" ");

        return 0;
    }

  //ilosc mozliwych permutacji
    public static int fact(int n)
    {
        int factorial = 1;

        for (int i = 1; i <= n; i++)
            factorial = factorial * i;
        return factorial;
    }


    public static void printPermutation(int n)
    {

        int[] permutation = new int[n];


        boolean[] directions = new boolean[n];

        // permutacja o ranku 0
        System.out.print("0: ");
        for (int i = 0; i < n; i++)
        {
            permutation[i] = i + 1;
            System.out.print(permutation[i]);
        }

        System.out.print("\n");

        //wszytkie kierunki na lewo < najmniejszy do najwiekszego
        for (int i = 0; i < n; i++)
            directions[i] = LEFT;


        //generuj reszte permutacji
        for (int i = 1; i < fact(n); i++){
            System.out.print(i +": ");
            generateNextPermutation(permutation, directions, n);
            System.out.println();}

    }




    public static void main(String argc[])
    {
        Scanner scanner = new Scanner(System.in);
        System.out.println("Podaj długość permutacji");
        int length = scanner.nextInt();

        System.out.println("Długość ciągu " + length);
        Permutations p = new Permutations();
        System.out.println("Maksymalna liczba permutacji: " + p.fact(length));
        Integer[] randomList = p.generateRandomList(length);
        System.out.print("Losowa permutacja: ");


        for(Integer i : randomList){
            System.out.print(i + " ");
        }
        int rank = p.rank(randomList,length);
        System.out.print("Rank: " + rank);
        //System.out.println("Następnik: " + p.unrank(rank + 1, length));
        System.out.println();
        System.out.print("Następnik: ");

        if((rank + 1) > (fact(length) -1)){
            System.out.print("Brak");
        }
        else {
            int[] succ = p.unrank(rank + 1, length);
            for (int i = 0; i < succ.length; i++) {
                System.out.print(succ[i]);
            }
        }
        System.out.println();
        System.out.print("Poprzednik: ");
        if((rank -1)< 0){
            System.out.print("Brak");
        }
        else {
            int[] prev = p.unrank(rank - 1, length);
            for (int i = 0; i < prev.length; i++) {
                System.out.print(prev[i]);
            }
        }
        System.out.println();
        printPermutation(length);


    }

}


