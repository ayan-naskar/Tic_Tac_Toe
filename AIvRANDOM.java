package TicTacToe;

public class AIvRANDOM {
    public static void main(String[] args){
        TTTBoard ob = new TTTBoard();
        Integer[] move;
        ob.AI='X';   
        ob.human='O';
        if(Math.random()>0.5){
            ob.AI='O';
            ob.human='X';
            move = ob.getRandomMove();
            ob.board[move[0]][move[1]]=ob.human;
            System.out.println(ob);
        }
        while(ob.numberOfEmptySpaces()>0){
            move=ob.getBestMove();
            ob.board[move[0]][move[1]]=ob.AI;
            System.out.println(ob);
            if(ob.whowins()==ob.AI){
                System.out.println(ob.AI+" wins(AI)");
                break;
            }
            if(ob.numberOfEmptySpaces()==0) break;
            move=ob.getRandomMove();
            ob.board[move[0]][move[1]]=ob.human;
            System.out.println(ob);
            if(ob.whowins()==ob.human){
                System.out.println(ob.human+" wins(Stupid AI)");
                break;
            }
        }
        if(ob.whowins()==' ') System.out.println("Tie");
    }
}
