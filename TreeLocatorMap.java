// Nada Alsomali | 441200865
public class TreeLocatorMap<K extends Comparable<K>> implements LocatorMap<K> {
Map<K, Location> BST ;
Locator<K> TreeLocator ;
// default constructor
public TreeLocatorMap()
{
    BST = new BST <K, Location> () ;
    TreeLocator = new TreeLocator <K >();
}
//Returns a map with the location of every key.
@Override
public Map<K, Location> getMap() {
        return BST;
}
//Returns a locator that contains all keys.
@Override
public Locator<K> getLocator() {
        return TreeLocator;
}
//Inserts the key k at location loc if it does not exist. The first element of 
// the returned pair indicates whether k was inserted, and the second is the
//number of key comparisons made.
@Override
public Pair<Boolean, Integer> add(K k, Location loc) {
Pair<Boolean, Integer> find = BST.find(k);
if ( find.first == false){
  BST.insert(k, loc);
  TreeLocator.add(k, loc);
return new Pair < Boolean , Integer >(true, find.second);}
return new Pair < Boolean , Integer >(false, find.second);
}
//Moves the key k to location loc if k exists. The first element of 
// the returned pair indicates whether k exists, and the second is the 
// number of key comparisons made.
@Override
public Pair<Boolean, Integer> move(K k, Location loc) {
Pair<Boolean, Integer> find = BST.find(k);
Pair<Boolean, Integer> pair;
if (find.first == true){
TreeLocator.remove(k,  BST.retrieve());
TreeLocator.add(k, loc);
BST.update(loc);
      return  new Pair < Boolean , Integer >(true, find.second);
}
      return new Pair < Boolean , Integer >(false, find.second);
        
}
//The first element of the returned pair contains the location of key k if it 
// exists, null otherwise. The second element is the number of key comparisons 
// required to find k.
@Override
public Pair<Location, Integer> getLoc(K k) {
Pair<Boolean, Integer> find = BST.find(k);
if (find.first == true){
  Pair <Location, Integer>  pair = new Pair <Location, Integer> (BST.retrieve(), find.second);
    return pair;
} 
else {
    Pair <Location, Integer>  noPair = new Pair <Location, Integer> (null, find.second);
    return noPair;}

}
//Removes the element with key k if it exists. .The first element of the
//returned pair indicates whether k was removed, and the second is the number 
// of key comparisons required to find k.
@Override
public Pair<Boolean, Integer> remove(K k) {
Pair<Boolean, Integer> find = BST.find(k);
if (find.first == true){
TreeLocator.remove(k, BST.retrieve());
BST.remove(k);
return new Pair < Boolean , Integer >(true, find.second);

}
return new Pair < Boolean , Integer >(false, find.second);

}
//Returns all keys in the map sorted in increasing order.
@Override
public List<K> getAll() {
        return BST.getAll();	 
}
//The first element of the returned pair is a list of all keys located within 
// the rectangle specified by its lower left and upper right corners (inclusive 
// of the boundaries). The second element of the pair is the number of
//comparisons made.
@Override
//public Pair<List<K>, Integer> getInRange(Location lowerLeft, Location upperRight) {
//
//Pair<List<Pair<Location,List<K>>>,Integer> pairs=TreeLocator.inRange(lowerLeft,upperRight);
//if ( pairs == null)
//    return null; 
//List<K>elements= new LinkedList<K>();
//
//if(! pairs.first.empty()){
//pairs.first.findFirst();
//while(!pairs.first.last()){
//addList(elements,pairs.first.retrieve().second);
//pairs.first.findNext();}
//addList(elements,pairs.first.retrieve().second);}
//Pair<List<K>,Integer> pair = new Pair<List<K>,Integer>(elements,pairs.second);
//return pair;
//}
//// helper method
//private void addList(List<K>L1, List<K>L2){
//    if( L2.empty())
//        return;
//    L2.findFirst();
//    while(!L2.last()) {
//        if (L1.empty()) 
//           return;
//    while (!L1.last()){
//    {
//    if (L1.retrieve().compareTo(L2.retrieve()) !=0)
//    return;
//    L1.findNext();
//    }
//    if(L1.retrieve().compareTo(L2.retrieve())!=0)
//        return;}
//        L1.insert(L2.retrieve());
//        L2.findNext();}
//            if (!L1.empty()) 
// while (!L1.last()){
//    {
//    if (L1.retrieve().compareTo(L2.retrieve()) !=0)
//    return;
//    L1.findNext();
//    }
//    if(L1.retrieve().compareTo(L2.retrieve())!=0)
//        return;}
// L1.insert(L2.retrieve());
//      L2.findNext();}
//      


public Pair < List <K >, Integer > getInRange ( Location lowerLeft , Location upperRight ){
Pair < List < Pair < Location , List <K >> >, Integer > pair = TreeLocator.inRange(lowerLeft, upperRight);
 List <K > pairsOfLocation = addList(pair.first); 
Pair < List <K >, Integer > result = new Pair <List <K >, Integer >(pairsOfLocation, pair.second);
return result;
        }

private  List <K > addList (List < Pair < Location , List <K >> > L){
List < Pair < Location , List <K >> > dataList = L;
List <K > pairsOfLocation = new LinkedList <K >();
if (! dataList.empty()) {
    dataList.findFirst();
 while (!dataList.last()) {
if (!dataList.retrieve().second.empty()){
    dataList.retrieve().second.findFirst();
    while ( ! dataList.retrieve().second.last()) {
        pairsOfLocation.insert(dataList.retrieve().second.retrieve());
        dataList.retrieve().second.findNext();
    }
   pairsOfLocation.insert(dataList.retrieve().second.retrieve());
}
     //==========================================================
     dataList.findNext();
 }
 
 if (!dataList.retrieve().second.empty())
 {
  dataList.retrieve().second.findFirst();
  while ( ! dataList.retrieve().second.last())
  {
   pairsOfLocation.insert(dataList.retrieve().second.retrieve());
   dataList.retrieve().second.findNext();
  }
 
  pairsOfLocation.insert(dataList.retrieve().second.retrieve());
 }
}
return pairsOfLocation;
}
}


