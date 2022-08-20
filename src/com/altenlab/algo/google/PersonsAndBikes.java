package com.altenlab.algo.google;

/**
 *  We have a concept of Google campus. It has bikes on its territory
 *  any person may use to get somewhere on campus.
 *  We represent campus as a matrix each cell of which is the space person can get to, or where the bike is.
 *  Campus space (matrix cell) can also be occupied by "wall", i.e. some space you can't move onto.
 *
 *  We can imagine following concept for the campus content:
 *      Empty -> 0
 *      Person -> 1
 *      Bike -> 2
 *      Wall -> 3
 *
 *   Empty is the cell person or bike can move to. Walls are restricted for travel. Nothing else can be there.
 *   Matrix can be rectangular.
 *
 *   Possible extention of this:
 *      only one person can get the bike. if bike is taken before the person-1 can get it... <-- didn't get that far
 */

/**
 * See also: https://www.geeksforgeeks.org/find-whether-path-two-cells-matrix/
 * See also: graph Breadth First Search traverse on graph, CoordinatesToGraphHelper class impl
 */
public class PersonsAndBikes {

    // Task 1: find shortest path from person to bike given person's and bike coordinates and campus matrix
    int getDistance(int[] person, int[] bike, int[][] campus) {
        // we will treat campus as weighted graph, where edge exist if value of the matrix=0, not exist if =1, and (exist and there is a bike or person there) =1|2
        // it is ok to move into the cell if there is a bike or person

        // confusing questions:
        // why matrix representation has to be square?
        // does it really?
        return -1;
    }
}
