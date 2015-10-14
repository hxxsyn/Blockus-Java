package comp1140.ass2.Players;

import comp1140.ass2.Game.Board;
import comp1140.ass2.Game.Colour;
import comp1140.ass2.Game.Coordinate;
import comp1140.ass2.Game.Panel;
import comp1140.ass2.Scenes.Game;

/**
 * @author ***REMOVED*** ***REMOVED***, ***REMOVED*** on 25/09/15.
 * There's nothing here so no point in marking yet
 */
public class GreedyBot3 implements Player {

    private final Game parent;

    /**
     * Creates a new HardBot
     * @param parent the Game class
     */
    public GreedyBot3(Game parent) {
        this.parent = parent;
    }

    @Override
    public void handleClick(int x, int y) {
    }

    @Override
    public String think(String string) {
        Board board = new Board(string);
        int playerID = parent.currentPlayerId;
        Panel myPanel = parent.panels[parent.currentPlayerId];
        Colour myColour = parent.playerColours[playerID];
        String bestMove = ".";
        int bestScore = 0;
        int currentScore;


        /**
         * builds all possible moves as a string and tests the scores of all the legal moves
         */
        for(char shape = 'A'; shape<'V'; shape++) {
            for(char orientation : new char[] {'A','B','C','D','E','F','G','H'}) {
                for(char x = 'A'; x<'U'; x++) {
                    for(char y = 'A'; y<'U'; y++) {
                        String testMove = "" + shape + orientation + x + y;
                        if(board.legitimateMove(testMove)) {
                            Board testBoard = new Board(string);
                            currentScore = scoreMove (testBoard, testMove, playerID);
                            if (currentScore > bestScore) {
                                bestMove = testMove;
                                bestScore = currentScore;
                            }
                        }
                    }
                }
            }
        }

        return bestMove;
    }

    private int scoreMove(Board board, String testMove, int playerID) {
        board.placePiece(testMove);
        return 30*placedCellCount(board, playerID) + weightedBoardCoverage(board, playerID);
    }

    /**
     * Returns how many cells are currently placed on board by this player, using Board
     * @param board
     * @param playerID
     * @return
     */
    private int placedCellCount(Board board, int playerID) {
        int cellCount = 0;
        for (Colour[] cellColourList : board.getGrid()) {
            for (Colour cellColour : cellColourList) {
                if (Colour.values()[playerID] == cellColour) {
                    cellCount += 1;
                }
            }
        }
        return cellCount;
    }

    /**
     * Returns how many cells are currently unplaced on board by this player, using Board
     * @param board
     * @param playerID
     * @return
     */
    private int unplacedCellCount(Board board, int playerID) {
        return 89 - placedCellCount(board, playerID);
    }

    /**
     * Factors in available corners for future moves, as well as how evenly distributed pieces are throughout the board
     *  this would be achieved by weighting the centre tiles heavier than the outer tiles, as well as tiles further from
     *  starting position
     * @param board
     * @return
     */
    public int boardCoverage(Board board, int playerID) {
        int cornerCells = 0;
        for (int x = 0; x < board.getGrid().length; x++) {
            for (int y = 0; y < board.getGrid().length; y++) {
                Coordinate cell = new Coordinate(x,y);
                if(board.cellAt(cell) == Colour.Empty) {
                    boolean touchingSide = false;
                    for (Coordinate sideCell : cell.getSideCells()) {
                        if (board.cellAt(sideCell) == Colour.values()[playerID]) touchingSide = true;
                    }
                    boolean touchingCorner = false;
                    for (Coordinate diagonalCell : cell.getDiagonalCells()) {
                        if (board.cellAt(diagonalCell) == Colour.values()[playerID]) touchingCorner = true;
                    }
                    if (touchingCorner && !(touchingSide)) cornerCells++;
                }
            }
        }
        return cornerCells;
    }


    /**
     * Factors in available corners for future moves, as well as how evenly distributed pieces are throughout the board
     *  this would be achieved by weighting the centre tiles heavier than the outer tiles, as well as tiles further from
     *  starting position
     * @param board
     * @return
     */
    private int weightedBoardCoverage(Board board, int playerID) {
        int cornerCells = 0;
        int weightedCornerCells = 0;

        //setting the starting position
        int homeX = 0;
        int homeY = 0;
        switch (playerID) {
            case 0: homeX = 0;
                    homeY = 0;
                    break;

            case 1: homeX = 19;
                    homeY = 0;
                    break;

            case 2: homeX = 19;
                    homeY = 19;
                    break;

            case 3: homeX = 0;
                    homeY = 19;
                    break;
        }

        for (int x = 0; x < board.getGrid().length; x++) {
            for (int y = 0; y < board.getGrid().length; y++) {
                Coordinate cell = new Coordinate(x,y);
                if(board.cellAt(cell) == Colour.Empty) {
                    boolean touchingSide = false;
                    for (Coordinate sideCell : cell.getSideCells()) {
                        if (board.cellAt(sideCell) == Colour.values()[playerID]) touchingSide = true;
                    }
                    boolean touchingCorner = false;
                    for (Coordinate diagonalCell : cell.getDiagonalCells()) {
                        if (board.cellAt(diagonalCell) == Colour.values()[playerID]) touchingCorner = true;
                    }
                    if (touchingCorner && !(touchingSide)) weightedCornerCells += Math.abs(homeX - x) + Math.abs(homeY - y);
                }
            }
        }
        return weightedCornerCells/10;
    }

    @Override
    public boolean isHuman() {
        return false;
    }

    @Override
    public void pass(Game parent) {
        parent.makeMove(".");
    }
}
