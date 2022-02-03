/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine.Eval;

import static Engine.EngineValues.BISHOP;
import static Engine.EngineValues.BLACK;
import static Engine.EngineValues.EMPTY;
import static Engine.EngineValues.KING;
import static Engine.EngineValues.KNIGHT;
import static Engine.EngineValues.PAWN;
import static Engine.EngineValues.QUEEN;
import static Engine.EngineValues.ROOK;
import static Engine.EngineValues.WHITE;
import Engine.MoveGen.Bitboard;

import Engine.MoveGen.ChessBoard;
import Engine.MoveGen.MoveUtil;

public class Eval {
    //eval constants
    //pawn, knight, bishop rook, queen, king
    public final static int[] pieceValues = {100, 325, 325, 500, 1050, 40000};
    
    //using basic eval right now
    public static int boardEval(final ChessBoard board) {
        return Material(board);
    }
    
    private static int Material(final ChessBoard board) {
        return (Long.bitCount(board.pieces[WHITE][PAWN]) * pieceValues[PAWN - 1])
                        + (Long.bitCount(board.pieces[WHITE][KNIGHT]) * pieceValues[KNIGHT - 1])
                        + (Long.bitCount(board.pieces[WHITE][BISHOP]) * pieceValues[BISHOP - 1])
                        + (Long.bitCount(board.pieces[WHITE][ROOK]) * pieceValues[ROOK - 1])
                        + (Long.bitCount(board.pieces[WHITE][QUEEN]) * pieceValues[QUEEN - 1]) -
                (Long.bitCount(board.pieces[BLACK][PAWN]) * pieceValues[PAWN - 1])
                        + (Long.bitCount(board.pieces[BLACK][KNIGHT]) * pieceValues[KNIGHT - 1])
                        + (Long.bitCount(board.pieces[BLACK][BISHOP]) * pieceValues[BISHOP - 1])
                        + (Long.bitCount(board.pieces[BLACK][ROOK]) * pieceValues[ROOK - 1])
                        + (Long.bitCount(board.pieces[BLACK][QUEEN]) * pieceValues[QUEEN - 1]);
    }
    
    private static int Mobility(final ChessBoard board) {
        int mobilityScore = 0;
        //calculate mobility of each piece type
        
        //white pawn moves
         long piece = board.pieces[WHITE][PAWN] & ~board.pinnedPieces & (board.emptySpaces  >>> 8) & Bitboard.RANK_23456;
         while (piece != 0) {
            mobilityScore++;
            piece &= piece - 1;
        }

        piece = board.pieces[WHITE][PAWN] & ~board.pinnedPieces & (board.emptySpaces >>> 16) & Bitboard.RANK_2;
        while (piece != 0) {
            if ((board.emptySpaces & (Long.lowestOneBit(piece) << 8)) != 0) {
                mobilityScore++;
            }
            piece &= piece - 1;
        }
        
        //black pawn moves
        //white pawn moves
         piece = board.pieces[BLACK][PAWN] & ~board.pinnedPieces & (board.emptySpaces  >>> 8) & Bitboard.RANK_23456;
         while (piece != 0) {
            mobilityScore++;
            piece &= piece - 1;
        }

        piece = board.pieces[BLACK][PAWN] & ~board.pinnedPieces & (board.emptySpaces >>> 16) & Bitboard.RANK_2;
        while (piece != 0) {
            if ((board.emptySpaces & (Long.lowestOneBit(piece) << 8)) != 0) {
                mobilityScore++;
            }
            piece &= piece - 1;
        }
        
        //bishop moves
        
        //then mobility of black pieces
        return 0;
    }
    

   
}
