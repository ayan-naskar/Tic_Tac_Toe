package TicTacToe;

import java.util.ArrayList;
import java.util.List;

public class TTTBoard {
    char[][] board;
    char AI,human;
    public TTTBoard(){ // default constructor
        board=new char[3][3];
        for(int i=0;i<3;i++)
            for(int j=0;j<3;j++)
                board[i][j]=' ';
    }

    public char whowins(){
        if(checkFor('X'))return 'X';
        if(checkFor('O'))return 'O';
        return ' ';
    }

    private boolean checkFor(char player_symbol){
        return  travelIn(0, 0, 1, 1, player_symbol) ||
                travelIn(0, 0, 0, 1, player_symbol) ||
                travelIn(0, 0, 1, 0, player_symbol) ||
                travelIn(1, 0, 0, 1, player_symbol) ||
                travelIn(2, 0, 0, 1, player_symbol) ||
                travelIn(0, 1, 1, 0, player_symbol) ||
                travelIn(0, 2, 1, 0, player_symbol) ||
                travelIn(2, 0, -1, 1, player_symbol);
    }

    private boolean travelIn(int startingpointx, int startingpointy, int directionx, int directiony, char player_symbol){
        for(;startingpointx<3 && startingpointy<3;startingpointx+=directionx, startingpointy+=directiony){
            if(board[startingpointx][startingpointy]!=player_symbol)
                return false;
        }
        return true;
    }

    @Override
    public String toString(){
        String output="";
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++)
                output+="|"+board[i][j]+"|";
            output+="\n";
        }
        return output;
    }

    public boolean setTurn(int pos_x, int pos_y,char player_symbol){
        if(board[pos_x][pos_y]==' ') board[pos_x][pos_y]=player_symbol;
        else return false;
        return true;
    }

    public Integer[] getBestMove() {
        //i also have to implement random function to select random moves for moves with similar maximum score

        if(numberOfEmptySpaces()==9) return new Integer[]{(int)(Math.random()*3),(int)(Math.random()*3)};

        List<Integer[]> list = new ArrayList<Integer[]>();
        Integer[] bestMove;
        int bestScore = Integer.MIN_VALUE,score;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    board[i][j]=AI;
                    
                    if(whowins()==AI)//somebody(AI) wins return or if it is draw
                        score=1*(numberOfEmptySpaces()+1);
                    else score=minimax(false,0);//with minimax algorithm to find most optimal move
                    
                    board[i][j]=' ';
                    if(score>bestScore){
                        list = new ArrayList<Integer[]>();
                        bestMove = new Integer[2];
                        bestScore=score;
                        bestMove[0]=i;
                        bestMove[1]=j;
                        list.add(bestMove);
                    }else if(score==bestScore){
                        bestMove = new Integer[2];
                        bestMove[0]=i;
                        bestMove[1]=j;
                        list.add(bestMove);
                    }
                }
            }
        }

        return list.get((int)(Math.random()*list.size()));
    }

    public int numberOfEmptySpaces() {
        int spaces=0;
        for(int i=0;i<3;i++)
        for(int j=0;j<3;j++)
        if(board[i][j]==' ')
        spaces++;
        return spaces;
    }

    private int minimax(boolean isMaximizing,int depth){
        int bestScore = isMaximizing?Integer.MIN_VALUE:Integer.MAX_VALUE,score;
        char player = isMaximizing?AI:human;
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' '){
                    board[i][j]=player;
                    if(whowins()==player){
                        score = (isMaximizing?1:-1)*(numberOfEmptySpaces()+1);
                        board[i][j]=' ';
                    }
                    else{ 
                        if(numberOfEmptySpaces()==0 && whowins()==' ')
                        score=0;
                        else score=minimax(!isMaximizing,depth+1);//with minimax algorithm to find most optimal move
                    }
                    board[i][j]=' ';
                    if((score>bestScore) == isMaximizing){
                        bestScore=score;
                    }
                }
            }
        }
        return bestScore;
    }

    public Integer[] getHumanMove() {
        String[] turn = new java.util.Scanner(System.in).nextLine().split(" ");
        return new Integer[]{Integer.valueOf(turn[0]), Integer.valueOf(turn[1])};
    }

    public Integer[] getRandomMove() {
        List<Integer[]> list = new ArrayList<Integer[]>();
        for(int i=0;i<3;i++){
            for(int j=0;j<3;j++){
                if(board[i][j]==' ') list.add(new Integer[]{i,j});
            }
        }
        return list.get((int)(Math.random()*list.size()));
    }
}