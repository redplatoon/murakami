(ns murakami.routes.home
  (:require [ring.util.response :as resp])
  (:use     [compojure.core]))

(defroutes home-routes
  (GET "/" []
    (resp/status
      {:body
        {:message
         "It's dangerous to go alone. Take this! c=[====>"}}
      200)))
