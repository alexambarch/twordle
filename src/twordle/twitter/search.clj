(ns twordle.twitter.search
  (:require [clj-http.client :as client]
            [twordle.auth :refer [auth-map]]))

;; I'm really about to make a new Clojure Twitter API aren't I
(defn search-recent-tweets
  "Search recent tweets (7day), optionally using a next_token to get paginated
  results."
  ([query-params]
     (client/get "https://api.twitter.com/2/tweets/search/recent"
                 {:oauth-token (:bearer-token auth-map)
                  :query-params query-params})))
