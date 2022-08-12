package com.altenlab.algo.fb;

import java.util.*;

/***\
 *  You have a group of people with their birth years and death years.
 *  How would you find a year with the most number people alive?
 */
public class AliveStat {
    /**
     * If the person was born and died at the same year, do we need to count them?
     *    -- Yes
     *  What does it mean the year of death?
     *  When the person could be considered to decrement, the year the died or the year after it?
     *    -- After it
     */
    // People(birth-death): 2-5, 1-4, 4-6, 3-6, 1-6
//        [1] [2] [3] [4] [5] [6]
//        -----------------------
//        [ ] [1] [1] [1] [1] [ ]
//        [1] [1] [1] [1] [ ] [ ]
//        [ ] [ ] [ ] [1] [1] [1]
//        [ ] [ ] [1] [1] [1] [1]
//        [1] [1] [1] [1] [1] [1]
//        -----------------------
//        [2] [3] [4] [5] [4] [3]
//                     *

    public static class Person {
        public int birthYear;
        public int deathYear;

        public Person(int birthYear, int deathYear) {
            this.birthYear = birthYear;
            this.deathYear = deathYear;
        }
    }

    public static class StatPerson {
        int year;
        boolean isBirth;

        public StatPerson(int year, boolean isBirth) {
            this.year = year;
            this.isBirth = isBirth;
        }
    }

    // TODO: calc big O
    public static int GetTheYearOfMostPeopleAlive(List<Person> people) {
        // 1. sort birth years - these are increments
        // 2. sort death years - these are decrements
        // 3. scan merged sorted [1]&[2] and calc
        ArrayList<StatPerson> process = new ArrayList<>(people.size()*2);
        for(Person person: people) {
            process.add(new StatPerson(person.birthYear, true));
            process.add(new StatPerson(person.deathYear + 1, false));
        }
        process.sort(new Comparator<StatPerson>() {
            @Override
            public int compare(StatPerson o1, StatPerson o2) {
                return o1.year < o2.year ? -1: (
                        o1.year == o2.year ? (o1.isBirth == o2.isBirth ? 0 : (o1.isBirth ? 1 : -1) )
                                : 1
                        );
            }
        });
        int currentlyAlive = 0;
        int maxAlive = 0;
        int yearMostAlive = -1;
        for(StatPerson stat: process) {
            if( stat.isBirth ) {
                ++currentlyAlive;
                if( currentlyAlive > maxAlive ) {
                    maxAlive = currentlyAlive;
                    yearMostAlive = stat.year;
                }
            } else {
                --currentlyAlive;
            }
        }

        return yearMostAlive;
    }

    // TODO: calc big O
    public static int GetTheYearOfMostPeopleAliveHash(List<Person> people) {
        //
        // 2-5, 1-4, 4-6, 3-6, 1-6
//        [1] [2] [3] [4] [5] [6]
//        -----------------------
//        [ ] [1] [1] [1] [ ] [ ]
//        [1] [1] [1] [ ] [ ] [ ]
//        [ ] [ ] [ ] [1] [1] [ ]
//        [ ] [ ] [1] [1] [1] [ ]
//        [1] [1] [1] [1] [1] [ ]
//        -----------------------
//        [2] [3] [4] [4] [3] [0]
//                 *   *
        // 1. scan persons, add a year with a counter to a sorted hash table
        // 2. scan sorted hash table to find what year the population was highest
        TreeMap<Integer, Integer> process = new TreeMap<>();
        for(Person person: people) {
            if( !process.containsKey(person.birthYear) ) {
                process.put(person.birthYear, 1);
            } else {
                process.put(person.birthYear, process.get(person.birthYear) + 1);
            }
            int decrementYear =  person.deathYear + 1;
            if( !process.containsKey(decrementYear) ) {
                process.put(decrementYear, -1);
            } else {
                process.put(decrementYear, process.get(decrementYear) - 1);
            }
        }

        int yearMostAlive = -1;
        int maxAlive = 0;
        int currentPopulation = 0;
        for(Map.Entry<Integer, Integer> entry : process.entrySet() ) {
            int year = entry.getKey();
            int populationChange = entry.getValue();
            currentPopulation += populationChange;
            if( currentPopulation > maxAlive ) {
                maxAlive = currentPopulation;
                yearMostAlive = year;
            }

        }

        return yearMostAlive;
    }
//    FIXME: strike this out!!!
//    public static int GetTheYearOfMostPeopleAliveFBRevelation1(List<Person> people) {
//        // this map first is a year when happened a birth and may be death
//        // deaths that occurred when there are no births are unaccounted at all
//        SortedMap<Integer, Integer> peopleAliveToThisYear = new TreeMap<>();
//    }

