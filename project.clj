(defproject outerpace "0.1.0-SNAPSHOT"
  :description "Web Application using Open Notify API"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [compojure "1.5.2"]                        ;routing
                 [clj-http "2.3.0"]                         ;http lib
                 [cheshire "5.7.0"]                         ;json encoding/decoding
                 [ring/ring-core "1.6.0-RC1"]                       ;http server abstraction
                 [hiccup "1.0.5"]]                          ;representing HTML in clojure
  :plugins [[lein-ring "0.11.0"]]
  :main ^:skip-aot outerspace.core
  :ring {:handler outerspace.web/handler}
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
