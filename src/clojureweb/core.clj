(ns clojureweb.core
  (:gen-class)
  (:require [clj-http.client :as client]
            [cheshire.core :as json]))

(defn get-api
  [path params]
  (let [url (str "http://api.open-notify.org" path)
        query-params (merge params {:format "json"})
        response (json/parse-string (:body (client/get url {:query-params query-params})) true)
        metadata (first response)
        results (second response) ]
    {:metadata metadata
     :results results}))

(defn get-astros                                            ;astronauts in space
  [response]
  (doseq [k (second (:metadata response))] (prn k)))

(defn get-long-and-lat                                      ;of ISS
  [response]
  (vector (get-in response [:metadata 1 :latitude]) (get-in response [:metadata 1 :longitude])))

(defn -main
  [& args]
  (println (get-astros(get-api "/astros.json" {:page 1})))
  (println (get-long-and-lat(get-api "/iss-now.json" {:page 1})))
  )