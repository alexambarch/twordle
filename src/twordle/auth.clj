(ns twordle.auth
  (:require [clojure.string :as str]))

(def key-file (str/split-lines (slurp "keys.txt")))

;; Mainly just so I remember which lines in the file correspond to what key
(def auth-map {:api-key (first key-file)
               :secret (second key-file)
               :bearer-token (get key-file 2)
               :access-token (get key-file 3)
               :access-token-secret (last key-file)})
