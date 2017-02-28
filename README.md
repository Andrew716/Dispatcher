# Dispatcher

Essential libraries for the assignment are JGroups and Gson

The aim: Should be made some objects(airplane and helicopter) and only one dispatcher center,
then join them in one cluster and change their parameters in real time by adding little delta to previous
parameters.
The data about changed parameters must go to the cluster in json-message type(for working with json should be
chosen the library Gson).
The dispatcher center follows to these messages and outputs data about objects every 10 seconds.
