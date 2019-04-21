/**
 * Jason Li
 * Ms. Krasteva
 * 2019/04/15
 * Solution of problem 4. This is a program to see whether a maze can be solved.
 */
public class Maze {
  
  //Checks if a coordinate is valid (it's a path and it's inside the maze)
  static boolean validPath(int maze[][], int x, int y) 
    { 
        return (x >= 0 && x < 5 && y >= 0 && y < 5 && maze[x][y] == 0); 
    } 
   //Solves a maze and returns whether or not it can be solved.
   private static boolean solveMaze(int maze[][], int x, int y) 
    { 
        // if the exit has been reached
        if (x == 4 && y == 4) 
            return true; 
  
        // Check if maze[x][y] is valid 
        if (validPath(maze, x, y)) 
        { 
            // Move forward in x direction
            if ( solveMaze(maze, x + 1, y)) 
                return true; 
            // Move down in y direction 
            if (solveMaze(maze, x, y + 1)) 
                return true; 
             return false; 
        } 
        return false;
    } 
  //main method to test solveMaze
  public static void main(String[] args) { 
    //this is the maze with 0 representing paths and 1s representing walls
    int [][] maze =   {{1, 0, 1, 1, 1},
                       {1, 0, 0, 0, 1},
                       {1, 0 ,1, 0, 1},
                       {1, 0, 1, 0, 1},
                       {1, 1, 1, 0, 1}};
    boolean solved = false;
    //finds the entrance
    for(int x = 0; x < 5; x++){
      if(maze[0][x] == 0){
        if(solveMaze(maze, 0, x))
             solved = true;
      }
    }
      for(int x = 0; x < 5; x++){
    if(maze[x][0] == 0){
      if(solveMaze(maze, x,0))
             solved = true;
    }
  }
    
    //prints the result
    System.out.println("Can this maze be solved? " + solved);
    
  }
  
}
