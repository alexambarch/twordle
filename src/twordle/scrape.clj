(ns twordle.scrape
  (:require [clojure.data.json :as json]
            [twordle.twitter.search :refer [search-recent-tweets]]
            [clojure.string :as str]))

;; Map words to counts
(def dictionary (str/split-lines (slurp "dictionary.txt")))

(def previous-day-params {:query "wordle"
                          :start_time (->
                                      (java.time.OffsetDateTime/now)
                                      (.minusHours 24))
                          :max_results 100})

(defn get-next-token
  [json]
  (get-in json [:meta :next_token]))

;; Look away from my shame
(defn scrape-wordle-tweets
  "Search tweets from the last 24 hours containing 'wordle'"
  []
  (loop [tweet-json (json/read-str
                     (:body (search-recent-tweets previous-day-params))
                     :key-fn keyword)
         tweets (map :text (:data tweet-json))]
    (if (or (<= 1000 (count tweets))
            (not (get-next-token tweet-json))) tweets
        (let [next-token (get-next-token tweet-json)
              next-page (json/read-str
                         (:body (search-recent-tweets
                                 (assoc previous-day-params
                                        :next_token next-token)))
                         :key-fn keyword)]
          (recur next-page
                 (apply (partial conj tweets) (map :text (:data next-page))))))))

(defn get-words-from-tweets
  ([] (get-words-from-tweets (scrape-wordle-tweets)))
  ([tweets]
   (for [tweet tweets
         word (str/split tweet #"[\s\p{Punct}]") :when (= 5 (count word))]
     word)))
