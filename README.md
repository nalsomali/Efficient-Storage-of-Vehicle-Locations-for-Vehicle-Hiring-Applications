# Efficient-Storage-of-Vehicle-Locations-for-Vehicle-Hiring-Applications
This system was a programming assignments required for data structures course in King Saud University : 
we were required to build a new data structer similar to BST, the tree contains a location of (u, v) but had 4 children :

• The nodes in the sub-tree rooted at Child 1 (shown in red in Figure 3) contain locations (x, y) satisfying: x < u and y ≤ v.
• The nodes in the sub-tree rooted at Child 2 (shown in green in Figure 3) contain locations (x,y) satisfying: x ≤ u and y > v.
• The nodes in the sub-tree rooted at Child 3 (shown in blue in Figure 3) contain locations (x, y) satisfying: x > u and y ≥ v.
• The nodes in the sub-tree rooted at Child 4 (shown in yellow in Figure 3) contain locations (x,y) satisfying: x ≥ u and y < v.
<img width="990" alt="Screen Shot 2022-01-24 at 11 20 18 PM" src="https://user-images.githubusercontent.com/86984964/150858417-639fa729-3b7a-44df-a950-389142953c68.png">

Introduction to the system : 

Vehicle hiring companies such as Uber rely on fast localization of available vehicles near potential customers. Such efficient localization requires specialized data structures, and the goal of this project is to implement and use one such data structure. 
vehicles and customers are localized using GPS devices available in mobile phones, and these devices determine the longitude and latitude coordinates as floating-point numbers. 
For simplicity the map was decided into small cells so we used integers to identify each location on the map When a customer request a ride, only vehicles within a certain range of this customer will be contacted


