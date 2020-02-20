# Benchmarking Java 8 Optional Performance

### Overview

This project aims to measure, quantify, and compare differences in performance when using `java.util.Optional` to null-check small blocks of code, compared against equivalent traditional, pre-Java 8 ways to accomplish the same task.

This repo accompanies [this Medium post](https://medium.com/@markmullan/benchmarking-java-8-optional-performance-566a2c6cd51c) which goes into more detail.

ref `Examples.java` for the application code, `ExamplesTest.java` for sample unit tests and sanity-check assertions, and `ExamplesBenchmark.java` for everything performance related.

### Steps to use

1. Clone the repo <br/>
  `$ git clone https://github.com/markrmullan/optional.git`
2. `Optional`ly (get it?) tweak some of the parameters of `ExamplesBenchmark.java`, e.g., how many iterations of each function you would like to run. **Note:** running one billion iterations of a function will take over a minute. Have patience. Or decrease the number of iterations by an order of magnitude.  
3. Run the `main()` method of `ExamplesBenchmark.java`.
4. Observe output like this in the console:

```
Previous operation doOptionalChaining took 62036 millis
Previous operation doOptionalChaining resulted in 518 millis of GC
Previous operation doOptionalChaining took 60095 millis
Previous operation doOptionalChaining resulted in 542 millis of GC
Previous operation doOptionalChaining took 59221 millis
Previous operation doOptionalChaining resulted in 525 millis of GC
```
