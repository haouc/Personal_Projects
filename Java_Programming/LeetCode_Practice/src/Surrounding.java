/**
 * Created by haozhou on 7/4/15.
 */
import java.util.*;
public class Surrounding {
    public void solve(char[][] board) {
        //find all 'O' on four borders.
        int row = board.length;
        int column = board[0].length;
        ArrayList<int[]> borders = new ArrayList<>();


        for(int i = 0; i < column; i++){
            if(board[0][i] == 'O') borders.add(new int[]{0, i});
            if(board[row-1][i] == 'O') borders.add(new int[]{row-1, i});
        }
        for(int j = 0; j < row; j++){
            if(board[j][0] == 'O') borders.add(new int[]{j, 0});
            if(board[j][column-1] == 'O') borders.add(new int[]{j, column-1});
        }

        for(int i = 0; i < borders.size(); i++){
            board = bfs(board, borders.get(i));
        }

        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(board[i][j] == 'O') board[i][j] = 'X';
            }
        }
        for(int i = 0; i < row; i++){
            for(int j = 0; j < column; j++){
                if(board[i][j] == 'C') board[i][j] = 'O';
            }
        }
    }
    private char[][] bfs(char[][] board, int[] root){
        int row = board.length;
        int column = board[0].length;
        List<int[]> ol = new ArrayList<>();
        if(ol.isEmpty()) ol.add(0, root);
        while(!ol.isEmpty()){
            int[] peek = ol.get(0);
//            int[] nUp = (peek[0]-1 < 0)?peek:board[peek[0]-1][peek[1]];
//            int[] nRight = (peek[1]+1 > column-1)?peek:board[peek[0]][peek[1]+1];
//            int[] nDown = (peek[0]+1 > row -1)?peek:board[peek[0]+1][peek[1]];
//            int[] nLeft = (peek[1]-1 < 0)?peek:board[peek[0]][peek[1]-1];
            int[] nUp = (peek[0]-1 < 0)?peek:new int[]{peek[0]-1, peek[1]};
            int[] nRight = (peek[1]+1 > column-1)?peek:new int[]{peek[0], peek[1]+1};
            int[] nDown = (peek[0]+1 > row -1)?peek:new int[]{peek[0]+1, peek[1]};
            int[] nLeft = (peek[1]-1 < 0)?peek:new int[]{peek[0], peek[1]-1};
            if(board[peek[0]][peek[1]] == 'O'){
                if(board[nUp[0]][nUp[1]] == 'O' && !ol.contains(nUp)) {ol.add(nUp);}
                if(board[nRight[0]][nRight[1]] == 'O' && !ol.contains(nRight)) {ol.add(nRight);}
                if(board[nDown[0]][nDown[1]] == 'O' && !ol.contains(nDown)) {ol.add(nDown);}
                if(board[nLeft[0]][nLeft[1]] == 'O' && !ol.contains(nLeft)) {ol.add(nLeft);}
            }
            ol.remove(0);
            board[peek[0]][peek[1]] = 'C';
        }
        return board;
    }
}
