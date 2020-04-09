Story 3:

AC1:

1. Given a cabinet with enough capacity, when the customer give the bag to the robot, then the robot should save the bag into the cabinet for the customer and get the ticket and return the ticket to customer
2. Given a cabinet with enough capacity, when the customer give nothing to the robot, then the customer should get an error message "Please put a bag into the cabinet."

AC2:

1. Given a cabinet with enough capacity and a valid ticket, when the customer give it to the robot, the robot can use the ticket to get the bag from cabinet
2. Given a cabinet with enough capacity, when customer give no ticket to the robot, the customer should get an error message "Please insert a ticket to get your bag".
3. Given a cabinet with enough capacity and a invalid ticket, when the customer give it to the robot, then should get the error message

AC3:

1. Given a cabinet and no empty lockers left, when robot choose to save the bag into the locker, should get an error message "Insufficient empty lockers."


Story 4:

AC1:

1. Given a cabinet with two empty lockers(the capacity left from two lockers are equal), when ask the robot to save the bag, should get the ticket from the first one
2. Given a cabinet with two lockers and the first one full, when ask the robot to save the bag, should get the ticket from the second one
3. Given a cabinet with two lockers both full, when ask the robot to save the bag, should get error message "Insufficient empty lockers."
