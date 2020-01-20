import java.util.ArrayList;
import java.util.Scanner;

public class projekt1 {

    public static void main(String[] args) {

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
        System.out.print("Rank: " + p.trotterJohnsonRank(randomList,length));
        System.out.println();


        int[] unrnakPerm = p.unrank(22, 4);
        for (int i = 0; i < unrnakPerm.length ; i++) {
            System.out.print(unrnakPerm[i]);

        }


    }

}
