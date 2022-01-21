(ns twordle.core
  (:require [twordle.scrape :refer :all]
            [clojure.string :as str])
  (:gen-class))

(def dictionary (str/split-lines (slurp "dictionary.txt")))

(defn get-word
  "Get the key of the first entry in a sorted list of tweeted Wordle words by
  frequency"
  []
  (key (first (sort-by #(- (second %)) (select-keys
                                        (frequencies (get-words-from-tweets))
                                        dictionary)))))

(defn -main
  "Filter tweets in the last 24 hours for words of length 5, filter that for
  words in the Wordle dictionary, then return the mode."
  []
  (println (get-word)))

