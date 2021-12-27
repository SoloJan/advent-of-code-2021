# Advent of code 2021

Advent of code is a series of coding puzzles leading up to Christmas, you can find it here
https://adventofcode.com/2021

in here you will find all the solutions to the exercises written in java, there is a separate readme in the folder of the day
explaining the specific task 

There is a package for every day and a resource folder including the puzzle input. 

## Performance

Most problems perform well and calculate the answer almost instantly, some problems are a bit slower and take about one or two seconds 
to finish the calculation. There is one problem which does not perform well which is the second exercise of day 15 
which takes 15 seconds to finish. 

Day 15 is about finding a shortest path in a square grid of numbers. I implemented two solutions, one naive method which assumes that there is no backtracking.
So when moving from the top left, to the bottom right, you always move down or right and never up or left. This gave the right answer 
to all examples and the first puzzle, but a wrong answer to the second puzzle. The second algorithm is an implementation of Dijkstra's 
shortest path algorithm, it is always right but takes 15 seconds. I think that it is probably possible to combine the two, 
letting the quick method doing the majority of the work and then using dijkstra to focus on the area's in the map where backtracking could lead to the shortest path.   

## Unit testing

I always take the example given in the puzzle description as a unit test case, to verify that my solution works on the example. 
When problems became bigger and more complicated I also added more unit test to verify that I implemented steps in between well. 

I use jacoco to measure the coverage of testing, unit test coverage was not a goal but the tool helps me to quickly discover parts of the code
which where not touched by testing the example. 

## Quality and Readability

The goal in advent of code is to solve a problem as quick as possible. I revisited some problems later on to add some java doc explaining my reasoning if it wasn't directly clear. But you will find out commented lines of code for extra logging, or other code smells. I use SONAR as a tool to warn me for this kind of code smells, but could not be bother in this setting.  


## Day 24

The day 24 problem  is about finding the maximum and minimum solution by following a set of instructions on a 14-digit number.  
The problem itself is not too complicated but the performance is an issue just looping to all 14 digits numbers will take forever. So I was looking if I could already identify 
some of the inputs to make the brute force take less time. At the end this turned out to be that effective that I solved the whole problem 
without writing code. But I did write some code to verify that my found solution was indeed correct. Below you find my reasoning

I divided the instructions in blocks for every input, and start writing down the value of z in terms of the different input w1 to w14. The first three blocks gave
me the following information 

<pre>
block 1 => z = w1+6
block 2 => z = 26w1 +156 + 6 + w2
block 3 => z = 676w1 + 26w2 + w3 + 3 4215
</pre>

At this stage I noticed the pattern that z grew with a factor of 26 for every block. I also noticed that the instruction <pre>div z 26</pre> existed 
a total of 7 times in the instruction. So z got multiplied by 26,  14 times, and divided by 26, 7 times. To find a solution z needed to be below 26 before 
entering the last block of instruction. To make that happen I needed to make sure that at least 7 of the multiplications with 26 did not happen.

To do that I needed to manipulate the value of x, consider the fourth set of instructions below

<pre>
inp w4
mul x 0
add x z
mod x 26     
div z 26               
add x -11      
eql x w        
eql x 0       x = 0 here if it was equal to the input w in the step before, which is always a number raging from 1 - 9, if not x = 1  
mul y 0
add y 25
mul y x
add y 1
mul z y       y = 1 here if x was zero, or 26 if x was 1 before
mul y 0
add y w
add y 11
mul y x
add z y  
</pre>

If I could make sure that x was equal to the input w4 than z would not be multiplied by 26. And from the instructions above
x would be modulo  26 of z,   minus 11 at the stage where it would be compared with w4. Putting this together leads to the following equations 

<pre> 
z  = 676w1 + 26w2 + w3 + 4215
mod z = w3 +3   =>
w3+ 3 - 11 = w4 
w3 - 8 = w4 
</pre>

And because w3 and w4 can only range from 1 to 9, w3 must be equal to 9 and w4 equal to 1

Following this same process and logic I got to the following equations

<pre>
w3 - 8 = w4 
w5 + 8 = w6
w8 + 6 = w9
w10 + 5 = w11
w7 -3  = w12
w2 -1  = w13
w1 -5  = w14
</pre>

Some inputs like w3 where uniquely defined, others could fit a range, in that case I would optimize to find the  maximum / minimum solution
th. 