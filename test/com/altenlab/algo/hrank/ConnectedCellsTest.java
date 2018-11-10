package com.altenlab.algo.hrank;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class ConnectedCellsTest {
    @Test
    void markConnected() {
    }

    @Test
    void connectedCell() {
        int[][] matrix = {
                { 1, 1, 0, 0 },
                { 0, 1, 1, 0 },
                { 0, 0, 1, 0 },
                { 1, 0, 0, 0 }
        };
        final int maxSquare = ConnectedCells.connectedCell(matrix);
        assertEquals(5, maxSquare);
    }

    @Test
    void connectedCell2() {
        int[][] matrix = {
                { 0, 0, 0},
                { 0, 0, 1 },
                { 0, 1, 1 },
                { 1, 0, 1 }
        };
        final int maxSquare = ConnectedCells.connectedCell(matrix);
        assertEquals(5, maxSquare);
    }

    @Test
    void connectedCell3() {
        int[][] matrix = {
                { 0, 1, 0, 1 },
                { 1, 1, 0, 1 },
                { 0, 0, 0, 1 },
                { 1, 1, 1, 1 }
        };
        final int maxSquare = ConnectedCells.connectedCell(matrix);
        assertEquals(7, maxSquare);
    }

    @Test
    void connectedCell4() {
        int[][] matrix = {
                { 0, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 1, 0, 0, 1 },
                { 1, 1, 1, 1 }
        };
        final int maxSquare = ConnectedCells.connectedCell(matrix);
        assertEquals(9, maxSquare);
    }

}