import java.util.Scanner;

// Java program to find the
// next optimal move for a player
class GFG{

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
//            if (board1.evaluate(board1.getBoard()) == 10){
//                System.out.println("przegrałeś!");
//                    break;}
//            if (board1.evaluate(board1.getBoard()) == (-10)){
//                System.out.println("koniecccc");
//                break;}
                if(board1.check() != 0){
                    break;
                }


            }
            else{
                break;
            }

        }



}}


