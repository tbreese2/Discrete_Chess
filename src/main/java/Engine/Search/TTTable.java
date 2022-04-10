/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package Engine.Search;

/**
 *
 * @author Tyler
 */
import java.util.*;
import java.lang.instrument.Instrumentation;

public class TTTable { 
    private Position hashtable[];
    private int tableSize;
    private long tableMask;
    private byte age;
    
    public TTTable (final long mb) {
        final long maxNum = (mb * 1024 * 1024) / ObjectSizeFetcher.getObjectSize(new Position());
        tableSize = 1;
        while (tableSize <= maxNum) {
            tableSize *= 2;
        }

        tableSize /= 2;
        tableMask = tableSize - 1;
        age = 0;
        hashtable = new Position[tableSize];
        voidTable();
    }
    
    public int tableSize() {
        return tableSize;
    }
    
    public void voidTable() {
        for(int i = 0; i < hashtable.length; i++) {
            hashtable[i] = new Position();
        }
    }
    
    public double percentFull() {
        double used = 0;
        
        for (int i = 0; i < 100; i++) {
            if (hashtable[i].zobrist != 0) {
                used++;
            }
        }

        return used / 100;
    }
    
    public Position transpositionTableLookup(final long key) {
        final long iKey = key & tableMask;
        return hashtable[(int)iKey];
    }
    
    public boolean transpositionTableStore(final long key, final int move, final short value, final byte depth, final byte type){
        final long iKey = key & tableMask;
        Position ent = hashtable[(int)iKey];
        final int lKey = (int)(key >> 32);
        if (ent.zobrist == 0) {
            ent.edit(lKey, move, value, depth, type, age);
            return true;
        } else {
            if (   enP->getAge() != m_currentAge
                || type == PV_NODE
                || (enP->type    != PV_NODE && enP->depth <= depth)
                || (enP->zobrist == key     && enP->depth <= depth * 2)) {
                enP->set(key, score, move, type, depth, eval);
                enP->setAge(m_currentAge);
                return true;
            }
            return false;
        }
    }
}
