(defproject clojureweb "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]              ;routing
                 [clj-http "2.3.0"]                         ;http lib
                 [cheshire "5.7.0"]                         ;json encoding/decoding
                 [ring/ring-core "1.6.0-RC1"]               ;http server abstraction
                 [hiccup "1.0.5"]]                          ;representing HTML in clojure
  :main ^:skip-aot clojureweb.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
