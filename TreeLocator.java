// Nada Alsomali | 441200865
public class TreeLocator<T> implements Locator<T> {
 
   class LocatorNode <T> {

   public Location loc ;  
   public List <T> data ;
   public LocatorNode<T> c1, c2, c3,c4;
   
   public LocatorNode(T d, Location i ) {
    c1= null;
    c2= null;
    c3= null;
    c4= null;
    data = new LinkedList <T> ();
    data.insert(d);
    loc= i; 
        }   

       
    }
  // default constructor
 TreeLocator(){
       current = root = null; 
   }
   
   LocatorNode <T> root, current; 
   
// Inserts e at location loc and returns the number of comparisons made when 
// searching for loc.
	@Override
    public int add(T e, Location loc) {
    int numOfComparisons = 0 ; 
    if ( root == null) {
    root = current = new LocatorNode<T>(e,loc);
    return numOfComparisons;
    }
    LocatorNode<T> p =root, q= root;
            while ( p != null ){
            q = p; 
    numOfComparisons++;
     if (( loc.x  == p.loc.x ) && ( loc.y ==p.loc.y )){
          p.data.insert(e);
             return numOfComparisons;}
    else if (( loc.x  < p.loc.x ) && ( loc.y <=p.loc.y ))
               p= p.c1;
    else  if (( loc.x  <= p.loc.x) && ( loc.y > p.loc.y ))
               p= p.c2 ;
     else if (( loc.x  > p.loc.x) && ( loc.y >= p.loc.y ))
               p= p.c3 ;
     else if ( loc.x  >= p.loc.x  &&  loc.y < p.loc.y )
               p= p.c4 ;
        }
    if (( loc.x  < q.loc.x ) && ( loc.y <=q.loc.y ))
                 q.c1 = new LocatorNode<T>(e,loc);
    else if (( loc.x  <= q.loc.x) && ( loc.y > q.loc.y ))
                 q.c2 = new LocatorNode<T>(e,loc);
    else if (( loc.x  > q.loc.x) && ( loc.y >= q.loc.y ))
                 q.c3 = new LocatorNode<T>(e,loc);
    else if (( loc.x  >= q.loc.x ) && ( loc.y < q.loc.y ))
                 q.c4 = new LocatorNode<T>(e,loc);
    
        return numOfComparisons;
    } 
	
	// The first element of the returned pair is a list containing all elements
	// located at loc. If loc does not exist or has no elements, the returned list 
	// is empty. The second element of the pair is the number of comparisons made 
	// when searching for loc.
	@Override
	public Pair<List<T>, Integer> get(Location loc) {
    int numOfComparisions = 0 ; 
    LocatorNode<T> p =root;
    if(root == null){
   return new Pair < List<T>, Integer> (new LinkedList(),0);
    }
    while(p != null) {
       numOfComparisions++;
        if ((loc.x == p.loc.x ) && (loc.y == p.loc.y) ){
           return new Pair < List<T>, Integer> (p.data,numOfComparisions);
        }
    else if (( loc.x  < p.loc.x ) && ( loc.y <=p.loc.y ))
               p= p.c1;
    else if (( loc.x  <= p.loc.x) && ( loc.y > p.loc.y ))
               p= p.c2 ;
    else if (( loc.x  > p.loc.x) && ( loc.y >= p.loc.y ))
               p= p.c3 ;
    else if (( loc.x  >= p.loc.x ) && ( loc.y < p.loc.y ))
               p= p.c4 ;
} 
Pair<List<T>, Integer> pair = new Pair < List<T>, Integer> (new LinkedList(),numOfComparisions);
     return pair;
	}

	// Removes all occurrences of element e from location loc. The first element
	// of the returned pair is true if e is removed and false if loc does not exist 
	// or e does not exist in loc. The second element of the pair is the number of 
	// comparisons made when searching for loc.
	@Override
	public Pair<Boolean, Integer> remove(T e, Location loc) {
if (root == null) {
return new Pair<Boolean, Integer>(false, 0);
}
int numOfComparisions=0;
LocatorNode< T> p = root;
while (p != null) {
numOfComparisions++;
if ((loc.x == p.loc.x ) && (loc.y == p.loc.y)){
boolean flag = removeElement(p.data, e);
if(flag == true)
return new Pair<Boolean, Integer> (true, numOfComparisions);
else
return new Pair<Boolean, Integer> (false, numOfComparisions);
}
else 
    if (( loc.x  < p.loc.x ) && ( loc.y <= p.loc.y ))
               p= p.c1;
    else if (( loc.x  <= p.loc.x) && ( loc.y > p.loc.y ))
               p= p.c2 ;
    else if (( loc.x  > p.loc.x) && ( loc.y >= p.loc.y ))
               p= p.c3 ;
    else if (( loc.x  >= p.loc.x ) && ( loc.y < p.loc.y ))
               p= p.c4 ;
}  

return new Pair<Boolean, Integer> (false, numOfComparisions);
	}

