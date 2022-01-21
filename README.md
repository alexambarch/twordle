# twordle
Solve today's Wordle using the power of Twitter.

# How does it work?
It pulls 10k tweets from the last 24 hours using the query "wordle", 
filters the words in those tweets down to the 5 letter words in the Wordle
dictionary, then returns the one that occurred the most frequently. 

# Does it actually solve the Wordle?
Probably not. 

People are pretty good about not spoiling it for others, meaning
that we mostly get words like "there" and "today" out of these tweets. Maybe if
there's a frustrating enough puzzle in the future, Twitter will get angry and
inadvertently cause this to work.
