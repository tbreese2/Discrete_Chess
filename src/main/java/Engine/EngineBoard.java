/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine;

/**
 *
 * @author tylerbreese
 */
public class EngineBoard {
    
    //basic bitboards
    private long WhitePawns;
    private long WhiteKnights;
    private long WhiteBishops;
    private long WhiteRooks;
    private long WhiteQueens;
    private long WhiteKing;
    
    private long BlackPawns;
    private long BlackKnights;
    private long BlackBishops;
    private long BlackRooks;
    private long BlackQueens;
    private long BlackKing;
    
    //combined bitboards
    private long WhitePieces;
    private long BlackPieces;
    
    //board information vars
    private boolean whiteToMove;
    
    //EFFECTS: creates a new engine board without peices
    //NOTE: the engineboard class is designed to use bitboard
    //which are much faster for computations
    public EngineBoard() {
        //initialize all bitboards to be empty
        WhitePawns = 0L;
        WhiteKnights = 0L;
        WhiteBishops = 0L;
        WhiteRooks = 0L;
        WhiteQueens = 0L;
        WhiteKing = 0L;
    
        BlackPawns = 0L;
        BlackKnights = 0L;
        BlackBishops = 0L;
        BlackRooks = 0L;
        BlackQueens = 0L;
        BlackKing = 0L;
    
        //combined bitboards
        WhitePieces = 0L;
        BlackPieces = 0L;
    }
    
    //REQUIRES: input string must be in FEN postion format,
    //see https://www.chess.com/terms/fen-chess
    //for more details
    //MODIFES: EngineBoard
    //EFFECTS: sets engine board according to inputed string
    public void setBoard(String FEN, boolean whiteToPlay) {
        
       //first convert FEN to array
       String[][] tempBoard = new String[8][8];
       int r = 7;
       int c = 0;
       for (int i = 0; i < FEN.length(); i++) {
            Boolean flag = Character.isDigit(FEN.charAt(i));
            if(flag) {
                for(int b = 0; b < Character.getNumericValue(FEN.charAt(i)); b++){
                    tempBoard[r][c] = "";
                    c++;
                }
            } else if(FEN.charAt(i) == '/') {
                r--;
                c = 0;
            } else {
                tempBoard[r][c] = String.valueOf(FEN.charAt(i));
                c++;
            } 
        }
       
       //now use array to set every single bitboard
       for(r = 7; r >= 0; r--) {
           for(c = 0; c < 8; c++) {
                String Binary="0000000000000000000000000000000000000000000000000000000000000000";
                int i = r * 8 + c;
                String location=Binary.substring(i+1)+"1"+Binary.substring(0, i);
                switch(tempBoard[r][c]) {
                    case "P": 
                        WhitePawns = Long.parseLong(location, 2) | WhitePawns;
                        break;
                    case "N": 
                        WhiteKnights = Long.parseLong(location, 2) | WhiteKnights;
                        break;
                    case "B": 
                        WhiteBishops = Long.parseLong(location, 2) | WhiteBishops;
                        break;
                    case "R": 
                        WhiteRooks = Long.parseLong(location, 2) | WhiteRooks;
                        break;
                    case "Q": 
                        WhiteQueens = Long.parseLong(location, 2) | WhiteQueens;
                        break;
                    case "K": 
                        WhiteKing = Long.parseLong(location, 2) | WhiteKing;
                        break;
                    case "p": 
                        BlackPawns = Long.parseLong(location, 2) | BlackPawns;
                        break;
                    case "n": 
                        BlackKnights = Long.parseLong(location, 2) | BlackKnights;
                        break;
                    case "b": 
                        BlackBishops = Long.parseLong(location, 2) | BlackBishops;
                        break;
                    case "r": 
                        BlackRooks = Long.parseLong(location, 2) | BlackRooks;
                        break;
                    case "q": 
                        BlackQueens = Long.parseLong(location, 2) | BlackQueens;
                        break;
                    case "k": 
                        BlackKing = Long.parseLong(location, 2) | BlackKing;
                        break;
               }
           }
       }
       
    }
    
    //MODIFES: EngineBoard
    //EFFECTS: sets the combined boards
    public void setCombinedBoards() {
        WhitePieces =  WhitePawns
        | WhiteKnights
        | WhiteBishops
        | WhiteRooks
        | WhiteQueens
        | WhiteKing;
        
        BlackPieces =  BlackPawns
        | BlackKnights
        | BlackBishops
        | BlackRooks
        | BlackQueens
        | BlackKing;
    }
    
    
}
