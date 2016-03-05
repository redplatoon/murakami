(ns murakami.handler
  (:require [compojure.core       :refer [defroutes]]
            [murakami.routes.home :refer [home-routes]]
            [noir.util.middleware :refer [app-handler]]
            [compojure.route      :as route])
  (:use     [compojure.core]))

(defroutes base-routes
  (route/not-found
    {:body
     {:message "These are not the droids you are looking for . . ."}}))

(def app
  (routes
    (app-handler
      [home-routes base-routes]
      :formats [:json :edn :transit-json])))
