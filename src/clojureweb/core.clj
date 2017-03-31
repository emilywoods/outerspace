(ns clojureweb.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(defn get-api
  [path params]
  (let [url (str "https://share.osf.io/api/v2/search/" path)
        query-params (merge params {:format "json" :per_page 10})
        response (json/parse-string (:body (client/get url {:query-params query-params})) true)
        metadata (first response)
        results (second response) ]
    {:metadata metadata
     :results results}))

;(defn get-contributor-and-description
;  [get-api "creativeworks/_search" {:page 1}]
;  (select-keys {} [:description :contributor]))
;

(defn -main
  [& args]
  (println (get-api "creativeworks/_search" {:page 1}))
  )
