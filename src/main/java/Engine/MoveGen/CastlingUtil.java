/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine.MoveGen;

import static Engine.EngineValues.BLACK;
import static Engine.EngineValues.EMPTY;
import static Engine.EngineValues.ROOK;
import static Engine.EngineValues.WHITE;

public final class CastlingUtil {

    // 4 bits: white-king,white-queen,black-king,black-queen
    public static long getCastlingIndexes(final ChessBoard cb) {
        if (cb.castlingRights == 0) {
            return 0;
        }
        if (cb.colorToMove == WHITE) {
            switch (cb.castlingRights) {
                case 0:
                case 1:
                case 2:
                case 3:
                    return 0;
                case 4:
                case 5:
                case 6:
                case 7:
                    return Bitboard.C1;
                case 8:
                case 9:
                case 10:
                case 11:
                    return Bitboard.G1;
                case 12:
                case 13:
                case 14:
                case 15:
                    return Bitboard.C1_G1;
            }
        } else {
            switch (cb.castlingRights) {
                case 0:
                case 4:
                case 8:
                case 12:
                    return 0;
                case 1:
                case 5:
                case 9:
                case 13:
                    return Bitboard.C8;
                case 2:
                case 6:
                case 10:
                case 14:
                    return Bitboard.G8;
                case 3:
                case 7:
                case 11:
                case 15:
                    return Bitboard.C8_G8;
            }
        }
        throw new RuntimeException("Unknown castling-right: " + cb.castlingRights);
    }

    public static int getRookMovedOrAttackedCastlingRights(final int castlingRights, final int rookIndex) {
        switch (rookIndex) {
            case 0:
                return castlingRights & 7; // 0111
            case 7:
                return castlingRights & 11; // 1011
            case 56:
                return castlingRights & 13; // 1101
            case 63:
                return castlingRights & 14; // 1110
        }
        return castlingRights;
    }

    public static int getKingMovedCastlingRights(final int castlingRights, final int kingIndex) {
        switch (kingIndex) {
            case 3:
                return castlingRights & 3; // 0011
            case 59:
                return castlingRights & 12; // 1100
        }
        return castlingRights;
    }

    public static long getRookInBetweenIndex(final int castlingIndex) {
        switch (castlingIndex) {
            case 1:
                return Bitboard.F1_G1;
            case 5:
                return Bitboard.B1C1D1;
            case 57:
                return Bitboard.F8_G8;
            case 61:
                return Bitboard.B8C8D8;
        }
        throw new RuntimeException("Incorrect castling-index: " + castlingIndex);
    }

    public static void uncastleRookUpdate(final ChessBoard cb, final int kingToIndex) {
        switch (kingToIndex) {
            case 1:
                // white rook from 2 to 0
                cb.pieces[cb.colorToMoveInverse][ROOK] ^= Bitboard.F1_H1;
                cb.friendlyPieces[cb.colorToMoveInverse] ^= Bitboard.F1_H1;
                cb.pieceIndexes[2] = EMPTY;
                cb.pieceIndexes[0] = ROOK;
                return;
            case 57:
                // black rook from 58 to 56
                cb.pieces[cb.colorToMoveInverse][ROOK] ^= Bitboard.F8_H8;
                cb.friendlyPieces[cb.colorToMoveInverse] ^= Bitboard.F8_H8;
                cb.pieceIndexes[58] = EMPTY;
                cb.pieceIndexes[56] = ROOK;
                return;
            case 5:
                // white rook from 4 to 7
                cb.pieces[cb.colorToMoveInverse][ROOK] ^= Bitboard.A1_D1;
                cb.friendlyPieces[cb.colorToMoveInverse] ^= Bitboard.A1_D1;
                cb.pieceIndexes[4] = EMPTY;
                cb.pieceIndexes[7] = ROOK;
                return;
            case 61:
                // black rook from 60 to 63
                cb.pieces[cb.colorToMoveInverse][ROOK] ^= Bitboard.A8_D8;
                cb.friendlyPieces[cb.colorToMoveInverse] ^= Bitboard.A8_D8;
                cb.pieceIndexes[60] = EMPTY;
                cb.pieceIndexes[63] = ROOK;
                return;
        }
        throw new RuntimeException("Incorrect king castling to-index: " + kingToIndex);
    }

    public static void castleRookUpdateKey(final ChessBoard cb, final int kingToIndex) {
        switch (kingToIndex) {
            case 1:
                // white rook from 0 to 2
                cb.pieces[cb.colorToMove][ROOK] ^= Bitboard.F1_H1;
                cb.friendlyPieces[cb.colorToMove] ^= Bitboard.F1_H1;
                cb.pieceIndexes[0] = EMPTY;
                cb.pieceIndexes[2] = ROOK;
                cb.zobristKey ^= Zobrist.piece[0][WHITE][ROOK] ^ Zobrist.piece[2][WHITE][ROOK];
                return;
            case 57:
                // black rook from 56 to 58
                cb.pieces[cb.colorToMove][ROOK] ^= Bitboard.F8_H8;
                cb.friendlyPieces[cb.colorToMove] ^= Bitboard.F8_H8;
                cb.pieceIndexes[56] = EMPTY;
                cb.pieceIndexes[58] = ROOK;
                cb.zobristKey ^= Zobrist.piece[56][BLACK][ROOK] ^ Zobrist.piece[58][BLACK][ROOK];
                return;
            case 5:
                // white rook from 7 to 4
                cb.pieces[cb.colorToMove][ROOK] ^= Bitboard.A1_D1;
                cb.friendlyPieces[cb.colorToMove] ^= Bitboard.A1_D1;
                cb.pieceIndexes[7] = EMPTY;
                cb.pieceIndexes[4] = ROOK;
                cb.zobristKey ^= Zobrist.piece[7][WHITE][ROOK] ^ Zobrist.piece[4][WHITE][ROOK];
                return;
            case 61:
                // black rook from 63 to 60
                cb.pieces[cb.colorToMove][ROOK] ^= Bitboard.A8_D8;
                cb.friendlyPieces[cb.colorToMove] ^= Bitboard.A8_D8;
                cb.pieceIndexes[63] = EMPTY;
                cb.pieceIndexes[60] = ROOK;
                cb.zobristKey ^= Zobrist.piece[63][BLACK][ROOK] ^ Zobrist.piece[60][BLACK][ROOK];
                return;
        }
        throw new RuntimeException("Incorrect king castling to-index: " + kingToIndex);

    }

    public static boolean isValidCMove(final ChessBoard cb, final int fromIndex, final int toIndex) {
        if (cb.checkingPieces != 0) {
            return false;
        }
        if ((cb.allPieces & getRookInBetweenIndex(toIndex)) != 0) {
            return false;
        }

        long kingIndexes = ChessConstants.IN_BETWEEN[fromIndex][toIndex] | Util.POWER_LOOKUP[toIndex];
        while (kingIndexes != 0) {
            // king does not move through a checked position?
            if (CheckUtil.isInCheckIncludingKing(Long.numberOfTrailingZeros(kingIndexes), cb.colorToMove, cb.pieces[cb.colorToMoveInverse], cb.allPieces,
                    MaterialUtil.getMajorPieces(cb.materialKey, cb.colorToMoveInverse))) {
                return false;
            }
            kingIndexes &= kingIndexes - 1;
        }

        return true;
    }

}
