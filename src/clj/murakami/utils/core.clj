(ns murakami.utils.core
  (:require [ring.util.response     :as resp]
            [camel-snake-kebab.core :refer :all]
            [clojure.walk           :refer :all]))

(defn json [body]
  {:body
    (postwalk
      (fn [el]
        (cond
          (keyword? el) (->snake_case el)
          :else el)) body)})

(defn NOT-FOUND
  "404 HTTP response"
  [body]
  (resp/status (json body) 404))

(defn ERROR
  "500 HTTP response"
  [body]
  (resp/status (json body) 500))

(defn OK
  "200 HTTP response"
  [body]
  (resp/status (json body) 200))
