/**
 * Glass Falling
 * Author: Sihang Li
 */
public class GlassFalling {
  // Do not change the parameters!
  public int glassFallingRecur(int floors, int sheets) {
    // Fill in here and change the return
	if (floors<=1) 
    return floors;  //If floor is 1 or 0, the number of trials needed corresponds to the number of floors, i.e. 1 or 0. 
	if (sheets==1)
	return floors;  //If there is only one glass sheet, which is the worst case, we have to try all floors so just return the number of floors. 
	int numOfTrials; 
	int min=1000;	//Initialize min to be 1000 is more than enough since the maximum floor is 100. 
	  for (int i=1;i<=floors;i++) {
		  
	  /*This is the recursion part that finds the max between case 1 and case 2. 
	  Case 1: glassFallingRecur(i-1,sheets-1), glass breaks at ith floor, the number of glasses minus 1. We then need to try (i-1)th floor. 
	  Case 2: glassFallingRecur(floors-i,sheets), glass doesnt break at ith floor, the number of glasses stays the same, we then try (floor-i)floor, which is essentially (i+1)floor.
	  */ 	
		  
		numOfTrials=Math.max(glassFallingRecur(i-1,sheets-1),glassFallingRecur(floors-i,sheets)); 
		if(numOfTrials<min)
			min=numOfTrials; //We are trying to find the minimum number of trials needed so if our current numOfTrials is smaller, min gets its value. 
	  }
	  return min+1; //We return min+1 to account for the current level.
	  }

  // Optional:
  // Pick whatever parameters you want to, just make sure to return an int.
  
  /**
   * To create a memorized recusive method, I made a caller method which initializes a memo[][] 2D-array and calls the actual recursive method. 
   * @param floors
   * @param sheets
   * @return numOfTrials INT 
   */
  public int glassFallingMemoized(int floors,int sheets) {
	  int memo[][]=new int [floors+1][sheets+1];
	  for (int i=0;i<=floors;i++) 
		  for (int j=1;j<=sheets;j++)
			  memo[i][j]=-1; //All array elements are assigned -1. 
	  
	  return glassFallingMemorizedHelper(memo,floors,sheets);
  }
  
/**
 * This is the recursive method which takes in one additional parameter which is the memo[][] array
 * Most of the code is just same as the no-memo method except one additional if statement to check if the result for current floor and sheet 
 * is already in the memo[][] array to avoid repeated calculation. 
 * Also all other return statements also assign the results to the memo[][]array. 
 * @param memo
 * @param floors
 * @param sheets
 * @return memo[floors][sheets] 
 */
  private int glassFallingMemorizedHelper(int memo[][],int floors, int sheets) {
	  if (memo[floors][sheets]!=-1) 
		  return memo[floors][sheets];
	  
      	  if (floors<=1) 
		  return memo[floors][sheets]=floors;  
      
	  if (sheets==1) 
		  return memo[floors][1]=floors;  
	  
	  int numOfTrials;
	  int min=1000;
	  for (int i=1;i<=floors;i++) {
			  numOfTrials=Math.max(glassFallingMemorizedHelper(memo,i-1,sheets-1),glassFallingMemorizedHelper(memo,floors-i,sheets));
				if(numOfTrials<min)
					min=numOfTrials;
			  }
	  return memo[floors][sheets]=min+1;	
}
  


  // Do not change the parameters!
  public int glassFallingBottomUp(int floors, int sheets) {
      int [][] glassDrops = new int [sheets+1][floors+1];
      //base case 1:
      //if floors = 0 then no drops are required // OR floors = 1 then 1 drop is required
      for (int i = 1; i <=sheets ; i++) {
    	  	glassDrops[i][0] = 0;
    	  	glassDrops[i][1] = 1;
      }
      //base case 2:
      //if only one sheet is there then drops = floors
      for (int i = 1; i <=floors ; i++) {
    	  	glassDrops[1][i] = i;
      }

      for (int i = 2; i <=sheets ; i++) {
          for (int j = 2; j <=floors ; j++) {
        	  	glassDrops[i][j] = Integer.MAX_VALUE;
              int tempResult;
              for (int k = 1; k <=j ; k++) {
                  tempResult = 1 + Math.max(glassDrops[i-1][k-1], glassDrops[i][j-k]);
                  glassDrops[i][j] = Math.min(tempResult,glassDrops[i][j]);
              }
          }
      }
      // glassDrops[sheets][floors] will have the result : minimum number of drops required in worst case
      return glassDrops[sheets][floors];
  }
  


  public static void main(String args[]){
      GlassFalling gf = new GlassFalling();

      // Do not touch the below lines of code, and make sure
      // in your final turned-in copy, these are the only things printed
      int minTrials1Recur = gf.glassFallingRecur(27, 2);
      int minTrials1Bottom = gf.glassFallingBottomUp(27, 2);
      int minTrials2Memo = gf.glassFallingMemoized(100, 3);
      int minTrials2Bottom = gf.glassFallingBottomUp(100, 3);
      System.out.println(minTrials1Recur + " " + minTrials1Bottom);
      System.out.println(minTrials2Memo + " " + minTrials2Bottom);
  }
}
