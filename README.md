# sortingEfficiencies

This program showcases a custom sorting algorithm I made, called packet sort, and how it fares against other popular sorting algorithms such as merge sort and selection sort.

In the program, the user enters a value that will serve as the length of a random array to be generated, and that same array will be used against a list of sorting algorithms already implemented in the code. Each algorithm with sort the exact same random array in a single program run.
Once all the algorithms have run, the program will display the times of each sorting algorithm and rank them from best to worst in microseconds.

Packet sort is a recursive sorting algorithm with a best case efficiency of Ω(nlog(n), and was inspired by binary trees and merge sort.
This sorting algorithm consists of a modified version of merge sort to create organized packets(subarrays), followed by insertion sort to finely sort the contents of each packet.

A diagram and explanation of how this sorting algorithm works can be found in the repository as "Packet Sort Diagram.jpg"
