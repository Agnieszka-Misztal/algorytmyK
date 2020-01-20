
import java.lang.reflect.Array;
import java.util.ArrayList;

public class Permutations {


    public Permutations() {
    }


    //Fisher-yeates shuffle algotithm
    public int nextRandomNumber(ArrayList<Integer> list){

        int size = list.size();
        int index = (int)(Math.random() * size);
        int randomNumber = list.get(index);


        //usun wylosowana liczbe z listy
        list.set(index, list.get(size-1));
        list.remove(size-1);

        return randomNumber;


    }

    public Integer[] generateRandomList(int length){
        ArrayList<Integer> list = new ArrayList<Integer>(length);
        ArrayList<Integer> randomList = new ArrayList<Integer>(length);
        for (int i = 0; i < length; i++) {
            list.add(i + 1);

        }
        while (list.size() > 0){
            randomList.add(nextRandomNumber(list));
        }

        Integer[] rList = new Integer[randomList.size()];

        return randomList.toArray(rList);

    }




    public  int fact(int n)
    {
        int res = 1;

        for (int i = 1; i <= n; i++)
            res = res * i;
        return res;
    }



    public  int trotterJohnsonRank( Integer pi[], int n )
    {
        int r = 0;
        for (int j = 2; j <=n ; j++) {
            int k = 1;
            int i =0;
            while (pi[i] != j){
                if(pi[i] < j){
                    k = k + 1;
                }
                i = i + 1;
            }
            if( (r % 2) == 0){
                r = j*r + j - k;
            }
            else {
                r = j * r + k -1;
            }

        }
        return r;
    }

    public int[] unrank(int rank, int n){
        int[] permutation =new int[n];
        permutation[0] = 1;
        int r2 = 0;
        for(int j = 2; j <= n; j++ ){
            int r1 = (int)Math.floor((rank * fact(j))/fact(n));
            int k = r1 - j * r2;
            if((r2 % 2) == 0){
                for (int i = j -1; i >= j - k; i--) {
                    permutation[i] = permutation[i-1]; }
                  permutation[j-k-1]=j;
                }
                else {
                for (int i = j - 1; i >= k + 1 ; i--) {
                    permutation[i]= permutation[i-1];
                }
                permutation[k] = j;
                }
                r2 = r1;

        }
        return permutation;

    }



}
