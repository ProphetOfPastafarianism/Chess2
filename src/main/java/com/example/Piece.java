package com.example;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

import javax.imageio.ImageIO;
import javax.swing.BorderFactory;

//you will need to implement two functions in this file.
public class Piece {
    private final boolean color;
    private BufferedImage img;
    
    public Piece(boolean isWhite, String img_file) {
        this.color = isWhite;
         
        try {
            if (this.img == null) {
                this.img = ImageIO.read(new File(System.getProperty("user.dir")+img_file));
            }
          } catch (IOException e) {
            System.out.println("File not found: " + e.getMessage());
          }
    }
    
    

    
    public boolean getColor() {
        return color;
    }
    
    public Image getImage() {
        return img;
    }
    
    public void draw(Graphics g, Square currentSquare) {
        int x = currentSquare.getX();
        int y = currentSquare.getY();
        
        g.drawImage(this.img, x, y, null);
    }
    
    
    // TO BE IMPLEMENTED!
    //return a list of every square that is "controlled" by this piece. A square is controlled
    //if the piece capture into it legally.
    public ArrayList<Square> getControlledSquares(Square[][] board, Square start) {
        ArrayList<Square> cm = new ArrayList<Square>();
        for (int i=-1; i+start.getCol()>=0;i--){
        Square horz=board[start.getRow()][start.getCol()+i];
          cm.add(horz);
        }
        for (int i=-1; i+start.getRow()>=0;i--){
          Square vert=board[start.getRow()+i][start.getCol()];
          if (!vert.isOccupied());
            cm.add(vert);
        }
        for (int i=1; i+start.getCol()<=7;i++){
        Square horz=board[start.getRow()][start.getCol()+i];
          cm.add(horz);
        }
        for (int i=1; i+start.getCol()<=7;i++){
        Square vert=board[start.getRow()+i][start.getCol()];
          cm.add(vert);
        }

       
     return cm;
    }
    

    //TO BE IMPLEMENTED!
    //implement the move function here
    //it's up to you how the piece moves, but at the very least the rules should be logical and it should never move off the board!
    //returns an arraylist of squares which are legal to move to
    //please note that your piece must have some sort of logic. Just being able to move to every square on the board is not
    //going to score any points.
    public ArrayList<Square> getLegalMoves(Board b, Square start){
       ArrayList<Square> moves = new ArrayList<Square>();
        //left
        for (int i=-1; i+start.getCol()>=0;i--){
          Square horz=b.getSquareArray()[start.getRow()][start.getCol()+i];
          if (!horz.isOccupied());
            moves.add(horz);
          if (horz.isOccupied()&&horz.getOccupyingPiece().getColor()!=color){
            moves.add(horz);
            break;
          }
        }
        //up
        for (int i=-1; i+start.getRow()>=0;i--){
          Square vert=b.getSquareArray()[start.getRow()+i][start.getCol()];
          if (!vert.isOccupied());
            moves.add(vert);
            if (vert.isOccupied()&&vert.getOccupyingPiece().getColor()!=color){
            moves.add(vert);
            break;
            }
        }
    
        //right
        for (int i=1; i+start.getCol()<=7;i++){
          Square horz=b.getSquareArray()[start.getRow()][start.getCol()+i];
          if (!horz.isOccupied());
            moves.add(horz);
            if (horz.isOccupied()&&horz.getOccupyingPiece().getColor()!=color){
            moves.add(horz);
            break;
            }
        }
        //down
        for (int i=1; i+start.getRow()<=7;i++){
          Square vert=b.getSquareArray()[start.getRow()+i][start.getCol()];
          if (!vert.isOccupied());
            moves.add(vert);
            if (vert.isOccupied()&&vert.getOccupyingPiece().getColor()!=color){
            moves.add(vert);
            break;
            }
        }
         
    	return moves;
    }
}