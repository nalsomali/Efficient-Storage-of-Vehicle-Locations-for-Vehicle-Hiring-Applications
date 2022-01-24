// Nada Alsomali | 441200865

public class BST <K extends Comparable<K>, T> implements Map <K, T> {
		  
		class BSTNode <K extends Comparable<K>,T> {
		   public K key;  
		   public T data;
		   public BSTNode<K,T> left, right;
		   public BSTNode(K key, T data) {
		     this.key = key  ;  
		     this.data = data;
		     left = right = null;
		        }
		   public BSTNode(K key, T data, BSTNode<K,T> l, BSTNode<K,T> r){
		     this.key = key  ;  
		     this.data = data;
		      left = l;
		      right = r;
		            }
		}
		BSTNode<K,T> root, current, current1;
		// default constructor 
		public BST() {
		      root = current = null;
		    }
		// Returns true if the tree is empty. Must be O(1).
			@Override
			public boolean empty() {
		   	return root == null;
			}
		// Returns true if the tree is full. Must be O(1).
			@Override
			public boolean full() {
				return false;
			}
		// Returns the data of the current element
			@Override
			public T retrieve() {
			   return current.data;

			}
		// Updates the data of current element.
			@Override
			public void update(T e) {
				current.data = e;
			}
		// Makes the element with key k the current element if it exists, and if k does 
		// not exist, the current is unchanged. The first element of the returned pair 
		// indicates whether k exists, and the second is the number of key comparisons 
		// made.
			@Override
		public Pair < Boolean , Integer > find (K key ){
		boolean flag = false;
		int numOfComparisons = 0 ; 
		if ( ! empty()){
		BSTNode<K,T> p = root;
		BSTNode<K,T>q = root;
		while(p != null) {
		q = p;
		numOfComparisons ++;
		if(p.key.equals(key)) {
		current = current1 = p;
		flag = true;
		break;
		}
		else if(key.compareTo(p.key) < 0)
		p = p.left;
		else
		p = p.right;
		}
		current1 = q;
		}

		Pair < Boolean , Integer > pair = new Pair < Boolean , Integer >(flag,  numOfComparisons);
		return pair;
		    }

		// Inserts a new element if it does not exist and makes it the current element. 
		// If the k already exists, the current does not change. The first element of 
		// the returned pair indicates whether k was inserted, and the second is the
		// number of key comparisons made.
			@Override
		 public Pair < Boolean , Integer > insert (K key , T data ){
		BSTNode<K,T> p = null;
		BSTNode<K,T> q = current;
		Pair < Boolean , Integer > pair = find (key);
		boolean flag = pair.first;
		if (flag == false) {
		p = new BSTNode<K,T>(key, data);
		 if (empty()) 
		 root = current = current1 = p;
		 else {
		if (key.compareTo(current1.key) < 0)
		    current1.left = p;
		else
		    current1.right = p;
		current = current1 = p;
		 }
		 flag = true;
		}
		else {
		    current = q;
		    flag= false;
		}
		pair.first = flag; 
		return pair;
		}

		// Removes the element with key k if it exists. The position of current is
		// unspecified after calling this method.The first element of the returned pair 
		// indicates whether k was removed, and the second is the number of key
		// comparisons made.
		@Override
		    public Pair<Boolean, Integer> remove(K key) {
		    BSTNode<K,T> p = root;
		    BSTNode<K,T> q = null; 
		    K key1 = key;
		    int numOfComparisons = 0 ; 
		      while (p != null) {
		          numOfComparisons++;
		         if (key1.compareTo(p.key) <   0) {
		     q = p;
		    p = p.left;
		         } 
		    else if (key1.compareTo(p.key) > 0) {    
		         q = p;
		         p = p.right;
		      }
		else  { 
		if ((p.left != null) && (p.right != null)) { 
		               
		  BSTNode<K,T> minimum = p.right;
		    q = p;
		    while (minimum.left != null) {
		      q = minimum;
		      minimum = minimum.left;
		        }
		       p.key = minimum.key;
		       p.data = minimum.data;
		       key1 = minimum.key;
		       p = minimum;
		     }

		if (p.left != null) { 
		         p = p.left;
		     } 
		     else { 
		         p = p.right;
		     }

		     if (q == null){ 
		         root = p;
		     } 
		     else {
		        if (key1.compareTo(q.key) < 0) 
		            q.left = p;
		        else 
		            q.right = p;
		    }
		    current = root;
		return new Pair <Boolean, Integer>(true,numOfComparisons) ;
		       } 
		  }
		return new Pair <Boolean, Integer> (false,numOfComparisons) ;
		}
		// Returns all keys of the map as a list sorted in increasing order.
			@Override
			public List<K> getAll() {
		        List <K > listOfKeys = new LinkedList<K> ();
		        inorder (root, listOfKeys); 
		        return listOfKeys;
		    }
		    private void inorder (BSTNode<K,T> p, List <K> listOfKeys) 
		    {
		        if ( p == null )
		            return;
		        else
		        {
		            inorder (p.left ,listOfKeys); 
		            listOfKeys.insert(p.key);
		            inorder (p.right ,listOfKeys); 
		        }
		    }
}


