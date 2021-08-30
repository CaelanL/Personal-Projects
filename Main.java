
import java.util.*;

public class Main {
        public static void main(String[] args) {
        char[][] gameBoard = {{'1','|','2','|','3'},
                              {'-','+','-','+','-'},
                              {'4','|','5','|','6'},
                              {'-','+','-','|','-'},
                              {'7','|','8','|','9'}};
        int whichPlayer=1; //1 = person, 0 = cpu
        
         ArrayList takenMoves = new ArrayList<>();
         //9printBoard(gameBoard);            
        while(true){
            System.out.println();
            System.out.println("Cpu has Moved");
            whichPlayer = 1;
            int cpu = bestMove(gameBoard,whichPlayer);
            takenMoves.add(cpu);
            playerMove(cpu, gameBoard, "cpu");
            printBoard(gameBoard);
            if (checkForWin(gameBoard, whichPlayer)!=-1){
                break;
            }


            whichPlayer = -1;
            System.out.println("Enter value 1-9 to place move: ");
            Scanner scan = new Scanner(System.in);
            int move = scan.nextInt();
            while(move<1||move>9||takenMoves.contains(move)){
                System.out.println("Invalid Move Try again!");
                move = scan.nextInt();
            }
            takenMoves.add(move);
            playerMove(move, gameBoard,"player");
            printBoard(gameBoard);
            if (checkForWin(gameBoard, whichPlayer)!=-1){
                break;
            }
         
           
            
            
           
        }
        
        if (checkForWin(gameBoard, whichPlayer) == -10){
            System.out.println("Congratulaions you have won!");
        }
        else if(checkForWin(gameBoard, whichPlayer) == 10){
            System.out.println("Lol you must be a windows user");
        }
        else if(checkForWin(gameBoard, whichPlayer) == 0){
            System.out.println("TIE!");
        }
    }
    public static void printBoard(char[][] gameBoard){
        for(char[] row : gameBoard){
            for(char i : row){
                System.out.print(i);
                }
            System.out.println();
        }   
        System.out.println();
    }
    public static void playerMove(int x, char[][] gameBoard, String userType){
        char c;
        if (userType == "cpu"){
             c = 'O';
        }
        else{
             c = 'X';
        }
        /* char[] collection = {gameBoard[0][0], gameBoard[0][2], gameBoard[0][4],gameBoard[2][0],gameBoard[2][2],gameBoard[2][4],gameBoard[4][0],gameBoard[4][2],gameBoard[4][4]};
        if ((collection[x]== 'X')||(collection[x]== 'O')){
           System.out.println("Sorry invalid selection, try again!");
           return;
        }
 */

        
         switch(x){
        
            case 1:
                gameBoard[0][0] = c;
                break;
            case 2:
                gameBoard[0][2] = c;
                break;
            case 3:
                gameBoard[0][4] = c;
                break;
            case 4:
                gameBoard[2][0] = c;
                break;
            case 5:
                gameBoard[2][2] = c;
                break;
            case 6:
                gameBoard[2][4] = c;
                break;
            case 7:
                gameBoard[4][0] = c;
                break;
            case 8:
                gameBoard[4][2] = c;
                break;
            case 9:
                gameBoard[4][4] = c;
                break;
            default:
                break; 
        }
    }
    public static int checkForWin(char[][] gameBoard, int player){
      int i;
      int j;
      int winner =-1;
        //horizontal chekc
        for( i= 0; i<5;i+=2){
            if ((gameBoard[i][0]== gameBoard[i][2])&&(gameBoard[i][2]==gameBoard[i][4])){
                winner = 10;
                return winner*player;
            }
        }
        //vertical check
        for( i= 0; i<5;i+=2){
            if ((gameBoard[0][i]== gameBoard[2][i])&&(gameBoard[2][i]==gameBoard[4][i])){
                winner =10;
               return winner*player;
            }
        }
        //diagnoal check
        if ((gameBoard[0][0]== gameBoard[2][2])&&(gameBoard[2][2]==gameBoard[4][4])){
            winner = 10;
            return winner*player;
        }
        if ((gameBoard[0][4]== gameBoard[2][2])&&(gameBoard[2][2]==gameBoard[4][0])){
                winner = 10;
                return winner*player;
        }
        boolean noTie = false;
        for(i=0;i<5;i+=2){
            
            for(j=0; j<5;j+=2){
               if((gameBoard[i][j] != 'O')&&(gameBoard[i][j] != 'X')){
                   noTie = true;
               }
            }
        }
            if(!noTie){
                
                return 0;
            }
        
        
        return winner;
    }
    public static int bestMove(char[][] gameboard, int player){
       int bestscore = -1000;
       int score;
       int O =0;
       int X =0;
       char L;
       int[][] bestMove = {{1,0,2,0,3},{0},{4,0,5,0,6},{0},{7,0,8,0,9}};
       int i;
       int j;
        for(i=0;i<5;i+=2){
            for(j=0; j<5;j+=2){
                if ((gameboard[i][j] != 'O')&&(gameboard[i][j] != 'X')){
                    L = gameboard[i][j];
                    gameboard[i][j] = 'O';
                    score = minmax(gameboard,-1 ,0);
                    gameboard[i][j] = L;
                    if(score>bestscore){
                       bestscore = score;
                       O = i;
                       X = j;
                       
                    }


                }
            }
        }
        return bestMove[O][X];
    }
    public static int minmax(char[][] gameboard, int player, int depth){
        if(checkForWin(gameboard,player*-1 )!= -1 ){
            return checkForWin(gameboard, player*-1)+depth;
        }
        //maximzing 
        if(player == 1){
            int bestscore = -1000;
            int score;
            char L;
            int i;
            int j;
                for(i=0;i<5;i+=2){
                    for(j=0; j<5;j+=2){
                        if ((gameboard[i][j] != 'O')&&(gameboard[i][j] != 'X')){
                            L = gameboard[i][j];
                            gameboard[i][j] = 'O';
                            score = minmax(gameboard,-1 ,depth +1);
                            gameboard[i][j] = L;
                            if(score>bestscore){
                            bestscore = score;
                           
                            
                            }


                        }
                    }
                }
                return bestscore;
            
        }
        else{
            int bestscore = 1000;
            int score;
            char L;
            int i;
            int j;
                for(i=0;i<5;i+=2){
                    for(j=0; j<5;j+=2){
                        if ((gameboard[i][j] != 'O')&&(gameboard[i][j] != 'X')){
                            L = gameboard[i][j];
                            gameboard[i][j] = 'X';
                            score = minmax(gameboard,1 ,depth +1);
                            gameboard[i][j] = L;
                            if(score<bestscore){
                            bestscore = score;
                            
                            }


                        }
                    }
                }
                return bestscore;
        }
    }

}
