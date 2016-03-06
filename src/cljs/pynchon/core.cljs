(ns pynchon.core
    (:require [reagent.core      :as reagent :refer [atom]]
              [reagent.session   :as session]
              [pynchon.websocket :as ws]
              [secretary.core    :as secretary :include-macros true]
              [accountant.core   :as accountant]))

;; -------------------------
;; Views

(defn home-page []
  [:div [:h2 "Welcome to Pynchon"]
   [:div [:a {:href "/app/about"} "go to about page"]]])

(defn about-page []
  [:div [:h2 "About Pynchon"]
   [:div [:a {:href "/app"} "go to the home page"]]])

(defn current-page []
  [:div [(session/get :current-page)]])

;; -------------------------
;; Routes

(secretary/defroute "/app" []
  (session/put! :current-page #'home-page))

(secretary/defroute "/app/about" []
  (session/put! :current-page #'about-page))

;; -------------------------
;; Initialize app

(defn mount-root []
  (reagent/render [current-page] (.getElementById js/document "app")))

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

