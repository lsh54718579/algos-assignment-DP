/**
 * Rod Cutting
 * Author: Sihang Li
 */
public class RodCutting {
	static int q;
	
  // Do not change the parameters!
  public int rodCuttingRecur(int rodLength, int[] lengthPrices) {
	  if (rodLength==0)
		  return 0;
	  q=0;
	  for (int i=1;i<rodLength-1;i++) {
		q=Math.max(q,lengthPrices[i]+rodCuttingRecur(rodLength-i,lengthPrices));
	  }
	  return q;
  	  }


  // Do not change the parameters!
  public int rodCuttingBottomUp(int rodLength, int[] lengthPrices) {
	  int r[]=new int[rodLength+1];
	  int q;
	  r[0]=0;
	  for (int j=0;j<rodLength;j++) {
		  q=0;
		  for (int i=1;i<j;i++) {
			  q=Math.max(q, lengthPrices[i]+r[j-i]);
		  }
		  r[j]=q;
	  }
    return r[rodLength-1];
  }


  public static void main(String args[]){
      RodCutting rc = new RodCutting();

      // In your turned in copy, do not touch the below lines of code.
      // Make sure below is your only output.
      int length1 = 7;
      int[] prices1 = {1, 4, 7, 3, 19, 5, 12};
      int length2 = 14;
      int[] prices2 = {2, 5, 1, 6, 11, 15, 17, 12, 13, 9, 10, 22, 18, 26};
      int maxSell1Recur = rc.rodCuttingRecur(length1, prices1);
      int maxSell1Bottom = rc.rodCuttingBottomUp(length1, prices1);
      int maxSell2Recur = rc.rodCuttingRecur(length2, prices2);
      int maxSell2Bottom = rc.rodCuttingBottomUp(length2, prices2);
      System.out.println( maxSell1Recur+ " " + maxSell1Bottom);
      System.out.println( maxSell2Recur+ " " + maxSell2Bottom);
  }
}
