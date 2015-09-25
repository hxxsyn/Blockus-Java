package comp1140.ass2.Game;

/**
 * Created by ***REMOVED*** on 19/08/15.
 */
public class Coordinate {
    private int x;
    private int y;

    public Coordinate (int x, int y) {
        this.x = x;
        this.y = y;
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public Coordinate shiftCoordinate(Coordinate shift) {
        return new Coordinate (x + shift.x, y + shift.y);
    }

    /*
       flips the coordinate across the y=0 axis
     */
    public Coordinate flipCoordinate() {
        return new Coordinate (-x, y);
    }

    /*
      rotates the coordinate clockwise about (0,0)
     */
    public Coordinate rotateCoordinate() {
        return new Coordinate (-y, x);
    }

    public Boolean equals(Coordinate o) {
        return (x == o.x && y == o.y);
    }

    public Coordinate[] getSideCells() {
        int count = 4;
        if(x==0 || x==19) count--;
        if(y==0 || y==19) count--;
        Coordinate[] sideCells = new Coordinate[count];
        if(x>0) sideCells[--count] = new Coordinate(x-1,y);
        if(x<19) sideCells[--count] = new Coordinate(x+1,y);
        if(y>0) sideCells[--count] = new Coordinate(x,y-1);
        if(y<19) sideCells[--count] = new Coordinate(x,y+1);
        return sideCells;
    }

    public Coordinate[] getDiagonalCells() {
        int count = 4;
        if(x==0 || x==19) count-=2;
        if(y==0 || y==19) count-=2;
        if(count==0) count+=2;
        Coordinate[] sideCells = new Coordinate[count];
        if((x>0 && y>0) || (x==0 && y==0)) sideCells[--count] = new Coordinate(x-1,y-1);
        if((x<19 && y<19) || (x==19 && y==19)) sideCells[--count] = new Coordinate(x+1,y+1);
        if((x>0 && y<19) || (x==0 && y==19)) sideCells[--count] = new Coordinate(x-1,y+1);
        if((x<19 && y>0) || (x==19 && y==0)) sideCells[--count] = new Coordinate(x+1,y-1);
        return sideCells;
    }

    @Override
    public String toString() {
        return "(" + x + "," + y + ")";
    }
}