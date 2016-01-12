(defproject twitterbot "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.7.0"][twitter-api "0.7.8"][clj-http-lite "0.3.0"]]
  :main ^:skip-aot twitterbot.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
