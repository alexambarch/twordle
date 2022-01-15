(ns twordle.scrape
  (:require [clojure.data.json :as json]
            [twordle.twitter.search :refer [search-recent-tweets]]))

(defn get-relevant-words
  "Search Twitter for tweets mentioning Wordle, and filter them for Wordle
  words."
  []
  (loop [tweets (json/read-str (:body (search-recent-tweets "wordle"))
                               :key-fn keyword)]))
