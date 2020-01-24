import java.util.Scanner;

public class Board2 {


    private char[][] board;
    private Scanner scanner;

    private final char EMPTY = '_';
    private char cpu = 'o', player = 'x';


    public char[][] getBoard() {
        return board;
    }

    public void setBoard(char[][] board) {
        this.board = board;
    }

    public Board2() {
        scanner = new Scanner(System.in);
        board = new char[3][3];
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                board[i][j] = EMPTY;
    }

    public void display() {

        System.out.println();
        System.out.print("       ");
        for (int i = 0; i < board.length; i++) {
            System.out.print(i);
            if (i < board.length - 1)
                System.out.print(" : ");
        }
        System.out.println();
        System.out.println();

        // display the board
        for (int j = 0; j < board.length; j++) {
            System.out.print("    " + j + ": ");
            for (int column = 0; column < board.length; column++) {
                System.out.print(board[j][column]);
                if (column < board.length - 1)
                    System.out.print(" | ");
            }
            if (j < board.length - 1)
                System.out.println("\n       ---------");
            else
                System.out.println();
        }
        System.out.println();
    }

    public char[][] mark(char[][] board, int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = 'x';
        }
        else{
            while (board[row][col] != EMPTY) {
                System.out.println("Hej to miejsce jest zajete!");
                System.out.println("wybierz jeszcze raz");
                System.out.println("Podaj wiersz: ");
                row = scanner.nextInt();
                System.out.println("Podaj kolumnę");
                col = scanner.nextInt();

            }
            board[row][col] = 'x';
        }

        return board;
    }

    public char[][] mark2(char[][] board, int row, int col) {
        if (board[row][col] == EMPTY) {
            board[row][col] = 'o';
        }


        return board;
    }

    Boolean isMovesLeft(char board[][]) {
        for (int i = 0; i < 3; i++)
            for (int j = 0; j < 3; j++)
                if (board[i][j] == EMPTY)
                    return true;
        return false;
    }

    public int evaluate(char b[][]) {
        // sprawdzanie wiersszy
        for (int row = 0; row < 3; row++)
        {
            if (b[row][0] == b[row][1] &&
                    b[row][1] == b[row][2])
            {
                if (b[row][0] == cpu)
                    return +10;
                else if (b[row][0] == player)
                    return -10;
            }
        }

        // sprawdzanie kolumn
        for (int col = 0; col < 3; col++)
        {
            if (b[0][col] == b[1][col] &&
                    b[1][col] == b[2][col])
            {
                if (b[0][col] == cpu)
                    return +10;

                else if (b[0][col] == player)
                    return -10;
            }
        }

        // sprawdzanie po skosie
        if (b[0][0] == b[1][1] && b[1][1] == b[2][2])
        {
            if (b[0][0] == cpu)
                return +10;
            else if (b[0][0] == player)
                return -10;
        }

        if (b[0][2] == b[1][1] && b[1][1] == b[2][0])
        {
            if (b[0][2] == cpu)
                return +10;
            else if (b[0][2] == player)
                return -10;
        }


        return 0;

    }

    //zwraca wartosc planszy dla wszytkich dostepncyh ruchów

    public int minimax(char board[][],
                       int depth, Boolean isMax)
    {
        depth = 1;
        int score = evaluate(board);

        //cpu win
        if (score == 10)
            return score -depth;

        // player win
        if (score == -10)
            return score -depth;

        // remis
        if (isMovesLeft(board) == false)
            return 0 -depth;


        if (isMax)
        {
            int best = -1000;


            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {

                    if (board[i][j]=='_')
                    {
                        //ruch cpu
                        board[i][j] = cpu;

                        //wywolanie rekurencji, wybranie maksymalnej wart, zwiekszanie glebokosci
                        best = Math.max(best, minimax(board,
                                depth + 1, !isMax));

                        //cofnij ruch
                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }

        // podpowiedzi dla gracza
        else
        {
            int best = 1000;


            for (int i = 0; i < 3; i++)
            {
                for (int j = 0; j < 3; j++)
                {

                    if (board[i][j] == '_')
                    {

                        board[i][j] = player;


                        best = Math.min(best, minimax(board,
                                depth + 1, !isMax));


                        board[i][j] = '_';
                    }
                }
            }
            return best;
        }
    }

    public Move findBestMove(char board[][])
    {
        int bestVal = -1000;
        Move bestMove = new Move();
        bestMove.row = -1;
        bestMove.col = -1;

        // wywolanie min max dla wszytkich wolnych miejsc

        for (int i = 0; i < 3; i++)
        {
            for (int j = 0; j < 3; j++)
            {

                if (board[i][j] == '_')
                {

                    board[i][j] = cpu;

                    //wartosc dla tego ruchu
                    int temp = minimax(board, 0, false);

                    //cofnij
                    board[i][j] = '_';

                    //jezeli ruch jest lepszy, podmien
                    if (temp > bestVal)
                    {
                        bestMove.row = i;
                        bestMove.col = j;
                        bestVal = temp;
                    }
                }
            }
        }



        return bestMove;
    }

    public int check(){
        if (evaluate(board) == 10){
            System.out.println("przegrałeś!");
            return 1;}
        if (evaluate(board) == (-10)){
            System.out.println("wygrales");
            return 1;}
        if(!isMovesLeft(board)){
            System.out.println("remis");
            return 1;
        }

        return 0;
    }


}


