(ns twordle.scrape
  (:require [clojure.data.json :as json]
            [twordle.twitter.search :refer [search-recent-tweets]]
            [clojure.string :as str]))

;; New Wordle drops at midnight UTC
(def previous-day-params {:query "wordle"
                          :start_time (->
                                      (java.time.OffsetDateTime/now)
                                      (.withHour 0)
                                      (.withMinute 0)
                                      (.withSecond 0)
                                      (.withOffsetSameLocal
                                       (java.time.ZoneOffset/UTC)))
                          :max_results 100})

(defn get-next-token
  "Get the next_token from the json response, if it exists."
  [json]
  (get-in json [:meta :next_token]))

(defn get-tweet-json
  "Return the Clojure'd json body of the API response"
  [params]
  (json/read-str (:body (search-recent-tweets params))
                 :key-fn keyword))

(defn scrape-wordle-tweets
  "Search tweets since the new Wordle dropped using the query 'wordle'"
  []
  (loop [tweet-json (get-tweet-json previous-day-params)
         tweets (map :text (:data tweet-json))]
    (if (or (<= 10000 (count tweets)) (not (get-next-token tweet-json)))
      tweets
      (let [next-token (get-next-token tweet-json)
            next-page (get-tweet-json (assoc previous-day-params
                                             :next_token next-token))]
        (recur next-page
               (apply (partial conj tweets) (map :text (:data next-page))))))))

(defn get-words-from-tweets
  "Filter tweets for Wordle words"
  ([] (get-words-from-tweets (scrape-wordle-tweets)))
  ([tweets]
   (for [tweet tweets
         word (str/split tweet #"[\s\p{Punct}]") :when (= 5 (count word))]
     (str/lower-case word))))
