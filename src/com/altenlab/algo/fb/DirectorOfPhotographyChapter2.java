package com.altenlab.algo.fb;
//https://www.metacareers.com/profile/coding_puzzles?puzzle=156565259776376

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import java.util.function.Consumer;

/// Note: Chapter 1 is an easier version of this puzzle. The only difference is a smaller constraint on N.
///  A photography set consists of N cells in a row, numbered from 1 to N in order, and can be represented by a string C of length N.
/// Each cell i is one of the following types (indicated by Ci, the i-th character of C):
///
///     If Ci = “P”, it is allowed to contain a photographer
///     If Ci = “A”, it is allowed to contain an actor
///     If Ci = “B”, it is allowed to contain a backdrop
///     If Ci = “.”, it must be left empty
///
/// A photograph consists of a photographer, an actor, and a backdrop, such that each of them is placed in a valid cell, and such that the actor is between the photographer and the backdrop. Such a photograph is considered artistic if the distance between the photographer and the actor is between XX and YY cells (inclusive), and the distance between the actor and the backdrop is also between XX and YY cells (inclusive). The distance between cells ii and jj is ∣i−j∣∣i−j∣ (the absolute value of the difference between their indices).
/// Determine the number of different artistic photographs which could potentially be taken at the set. Two photographs are considered different if they involve a different photographer cell, actor cell, and/or backdrop cell.
/// Constraints
/// 1≤N≤300,000
/// 1≤X≤Y≤N
/// Sample test case #1
///
/// N = 5
/// C = APABA
/// X = 1
/// Y = 2
///
/// Expected Return Value = 1
///
/// Sample test case #2
///
/// N = 5
/// C = APABA
/// X = 2
/// Y = 3
///
/// Expected Return Value = 0
///
/// Sample test case #3
///
/// N = 8
/// C = .PBAAP.B
/// X = 1
/// Y = 3
///
/// Expected Return Value = 3
///
/// Sample Explanation
/// In the first case, the absolute distances between photographer/actor and actor/backdrop must be between 11 and 22. The only possible photograph that can be taken is with the 33 middle cells, and it happens to be artistic.
/// In the second case, the only possible photograph is again taken with the 33 middle cells. However, as the distance requirement is between 22 and 33, it is not possible to take an artistic photograph.
/// In the third case, there are 44 possible photographs, illustrated as follows:
/// .P.A...B
/// .P..A..B
/// ..BA.P..
/// ..B.AP..
/// All are artistic except the first, where the actor and backdrop exceed the maximum distance of 33.
public class DirectorOfPhotographyChapter2 {
    private static int alignToBound(final int i, final int N) {
        return Math.max(0, Math.min(i, N));
    }

    public static long getArtisticPhotographCount(int N, String C, int X, int Y) {
        final long[] photographerCumSum = new long[N + 1];
        final long[] backdropCumSum = new long[N + 1];
        // build aux arrays of cumulative count
        for( int i = 0; i < C.length(); ++i ) {
            final char ch = C.charAt(i);
            photographerCumSum[i + 1] = (ch == 'P' ? 1 : 0) + photographerCumSum[i];
            backdropCumSum[i + 1] = (ch == 'B' ? 1 : 0) + backdropCumSum[i];
        }
        long artisticCount = 0;
        for( int i = 0; i < C.length(); ++i ) {
            final char ch = C.charAt(i);
            if( ch != 'A' ) {
                continue;
            }
            int[] leftWindow = new int[] {alignToBound(i - Y, N), alignToBound(i - X + 1, N)};
            int[] rightWindow = new int[] {alignToBound(i + X, N), alignToBound(i + Y + 1, N)};

            final long photographersToTheLeft = photographerCumSum[leftWindow[1]] - photographerCumSum[leftWindow[0]];
            final long backdropToTheRight = backdropCumSum[rightWindow[1]] - backdropCumSum[rightWindow[0]];

            final long photographersToTheRight = photographerCumSum[rightWindow[1]] - photographerCumSum[rightWindow[0]];
            final long backdropToTheLeft = backdropCumSum[leftWindow[1]] - backdropCumSum[leftWindow[0]];

            artisticCount += backdropToTheRight * photographersToTheLeft + backdropToTheLeft * photographersToTheRight;
        }
        return artisticCount;
    }
}
