(ns pynchon.core
    (:require [reagent.core      :as reagent :refer [atom]]
              [reagent.session   :as session]
              [pynchon.websocket :as ws]
              [pynchon.views     :as views]
              [secretary.core    :as secretary :include-macros true]
              [accountant.core   :as accountant]))

;; -------------------------
;; Routes

(secretary/defroute "/app" []
  (session/put! :current-page #'views/home-page)
  (session/put! :current-count #'views/current-count))

(secretary/defroute "/app/about" []
  (session/put! :current-page #'views/about-page))


;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [views/current-page] (.getElementById js/document "app")))

(defn init! []
  (accountant/configure-navigation!
    {:nav-handler
     (fn [path]
       (secretary/dispatch! path))
     :path-exists?
     (fn [path]
       (secretary/locate-route path))})
  (accountant/dispatch-current!)
  (mount-root))


;---------------------------
;; Run app

(defn start! []
  (init!)
  (ws/start-router!))

(defonce _start-once (start!))

