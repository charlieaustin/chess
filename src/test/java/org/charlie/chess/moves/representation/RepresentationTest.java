package org.charlie.chess.moves.representation;

import org.charlie.chess.Square;
import org.junit.Test;

import static org.junit.Assert.assertEquals;

public class RepresentationTest {
    
    
    @Test
    public void testCorrectRepresentation() throws Exception {
        // initialize inputs

        // initialize mocks

        // initialize subject
        Representation representation = new Representation();
        
        // invoke target
        String strRepresentation = representation.getSquareRepresentation(new Square(0, 0));

        // assert
        assertEquals("a1", strRepresentation);
        // verify
    }

    @Test
    public void testCorrectRepresentation1() throws Exception {
        // initialize inputs

        // initialize mocks

        // initialize subject
        Representation representation = new Representation();

        // invoke target
        String strRepresentation = representation.getSquareRepresentation(new Square(0, 1));

        // assert
        assertEquals("b1", strRepresentation);
        // verify
    }

    @Test
    public void testCorrectRepresentation2() throws Exception {
        // initialize inputs

        // initialize mocks

        // initialize subject
        Representation representation = new Representation();

        // invoke target
        String strRepresentation = representation.getSquareRepresentation(new Square(0, 3));

        // assert
        assertEquals("d1", strRepresentation);
        // verify
    }


    @Test
    public void testCorrectRepresentation3() throws Exception {
        // initialize inputs

        // initialize mocks

        // initialize subject
        Representation representation = new Representation();

        // invoke target
        String strRepresentation = representation.getSquareRepresentation(new Square(1, 3));

        // assert
        assertEquals("d2", strRepresentation);
        // verify
    }

}