package org.charlie.chess;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.runners.MockitoJUnitRunner;

import java.util.Set;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

@RunWith(MockitoJUnitRunner.class)
public class SquareTest {


    @Test
    public void testLocationsBetweenTwoAdjacentPoints() {
        Square square = new Square(0, 1);
        Square sut = new Square(0, 0);

        Set<Square> squares = sut.locationsBetween(square);

        assertEquals(0, squares.size());
    }

    @Test
    public void testLocationsBetweenTwoPointsInALineOnXAxis() {
        Square square = new Square(0, 2);
        Square sut = new Square(0, 0);

        Set<Square> squares = sut.locationsBetween(square);

        assertEquals(1, squares.size());
        assertTrue(squares.contains(new Square(0, 1)));
    }

    @Test
    public void testLocationsBetweenTwoPointsInALineOnYAxis() {
        Square square = new Square(2, 0);
        Square sut = new Square(0, 0);

        Set<Square> squares = sut.locationsBetween(square);

        assertEquals(1, squares.size());
        assertTrue(squares.contains(new Square(1, 0)));
    }

    @Test
    public void testLocationsBetweenTwoPointsDiagonal() {
        Square square = new Square(4, 4);
        Square sut = new Square(0, 0);

        Set<Square> squares = sut.locationsBetween(square);

        assertEquals(3, squares.size());
        assertTrue(squares.contains(new Square(1, 1)));
        assertTrue(squares.contains(new Square(2, 2)));
        assertTrue(squares.contains(new Square(3, 3)));
    }

    @Test
    public void testLocationsBetweenTwoPointsDiagonalOther() {
        Square square = new Square(5, 7);
        Square sut = new Square(3, 5);

        Set<Square> squares = sut.locationsBetween(square);

        assertEquals(1, squares.size());
        assertTrue(squares.contains(new Square(4, 6)));
    }


}