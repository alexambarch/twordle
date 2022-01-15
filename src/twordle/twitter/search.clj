(ns twordle.twitter.search
  (:require [clj-http.client :as client]
            [twordle.auth :refer [auth-map]])
  (:gen-class))

;; I'm really about to make a new Clojure Twitter API aren't I
(defn search-recent-tweets
  "Search recent tweets (7day), optionally using a next_token to get paginated
  results."
  ([query & next-token]
   (let [query-params (if (nil? next-token)
                        {:query query}
                        {:query query :next_token next-token})]
     (client/get "https://api.twitter.com/2/tweets/search/recent"
                 {:oauth-token (:bearer-token auth-map)
                  :query-params query-params}))))
