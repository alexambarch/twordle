(ns twordle.scrape
  (:require [clojure.data.json :as json]
            [twordle.twitter.search :refer [search-recent-tweets]]
            [clojure.string :as str]))

;; Map words to counts
(def dictionary (zipmap (map keyword (str/split-lines (slurp "dictionary.txt")))
                        (repeat 0)))

(defn get-relevant-words
  "Search recent tweets and count the occurrences of possible wordle words."
  []
  (let [tweets (json/read-str (:body (search-recent-tweets "wordle"))
                              :key-fn keyword)]
    tweets))
