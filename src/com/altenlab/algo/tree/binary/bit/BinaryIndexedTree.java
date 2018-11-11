package com.altenlab.algo.tree.binary.bit;

/**
 * Implementation of a Binary Indexed Tree (Fennwick Tree)
 */
public class BinaryIndexedTree {
    private int[] data;
    public BinaryIndexedTree(int[] data) {
        this.data = new int[data.length + 1];

        // this would be converted to 1 based array
        for( int i = 0; i < data.length; ++i ) {
            this.data[i + 1] = data[i];
        }

        System.out.print("idx2s[l:"+this.data.length+"]: ");
        for( int i = 1; i < this.data.length; ++i ) {
            int idx2 = i + ( i & (-i) );
            System.out.print(idx2 + " ");
            if( idx2 < this.data.length ) {
                this.data[idx2] += this.data[i];
            }
        }
        System.out.println("");
    }

    public int prefixQuery(int idx) {
        // Computes prefix sum of up to the element at index idx
        int result = 0;
        for( ++idx; idx > 0; idx -= idx & (-idx) ) {
            result += this.data[idx];
        }
        return result;
    }

    public int rangeQuery(int from, int to) {
        // Computes the range sum between two indices (both inclusive)
        if( from == 0 )
            return prefixQuery(to);
        else
            return prefixQuery(to) - prefixQuery(from - 1);
    }

    public void update(int idx, int add) {
        // Add a value to the element at index idx
        for( ++idx; idx < this.data.length; idx += idx & (-idx) ) {
            this.data[idx] += add;
        }
    }

    public void dump(int length) {
        System.out.print("index: ");
        for( int idx = 0; idx < length + 1 && idx < this.data.length; ++idx ) {
            System.out.print(idx + " ");
        }
        System.out.println("");
        System.out.print(" Data:   ");
        for( int idx = 1; idx < length + 1 && idx < this.data.length; ++idx ) {
            System.out.print(this.data[idx] + " ");
        }
        System.out.println("");
        System.out.print("Array:   ");
        for( int idx = 0; idx < length && idx < this.data.length; ++idx ) {
            System.out.print(rangeQuery(idx, idx) + " ");
        }
        System.out.println("");

//        std::cout << "Range sum from index 1 to index 5: \t" << bit.range_query(1, 5) << std::endl;
//        std::cout << std::endl;
    }
}
