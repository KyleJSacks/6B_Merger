/**
  Represent a merge operation for sorted lists,
  as described in README.md
 */
import java.util.ArrayList;
import java.util.LinkedList;

public class Merger {

    ArrayList<String> usersData;

    /**
      Construct an instance from a list of data
      part of which is to be merged. See README
     */
    public Merger( ArrayList<String> list) {
        usersData = list;
    }

    private void addToSorted(ArrayList<String> addTo, int fromPos){
			 addTo.add(usersData.get(fromPos));
			 usersData.set(fromPos, null);
    }		
    /**
      Merge the sorted sub-lists.
     */
    public void merge(
      // indexes of sub-list boundaries; see README
        int start0  // index of first item in list0
      , int start1  // index of first item in list1
                    // = just past end of list0
      , int nItems  // number of items in the merged list
                    // = just past end of list1
      ) {
		  ArrayList<String> holder = new ArrayList<String>();
		  while (holder.size() < start0) holder.add(usersData(holder.size()));
		  int limit0 = start1 - 1;
		  int limit1 = nItems - 1;
		  while (usersData.get(limit0) != null && usersData.get(limit1) != null){
			  if (usersData.get(start0).compareTo(usersData.get(start1)) <= 0) {
				  addToSorted(holder, start0);
				  start0++;
			  }
			  else if (usersData.get(start0).compareTo(usersData.get(start1)) > 0) {
				  addToSorted(holder, start1);
				  start1++;
			  }  
		  }
		  while (usersData.get(limit0) != null){
		          addToSorted(holder, start0);
			  start0++;
		  }
		  while (usersData.get(limit1) != null){
			  addToSorted(holder, start1);
			  start1++;
		  }
		  usersData = holder;
    }
	
	public static LinkedList<Integer> mergeR(LinkedList<Integer> L1, LinkedList<Integer> L2){
		return mergeR(L1, L2, new LinkedList<Integer>());
	}
	
	public static LinkedList<Integer> mergeR(LinkedList<Integer> L1, LinkedList<Integer> L2, LinkedList<Integer> Solution){
		if (L1.size() == 0){
			for(Integer i: L1){
				Solution.add(i);
			}
			return Solution;
			
		}
		if (L2.size() == 0){
			for(Integer i: L2){
				Solution.add(i);
			}
			return Solution;
		}
		if (L1.get(0) < L2.get(0)) {
			Solution.add(L1.remove(0));
		}
		else {
			Solution.add(L2.remove(0));
		}
		return mergeR(L1, L2, Solution);
	}
    /**
      @return a string representation of the user's data
     */
    public String toString() {
        return "" + usersData; 
    }

    
    /** 
      @return the boolean value of the statement
         "the data in the range are in ascending order"
     */
    public boolean isSorted( int startAt, int endBefore) {
        for( int i = startAt
           ; i < endBefore -1 // stop early, because comparing to next
           ; i++
           )
            if( usersData.get(i).compareTo( usersData.get(i+1)) > 0) return false;
        return true;
    }
}
