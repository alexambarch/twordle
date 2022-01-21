(ns twordle.core
  (:require [twordle.scrape :refer :all]
            [clojure.string :as str])
  (:gen-class))

(defn -main
  "Filter tweets in the last 24 hours for words of length 5, filter that for
  words in the Wordle dictionary, then return the mode."
  []
  (first
   (first
    (sort-by #(- (second %))
             (select-keys
              (frequencies
               (map str/lower-case
                    (get-words-from-tweets)))
              dictionary)))))
