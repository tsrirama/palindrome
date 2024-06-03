Palindrome Service

- Please note that this service can check if the given string is a palindrome
- This version of the service only accpets string without spaces and numbers

How to Run

- Please run the Main class com.srirama.palindrome.MyPalindromeApp to start the spring boot application

Send a Request

- To call the rest service through the browser please use the following request format

	http://localhost:8080/v1/palindrome/check/<username>/<input_value>

Data Store

- The applilcation uses a file data store to store the historical attempts of palindrome checks , - which can be changed with a new implementation to use a different persistent store in future

- The data from the persistent store is loaded in to an inmemeory cache on styart up of service

- The application uses a non blocking asynchronous service to write to the persistent store

Cache

- Cache is loaded from the persistent store on start up.

- Each request is checked against the cache first

- The cache is updated for each new value




