// Nada Alsomali | 441200865
public class VehicleHiringManager {
                LocatorMap<String> TreeLocatorMap ;
// default constructor
   public VehicleHiringManager() {
      TreeLocatorMap = new TreeLocatorMap <String> ();
	}
// Returns the locator map.
	public LocatorMap<String> getLocatorMap() {
		return TreeLocatorMap;
	}
// Sets the locator map.
	public void setLocatorMap(LocatorMap<String> locatorMap) {
              TreeLocatorMap = locatorMap;  
	}
                
// Inserts the vehicle id at location loc if it does not exist and returns true.
// If id already exists, the method returns false.
	public boolean addVehicle(String id, Location loc) {
    Pair<Boolean, Integer> exist = TreeLocatorMap.add(id,loc);
		return exist.first;
			}

	// Moves the vehicle id to location loc if id exists and returns true. If id not
	// exist, the method returns false.
	public boolean moveVehicle(String id, Location loc) {
		 Pair<Boolean, Integer> exist = TreeLocatorMap.move(id, loc);
	        return exist.first;
	        }

	// Removes the vehicle id if it exists and returns true. If id does not exist,
	// the method returns false.
	public boolean removeVehicle(String id) {
		 Pair<Boolean, Integer> exist = TreeLocatorMap.remove(id);
	        return exist.first; 
        }

	// Returns the location of vehicle id if it exists, null otherwise.
	public Location getVehicleLoc(String id) {
		 Pair<Location, Integer> exist = TreeLocatorMap.getLoc(id);
	        return exist.first;
	        }


	// Returns all vehicles located within a square of side 2*r centered at loc
	// (inclusive of the boundaries).
	public List<String> getVehiclesInRange(Location loc, int r) {
        Pair <List<String>,Integer> VehiclesInRange = TreeLocatorMap.getInRange(new Location (loc.x - r , loc.y - r), new Location (loc.x + r , loc.y + r));	
        return VehiclesInRange.first;
}
}
