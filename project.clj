(defproject twordle "0.1.0"
  :description "Solve today's Wordle using the power of Twitter (kinda)"
  :url "https://github.com/alexambarch/twordle"
  :license {:name "MIT License"
            :url "https://mit-license.org"}
  :dependencies [[org.clojure/clojure "1.10.3"]
                 [clj-http "3.12.3"]
                 [org.clojure/data.json "2.4.0"]]
  :main ^:skip-aot twordle.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
