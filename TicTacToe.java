package TicTacToe;

import java.util.Scanner;

public class TicTacToe {
    public static void main(String[] args){
        try (Scanner sc = new Scanner(System.in)) {
            TTTBoard ob = new TTTBoard();
            int turns=0;
            char AI='X',human='O';
            Integer[] move;

            System.out.print("Press 1 for Computer, 2 for Human 's first Turn: ");
            if(sc.nextInt()==2){
                turns++;
                human='X';
                AI='O';

                move = ob.getHumanMove();
                ob.setTurn(move[0], move[1], human);            
            }

            ob.AI=AI;
            ob.human=human;
            
            for(;turns<9;turns+=2){
                //turn for the AI
                move = ob.getBestMove();
                ob.setTurn(move[0], move[1], AI);
                System.out.println(ob);
                if(ob.whowins()!=' ' || turns==8) break;

                //turn for the Human
                move = ob.getHumanMove();
                while(!ob.setTurn(move[0], move[1], human)){
                    System.out.println("Invalid Move!! Enter again!!");
                    move = ob.getHumanMove();
                }
                if(ob.whowins()!=' ') break;
            }
            System.out.println("The Final State of Board is:\n"+ob);
            if(turns>=9 || ob.whowins()==' ') System.out.println("Tie!!");
            else System.out.println(ob.whowins()+" wins!!");
            sc.close();
        }
    }
}