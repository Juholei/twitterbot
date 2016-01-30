(defproject twitterbot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "MIT"
            :url "http://opensource.org/licenses/mit-license.php"}
  :dependencies [[org.clojure/clojure "1.8.0"][twitter-api "0.7.8"][clj-http "2.0.1"]]
  :main ^:skip-aot twitterbot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