    /** FIXME: strike this out!!!
     * This is revelation 2
     * The gist is:
     *   1. We can have an sorted hash table of size of the dates of birth
     *   2. If a person being born this year, we increment that key's value by 1
     *   3. If a person being dead this year, we check if cure
     *   3. If a person being dead this year, we decrement next key's value by 1
     *   4. If a next key's value does not exist we store its value in accumulator
     * @param people
     * @return
     */
//    public static int GetTheYearOfMostPeopleAliveFBRevelation2(List<Person> people) {
//        // this map first is a year when happened a birth and may be death
//        // deaths that occurred when there are no births are unaccounted at all
//        SortedMap<Integer, Integer> peopleAliveToThisYear = new TreeMap<>();
//    }

    // NOTE: make sense to clarify what can be valid years (year=0)
    protected static int getMinMaxBirthYear(List<Person> people, boolean isMin) {
        int year = isMin ? Integer.MAX_VALUE : 0;
        for(Person person : people) {
            if( isMin && person.birthYear < year ) {
                year = person.birthYear;
            } else if ( !isMin && person.birthYear > year ) {
                year = person.birthYear;
            }
        }
        return year;
    }

    protected static void addToDeltas(final int[] deltas, int yearIndex, int value) {
        if( deltas != null && deltas.length > 0 && yearIndex >= 0 && yearIndex < deltas.length ) {
            deltas[yearIndex] += value;
        }
    }

    protected static int[] getDeltas(List<Person> people, int minBirthYeaR, int maxBirthYeaR) {
        if( minBirthYeaR > maxBirthYeaR ) {
            throw new IllegalArgumentException("min year can not be greater than max year");
        }
        int[] deltas = new int[maxBirthYeaR - minBirthYeaR + 1];
        for (Person person : people) {
            final int yearOfBirth = person.birthYear;
            final int yearOfDeathCounts = person.deathYear + 1;
            // here I won't skip implementation of adding to the array of deltas
            addToDeltas(deltas, yearOfBirth - minBirthYeaR, 1);
            addToDeltas(deltas, yearOfDeathCounts - minBirthYeaR, -1);
        }
        return deltas;
    }

    protected static int getYearIndexWithMostPopulation(int[] deltas) {
        int currentIndex = 0;
        int maxValue = 0;
        int yearIndex = 0;
        int runningSum = 0;
        for( int delta : deltas ) {
            runningSum += delta;
            if( runningSum > maxValue ) {
                maxValue = runningSum;
                yearIndex = currentIndex;
            }
            ++currentIndex;
        }
        return yearIndex;
    }

    // TODO: calc big O
    public static int GetTheYearOfMostPeopleAliveAfterFBVideo(List<Person> people) {
        int minBirthYear = getMinMaxBirthYear(people, true);
        int maxBirthYear = getMinMaxBirthYear(people, false);

        // this is the array which indexes years of birth
        // from the minBirthYeaR to the maxBirthYeaR (inclusive)
        // where minBirthYeaR indexed as 0 going forward
        // for example, if minBirthYeaR = 2000 and maxBirthYeaR = 2003
        // the array of deltas will be returned of size 4,
        // where index 0 would correspond to year 2000 and index 3 to 2003
        int[] deltas = getDeltas(people, minBirthYear, maxBirthYear);
        int firstYearIndexWithMostPopulation = getYearIndexWithMostPopulation(deltas);
        return firstYearIndexWithMostPopulation + minBirthYear;
    }
}

