# Training on functional programming with Java

The goal of this training is to experiment the difference between 2 programming style : Imperative programming and Functional programming.

## Pre-requisites :
 - Java 17+
 - Maven 3+

## Description
On a simple example of [coaches](src/main/java/fr/equalityit/training/degree/model/Coach.java) obtaining [degrees](src/main/java/fr/equalityit/training/degree/model/Degree.java), we want to compute a summary of the degrees of a coach :
the [CoachSummary](src/main/java/fr/equalityit/training/degree/model/summary/CoachSummary.java).

 - The [CoachSummary](src/main/java/fr/equalityit/training/degree/model/summary/CoachSummary.java) is the aggregation of the [CoachDegreeSummary](src/main/java/fr/equalityit/training/degree/model/summary/CoachDegreeSummary.java).
 - The [CoachDegreeSummary](src/main/java/fr/equalityit/training/degree/model/summary/CoachDegreeSummary.java) is the summary of a [Degree](src/main/java/fr/equalityit/training/degree/model/Degree.java) for a [Coach](src/main/java/fr/equalityit/training/degree/model/Coach.java), it is composed of :
   - The [Degree](src/main/java/fr/equalityit/training/degree/model/Degree.java)
   - Its success date
   - Its last revision date.
 - A [Degree](src/main/java/fr/equalityit/training/degree/model/Degree.java) can be obtained many times by a [Coach](src/main/java/fr/equalityit/training/degree/model/Coach.java) :
   - The first time, a coach obtains a degree, its type is [SUCCESS](src/main/java/fr/equalityit/training/degree/model/DegreeType.java)
   - The other times, its type is [REVISION](src/main/java/fr/equalityit/training/degree/model/DegreeType.java)

## Training
On this training, you will find inside the test class [DegreeServiceTest](src/test/java/fr/equalityit/training/degree/service/DegreeServiceTest.java) :
 - A dataset of 2 coaches who got many degrees.
 - An assertion method that will verify the transformation of this dataset into the target [CoachSummary](src/main/java/fr/equalityit/training/degree/model/summary/CoachSummary.java).
 - 3 test methods that call [DegreeService](src/main/java/fr/equalityit/training/degree/service/DegreeService.java) methods to implement the algorithm :
   - With imperative programming
   - With functional programming
   - With Functional programming using Java Collectors

The tests will fail until the 3 algorithms have been implemented.