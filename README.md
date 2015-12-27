# Sets
Java 8 Streams implementation of the Inclusion Exclusion Principle

Counts how many integers from 1 to N are not divisible by a given Set of exclusion integers.

i.e. How many integers in {1,...,N} are not divisible by {exclusions}?

e.g. How many integers in {1,...,100} are not divisible by 2, 3 or 5?

This can be used to solve the caterpillars and leaves programming problem, where we want to count how many leaves are uneaten, given N number of leaves and a set of jumps for each caterpillar.

A powerset function to generate the powerset of a given set is also included.