import java.util.Scanner;

public class Projekt2 {


        public static void main(String[] args) {

            Board board1 = new Board();
            board1.display();
            Scanner scanner = new Scanner(System.in);
            int row = 0;
            int col = 0;

            System.out.println("Kto zaczyna?");
            System.out.println("1 - Gracz");
            System.out.println("2 - Komputer");
            int turn = scanner.nextInt();
            if(turn == 2){
                Move bestMove = board1.findBestMove(board1.getBoard());
                board1.mark2(board1.getBoard(), bestMove.row,bestMove.col);
                board1.display();
            }

            while (true){

                System.out.println("Podaj wiersz: ");
                row = scanner.nextInt();
                System.out.println("Podaj kolumnę");
                col = scanner.nextInt();

                board1.mark(board1.getBoard(), row, col);
                board1.display();

                if(board1.isMovesLeft(board1.getBoard())){
                    Move bestMove = board1.findBestMove(board1.getBoard());
                    board1.mark2(board1.getBoard(), bestMove.row,bestMove.col);
                    board1.display();

                    if(board1.check() != 0){
                        break;
                    }
                }
                else{
                    System.out.println("remis");
                    break;
                }
            }
        }}

