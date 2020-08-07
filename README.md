# Task 2

Glints runs a product called TalentHunt, which seeks to match jobs to candidates. Assume that this matching is conducted once daily, and at each run, there are N jobs and N candidates respectively, where N > 1.

Based on the job details, for every job, TalentHunt would rank all the candidates at every run from the most relevant to the least. Symmetrically, based on the candidate profiles, for every candidate, TalentHunt would rank all the jobs at every run from the most relevant to the least.

If N = 3, the ranking would look something like this. j<sub>i</sub> denotes a job, and c<sub>i</sub> denotes a candidate.

```
j1: c2 c1 c3
j2: c3 c1 c2
j3: c2 c3 c1

c1: j1 j2 j3
c2: j1 j3 j2
c3: j3 j2 j1
```

Write a program to help TalentHunt plug in the last gap of matching up the candidates and jobs such that:

a) all candidates are matched to a job, and vice versa, in a 1-1 relationship,

b) the matching is stable, in the sense that no job or candidate which are not matched to each other, would both be more relevant to each other than their current match. For example, it is possible for a job j1 to not be matched to a candidate c2, even though c2 is ranked higher for j1 than its current matched candidate, as long as c2 is matched to a job that is ranked higher than j1. Another example, is taking j2 and c3, which are both matched, but not to each other. Upon completion of your program, it is not possible for j2 and c3 to both be more relevant to each other (both counterparts rank higher on relevance) than their current match.

Your program should take in an input text file which specifies the ranked relevance for jobs and candidates in that order, denoted respectively as j<sub>i</sub> and c<sub>i</sub>. Each individual  first includes the job/candidate for which the ranking is intended for, followed by a colon and a space, and a space-separated ranked list of all its counterparties. A line break separates the blocks of rankings of jobs and candidates. Your program should then pint out the list of matches, separated by a line break each.

Refer to the example below for more clarity. Include clear instructions on how to run your program with the input file. We only accept either a stdin or a < redirection, and we only expect the answer to be printed out in the console, without any additional helper text.

**Sample Input File 1**

```
j1: c3 c4 c2 c1
j2: c2 c3 c1 c4
j3: c4 c2 c3 c1
j4: c4 c3 c1 c2

c1: j4 j1 j2 j3
c2: j1 j2 j4 j3
c3: j1 j3 j4 j2
c4: j1 j3 j4 j2
```

**Sample Output 1**

```
c1 j4
c2 j2
c3 j1
c4 j3
```

Explanation: These matches are all stable because there aren't any other possible pairs for which each are more relevant to each other than their respective matches here. eg. let's take a potential pair {j4, c4}. In the output, j4 is matched to c1 while c4 is matched to j3. Although for j4, c4 (ranked 1st for j4) is more relevant than its current match of c1 (ranked 3rd for j4), from c4's vantage point, its current match of j3 (ranked 2nd for c4) is more relevant than j4 (ranked 3rd for c4). Therefore, the matches of {j4, c1} and {j3, c4} are stable relative to {j4, c4}. If all the matches in the output are stable relative to all possible alternative matches, then your overall matching is considered stable.

**Sample Input File 2**

```
j1: c2 c3 c4 c1 c5
j2: c4 c3 c1 c5 c2
j3: c4 c3 c5 c1 c2
j4: c2 c3 c4 c1 c5
j5: c4 c1 c2 c5 c3

c1: j2 j5 j1 j3 j4
c2: j3 j5 j4 j2 j1
c3: j1 j4 j5 j2 j3
c4: j5 j1 j4 j3 j2
c5: j4 j3 j1 j5 j2
```

**Sample Output 2**

```
c1 j2
c2 j4
c3 j1
c4 j5
c5 j3
```

**Sample Input File 3**

```
j1: c8 c4 c2 c3 c7 c6 c1 c5
j2: c3 c5 c7 c8 c1 c6 c2 c4
j3: c5 c1 c4 c2 c6 c7 c8 c3
j4: c5 c3 c1 c6 c2 c8 c4 c7
j5: c7 c3 c4 c1 c2 c6 c5 c8
j6: c6 c5 c4 c1 c3 c2 c7 c8
j7: c8 c2 c6 c4 c1 c3 c5 c7
j8: c2 c1 c5 c4 c6 c7 c8 c3

c1: j4 j5 j1 j7 j8 j6 j3 j2
c2: j6 j4 j2 j1 j5 j3 j8 j7
c3: j6 j2 j3 j4 j8 j7 j1 j5
c4: j2 j1 j3 j5 j6 j4 j8 j7
c5: j3 j5 j8 j7 j6 j1 j2 j4
c6: j8 j5 j6 j4 j2 j7 j3 j1
c7: j5 j8 j1 j3 j6 j4 j7 j2
c8: j5 j3 j7 j8 j1 j6 j4 j2
```

**Sample Output 3**

```
c1 j4
c2 j8
c3 j2
c4 j1
c5 j3
c6 j6
c7 j5
c8 j7
```
