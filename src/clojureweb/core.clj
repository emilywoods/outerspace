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

(defn get-astros
  [response]
  (doseq [k (second (:metadata response))] (prn k)))

(defn get-long-and-lat
  [response]
  (vector (get-in response [:results 1 :latitude]) (get-in response [:results 1 :longitude])))

(defn -main
  [& args]
  (println (str "Astronauts in Space and Craft:") )
  (println (get-astros(get-api "/astros.json" {:page 1})))
  (println (str "Coordinates of ISS:\n") (get-long-and-lat(get-api "/iss-now.json" {:page 1})))
  )

