(ns murakami.handler
  (:require [compojure.core            :refer [defroutes]]
            [murakami.routes.home      :refer [home-routes]]            [murakami.routes.home :refer [home-routes]]
            [murakami.routes.websocket :refer [websocket-routes]]
            [noir.util.middleware      :refer [app-handler]]
            [compojure.route           :as route]
            [taoensso.timbre :as timbre
             :refer (log  trace  debug  info  warn  error  fatal  report
              logf tracef debugf infof warnf errorf fatalf reportf
              spy get-env log-env)]
            [taoensso.timbre.profiling :as profiling
             :refer (pspy pspy* profile defnp p p*)])
  (:use     [compojure.core]))

(defroutes base-routes
  (route/not-found
    {:body
     {:message "These are not the droids you are looking for . . ."}}))

(def app
  (routes
    websocket-routes
    (app-handler
      [home-routes base-routes]
      :formats [:json :edn :transit-json])))
