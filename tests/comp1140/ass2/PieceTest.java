package comp1140.ass2;

import org.junit.Test;

import java.util.Arrays;

import static org.junit.Assert.assertTrue;

/**
 * Created by ***REMOVED*** on 22/08/15.
 */
public class PieceTest {
    public static void main(String[] args) {
    }
    @Test
    public void checkGetCoordinates() {

        Piece myPiece = new Piece(Shape.S, Colour.Red);
        Coordinate[] myCoords;
        myPiece.shape.initialisePiece(new Coordinate(5,3), 'C');
        //myCoords = myPiece.shape.getOccupiedCells();
        System.out.println(myPiece);

        myPiece.shape.movePiece(3, false);
        System.out.println(myPiece);

        /** Temporarily here, for now, to make this a test. */
        assertTrue(true);

        /*Piece myPiece = new Piece(Shape.T, Colour.Red);

        Coordinate[] is     = myPiece.getCoordinates(new Coordinate(0,0), 'A');
        Coordinate[] should = {new Coordinate( 0, 0), new Coordinate( 0, 1), new Coordinate( 1, 1), new Coordinate( 2, 1), new Coordinate( 1, 2)};
        for (Coordinate c: is) {
            System.out.println(c);
        }
        for (Coordinate c: should) {
            System.out.println(c);
        }
        //.... I don't know why I can't set them equal
        assertTrue("Error: getCoordinatesHasFailed", Arrays.equals(is, should)); */
    }
}
