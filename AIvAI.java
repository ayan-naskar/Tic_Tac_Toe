package TicTacToe;

public class AIvAI {
    public static void main(String[] args){
        TTTBoard ob = new TTTBoard();
        TTTBoard op = new TTTBoard();
        Integer[] move;
        char player1='X',player2='O';
        ob.AI=player1;
        ob.human=player2;
        op.AI=player2;
        op.human=player1;
        while(ob.numberOfEmptySpaces()>0){
            move=ob.getBestMove();
            ob.board[move[0]][move[1]]=player1;
            System.out.println(ob);
            if(ob.whowins()==player1) {
                System.out.println(player1+" won");
                break;
            }
            op.board[move[0]][move[1]]=player1;

            if(ob.numberOfEmptySpaces()==0) break;

            move=op.getBestMove();
            op.board[move[0]][move[1]]=player2;
            System.out.println(op);
            if(op.whowins()==player2) {
                System.out.println(player2+" won");
                break;
            }
            ob.board[move[0]][move[1]]=player2;
        }
        if(ob.whowins()==' ')
            System.out.println("Tie");
    }
}