	// Returns all locations and the elements they contain.
@Override
public List<Pair<Location, List<T>>> getAll() {
List<Pair<Location, List<T>>> cars = new LinkedList<Pair<Location, List<T>>>();

preorder(root,cars); 
return cars;
}
 private void preorder(LocatorNode<T> p, List<Pair<Location, List<T>>> cars){
     if (p == null)
        return;
     else{
Pair<Location, List<T>>  pair = new Pair<Location, List<T>>(p.loc, p.data);
     cars.insert(pair);           
     preorder(p.c1, cars);
     preorder(p.c2, cars);
     preorder(p.c3, cars);
     preorder(p.c4, cars);
 }
 }
	 
 
//The first element of the returned pair is a list of all locations and their
//data if they are located within the rectangle specified by its lower left and
//upper right corners (inclusive of the boundaries). The second element of the
//pair is the number of comparisons made.
    public Pair<List<Pair<Location, List<T>>>, Integer> inRange(Location lowerLeft, Location upperRight) {
             int numOfComaprisons = 0;

        Location lowerRight = new Location (upperRight.x, lowerLeft.y);
        Location upperLeft = new Location (lowerLeft.x, upperRight.y);

        List<Pair<Location, List<T>>> ListOfPairs = new LinkedList<Pair<Location, List<T>>>();
        numOfComaprisons = range(ListOfPairs, root, lowerLeft, upperRight , lowerRight, upperLeft) ;
        Pair<List<Pair<Location, List<T>>>, Integer> pairs = new Pair<List<Pair<Location, List<T>>>, Integer> (ListOfPairs, numOfComaprisons );
         return pairs;
    }
    
 private int range( List<Pair<Location, List<T>>> ListOfPairs ,  LocatorNode<T> Node , Location LL, Location UR , Location LR, Location UL){ // ll = lower left , ur = upper right , lr = lower right , ul = upper left 
if (Node==null)
    return  0;
else{
int numOfComaprisons = 1;

if (( LL.x <= Node.loc.x  && Node.loc.x <= UR.x ) && ( LL.y <= Node.loc.y  && Node.loc.y <= UR.y ))
{
    List<T> listofElements = new LinkedList <T> ();

    if ( ! Node.data.empty())
    {
        Node.data.findFirst();
        while (! Node.data.last())
        {
            listofElements.insert(Node.data.retrieve());
            Node.data.findNext();
        }
        listofElements.insert(Node.data.retrieve());

    }   
    ListOfPairs.insert(new Pair<>(Node.loc, listofElements));
    }

            //--------------------------------------------------------------------------
            
    // the conditions here checks all four corners 
   if ((( LL.x  < Node.loc.x ) && ( LL.y <= Node.loc.y )) || (( UR.x  < Node.loc.x ) && ( UR.y <= Node.loc.y )) 
   || (( LR.x  < Node.loc.x ) && ( LR.y <= Node.loc.y )) || (( UL.x  < Node.loc.x ) && ( UL.y <= Node.loc.y )))
        numOfComaprisons +=  range(ListOfPairs, Node.c1, LL, UR, LR, UL);

   if ((( LL.x  <= Node.loc.x ) && ( LL.y > Node.loc.y ))  || (( UR.x  <= Node.loc.x ) && ( UR.y > Node.loc.y ))
   || (( LR.x  <= Node.loc.x ) && ( LR.y > Node.loc.y )) || (( UL.x  <= Node.loc.x ) && ( UL.y > Node.loc.y )))
        numOfComaprisons +=  range(ListOfPairs, Node.c2, LL, UR, LR, UL);

   if ((( LL.x  > Node.loc.x ) && ( LL.y >= Node.loc.y )) || (( UR.x  > Node.loc.x ) && ( UR.y >= Node.loc.y ))
   ||(( LR.x  > Node.loc.x ) && ( LR.y >= Node.loc.y )) || (( UL.x  > Node.loc.x ) && ( UL.y >= Node.loc.y )))
        numOfComaprisons +=  range(ListOfPairs, Node.c3, LL, UR, LR, UL);

   if ((( LL.x  >= Node.loc.x ) && ( LL.y < Node.loc.y )) || (( UR.x  >= Node.loc.x ) && ( UR.y < Node.loc.y ))
  ||(( LR.x  >= Node.loc.x ) && ( LR.y < Node.loc.y ))  || (( UL.x  >= Node.loc.x ) && ( UL.y < Node.loc.y )))
        numOfComaprisons += range(ListOfPairs, Node.c4, LL, UR, LR, UL);

    return numOfComaprisons;
}
}
// helper method 
private boolean removeElement(List<T> L, T e) {// search for key in list to delete 
 boolean flag=false;
if(L.empty()) 
 return flag;
L.findFirst();
while (!L.last()){
if(L.retrieve().equals(e)){
L.remove();
flag=true;
}
else
L.findNext();
}
if (L.retrieve().equals(e)){
L.remove();
flag=true;
}
return flag;
}
 
}