(ns twordle.twitter.search
  (:require [clj-http.client :as client]
            [twordle.auth :refer [auth-map]]))

(defn search-recent-tweets
  "Search tweets from the last 7 days for a query."
  [query-params]
  (client/get "https://api.twitter.com/2/tweets/search/recent"
              {:oauth-token (:bearer-token auth-map)
               :query-params query-params}))
