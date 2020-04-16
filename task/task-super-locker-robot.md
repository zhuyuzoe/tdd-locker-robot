Save Bag With One Locker:

1. Given one locker and enough capacity left, 
   when ask the super robot to save the bag, should get the ticket
2. Given one locker and no capacity left, 
   when ask the super robot to save the bag, should get an error message "Insufficient empty lockers."
3. Given one locker and enough capacity left, 
   when the customer give nothing to the super robot to save, 
   then the customer should get an error message "Please put a bag into the cabinet."


Save Bag With Three Lockers:   

1. Given three lockers, the first is of 10% capacity left with total capacity 1000, 
                                       second is of 20% with total capacity 100 
                                       and third is of 30% with total capacity 10, 
   when ask the super robot to save the bag, should get the ticket from the third locker
2. Given three lockers, the first is of 20% capacity left with total capacity 100, 
                                       second is of 30% with total capacity 10 
                                       and third is of 30% with total capacity 10,
   when ask the super robot to save the bag, should get the ticket from the second locker
3. Given three lockers, all are of 20% capacity left with total capacity of 10, 100 and 1000 in order,
   when ask the super robot to save the bag, should get the ticket from the first locker
   
   
Get Bag From One Locker: 
   
1. Given one locker and enough capacity left,
   when give the corresponded ticket to the super robot, should get the saved bag
2. Given one locker and enough capacity left,
   when give no ticket to the super robot, should get an error message "Please insert a ticket to get your bag"
3. Given one locker and enough capacity left, 
   when give invalid ticket to the super robot, should get an error message
      
Get Bag From Three Lockers: 
   
1. Given three lockers, the first is of 20% capacity left with total capacity 100, 
                                       second is of 30% with total capacity 10 
                                       and third is of 30% with total capacity 10,
   and the super robot to save the bag successfully,
   when give the corresponded ticket to the super robot, should get the saved bag
