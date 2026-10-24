package com.example.gameoflife;

import javafx.animation.KeyFrame;
import javafx.animation.Timeline;
import javafx.scene.layout.GridPane;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.util.Duration;

import java.util.Random;

public class Game{

    GridPane pane;
    int Rows = 50;
    int Cols = 50;
    int cellSize = 10;
    int[][] oldGrid = new int[Rows][Cols];
    int[][] newGrid = new int[Rows][Cols];
    public Game(GridPane pane){
        this.pane = pane;
    }

    public void start(){
        initGrid();

        Timeline timeline = new Timeline(new KeyFrame(Duration.millis(100), actionEvent -> evolve()));
        timeline.setCycleCount(Timeline.INDEFINITE);
        timeline.play();
    }

    private void initGrid() {
        Random random = new Random();
        for(int row = 0; row< Rows; row++){
            for(int col =0; col<Cols; col++){
                int choice = random.nextInt(2);
                Color color = choice==1? Color.WHITE : Color.BLACK;
                oldGrid[row][col] = choice;
                newGrid[row][col] = choice;
                pane.add(new Rectangle(cellSize,cellSize,color),row*cellSize,col*cellSize);
            }
        }
    }

    private void evolve() {
        for(int row=0; row<Rows; row++){
            for(int col=0; col<Cols; col++){
                int neighbours = countNeighbours(row,col);
                boolean willLive = neighbours==2 || neighbours==3;
                if(oldGrid[row][col]==1){
                    newGrid[row][col] = willLive? 1:0;
                    updateColor(row,col);
                } else{
                    newGrid[row][col] = (neighbours==3)? 1:0;
                    updateColor(row,col);
                }
            }
        }
        int[][] temp = oldGrid;
        oldGrid = newGrid;
        newGrid = temp;

    }

    private void updateColor(int row, int col) {
        Color color = (newGrid[row][col]==1)? Color.WHITE : Color.BLACK;
        Rectangle cell = (Rectangle) pane.getChildren().get(row*Cols+col);
        cell.setFill(color);
    }

    private int countNeighbours(int row, int col) {
        int neighbours = 0;
        for(int i=-1; i<=1; i++){
            for(int j=-1; j<=1; j++){
                if(i==0 && j==0) continue;
                int newRow = row+i;
                int newCol = col+j;
                if(newRow>=0 && newRow<Rows && newCol>=0 && newCol<Cols){
                    neighbours+= oldGrid[newRow][newCol];
                }
            }
        }
        return neighbours;
    }
}