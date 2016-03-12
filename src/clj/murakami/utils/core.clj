(ns murakami.utils.core
  (:require [ring.util.response     :as resp]
            [camel-snake-kebab.core :refer :all]
            [clojure.walk           :refer :all]
            [criterium.core         :refer :all]
            [clojure.core.async     :refer :all]
            [cheshire.core          :refer :all])
  (:use     [org.httpkit.server]))

(defmacro async-resp
  "Generates a function that takes a request and
   asynchronously returns a response"
  [resp]
  `(fn [req#]
    (with-channel req# channel#
      (send! channel# ~resp))))

(defn json [body]
  {:body
    (generate-string
      (postwalk
        (fn [el]
          (cond
            (keyword? el) (->snake_case el)
            :else el)) body) {:pretty true})})

(defn NOT-FOUND
  "404 HTTP response"
  [body]
  (async-resp (resp/status (json body) 404)))

(defn ERROR
  "500 HTTP response"
  [body]
  (async-resp (resp/status (json body) 500)))

(defn OK
  "200 HTTP response"
  [body]
  (async-resp (resp/status (json body) 200)))
