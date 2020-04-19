Akka Framework:
The goal was to compare the efficiency of Akka framework in Scala, which uses the actor model to achieve concurrency, against the traditional Java threads, with the help of J-Meter. First wrote a simple Java program which used thread pool, and has a sleep of 1 second in the background for each thread. A similar thing was done in Scala using Akka framework. A separate actor for every request is defined. The http request were sent using JMeter. A counter was sent along with every request to keep track of number of users. The results were not as expected. Java threads were taking lesser time as compared to Akka framework. And in case of Akka, some requests were also not served. The possible reasons for this mishap are not proper execution of dispatchers in the system, initializing the actors only once and then reusing them for every request( like logging in once and then using that actor to serve other requests) and not using child-actors (one actor should be initialized and rest all should be child actors, this could even solve the dispatcher problem, had started it but couldn’t complete, so I have git stashed the changes and submitted a working version).