(ns murakami.routes.websocket
  (:require [ring.util.response :as resp]
            [taoensso.sente :as sente])
  (:use     [murakami.services.websocket]
            [compojure.core]))

(defroutes websocket-routes
  (GET  "/chsk" req (ring-ajax-get-or-ws-handshake req))
  (POST "/chsk" req (ring-ajax-post                req)))

