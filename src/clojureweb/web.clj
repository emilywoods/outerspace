(ns clojureweb.web
  (:require [clojureweb.core :as api]
            [compojure.core :refer :all]
            [compojure.handler :refer [site]]
            [hiccup.core :as hiccup]
            [hiccup.page :as page]
            [hiccup.form :as form]))


(defn layout
  [title & content]
  (page/html5
    [:head
     [:title title]
     (page/include-css "//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap.min.css")
     (page/include-css "//netdna.bootstrapcdn.com/bootstrap/3.1.0/css/bootstrap-theme.min.css")
     [:meta {:name "viewport" :content "width=device-width, initial-scale=1"}]
     [:body
      [:nav.navbar.navbar-default {:role "navigation"}
       [:div.container-fluid
        [:div.navbar-header
         [:a.navbar-brand {:href "/"} "Outer Space"]]]]]
     [:div.container
      content]
     ]))

(defn ordered-list
  [coll]
  (str coll))

(defn iss-str
  [iss-coords]
  (str iss-coords ))

(defn astr-str
  [astro-map]
  (str (get-in astro-map [:metadata 1])))

(defn homepage []
  (let [iss-vec (api/get-long-and-lat(api/get-api "/iss-now.json" {:page 1}))
        astro-map (api/get-api "/astros.json" {:page 1})]

    (layout "Hello"
            [:h1 "Hello again world"]
            [:div.row
             [:div.form-group.col-md-6
              [:p "Current coordinates of ISS:"]
              (iss-str iss-vec)
              [:p "People currently in space:"]
              (astr-str astro-map)
              ]])))


(defroutes main-routes
           (GET "/" [] (homepage))
           (GET "/astronauts" [] "Hello again, world"))

(def handler (site main-routes))
