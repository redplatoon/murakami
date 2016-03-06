(ns pynchon.views
  (:require [reagent.session :as session]
            [reagent.core    :as reagent :refer [atom]]))

;; -------------------------
;; State

(def local-broadcast-count (atom 0))
(def total-broadcast-count (atom 0))

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


;; --------------------------
;; UI element for sample websocket broadcast

(defn current-count []
  [:h4 (str @local-broadcast-count " pushes from server.")])

(defn current-total []
  [:h4 (str "Server has pushed " @total-broadcast-count " messages in total.")])

(defn update-broadcast-count [curr-total]
  (swap! local-broadcast-count inc)
  (swap! total-broadcast-count (fn [n] curr-total))

  (session/put! :current-count #'home-page)

  ;; not sure if this is the cleanest way to do this..
  (reagent/render-component
    [current-count]
    (.getElementById js/document "count"))

  (reagent/render-component
    [current-total]
    (.getElementById js/document "total")))
