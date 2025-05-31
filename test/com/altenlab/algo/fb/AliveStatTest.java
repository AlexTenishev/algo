package com.altenlab.algo.fb;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

class AliveStatTest {

    @org.junit.jupiter.api.Test
    void getTheYearOfMostPeopleAlive() {
        ArrayList<AliveStat.Person> people = new ArrayList<>();
        people.add(new AliveStat.Person(2, 5));
        people.add(new AliveStat.Person(1, 4));
        people.add(new AliveStat.Person(4, 6));
        people.add(new AliveStat.Person(3, 6));
        people.add(new AliveStat.Person(1, 6));
        //2-5, 1-4, 4-6, 3-6, 1-6
        int expectedYear = 4;
        int gotYear = AliveStat.GetTheYearOfMostPeopleAlive(people);
        assertEquals(expectedYear, gotYear);
    }

    @org.junit.jupiter.api.Test
    void getTheYearOfMostPeopleAliveHash() {
        ArrayList<AliveStat.Person> people = new ArrayList<>();
        people.add(new AliveStat.Person(2, 5));
        people.add(new AliveStat.Person(1, 4));
        people.add(new AliveStat.Person(4, 6));
        people.add(new AliveStat.Person(3, 6));
        people.add(new AliveStat.Person(1, 6));
        //2-5, 1-4, 4-6, 3-6, 1-6
        int expectedYear = 4;
        int gotYear = AliveStat.GetTheYearOfMostPeopleAliveHash(people);
        assertEquals(expectedYear, gotYear);
    }

    @org.junit.jupiter.api.Test
    void getTheYearOfMostPeopleAliveAfterFBVideo1() {
        ArrayList<AliveStat.Person> people = new ArrayList<>();
        people.add(new AliveStat.Person(2000, 2010));
        people.add(new AliveStat.Person(1975, 2005));
        people.add(new AliveStat.Person(1975, 2003));
        people.add(new AliveStat.Person(1803, 1809));
        people.add(new AliveStat.Person(1750, 1869));
        people.add(new AliveStat.Person(1840, 1935));
        people.add(new AliveStat.Person(1803, 1921));
        people.add(new AliveStat.Person(1894, 1921));

        int expectedYear = 1803;
        int gotYear = AliveStat.GetTheYearOfMostPeopleAliveAfterFBVideo(people);
        assertEquals(expectedYear, gotYear);
    }

    @org.junit.jupiter.api.Test
    void getTheYearOfMostPeopleAliveAfterFBVideo2() {
        ArrayList<AliveStat.Person> people = new ArrayList<>();
        people.add(new AliveStat.Person(2, 5));
        people.add(new AliveStat.Person(1, 4));
        people.add(new AliveStat.Person(4, 6));
        people.add(new AliveStat.Person(3, 6));
        people.add(new AliveStat.Person(1, 6));
        //2-5, 1-4, 4-6, 3-6, 1-6
        int expectedYear = 4;
        int gotYear = AliveStat.GetTheYearOfMostPeopleAliveAfterFBVideo(people);
        assertEquals(expectedYear, gotYear);
    }
}