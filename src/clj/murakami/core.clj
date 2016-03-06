(ns murakami.core
  (:require [murakami.handler            :refer :all]
            [murakami.services.websocket :as socket]
            [taoensso.sente              :as sente]
            [ring.logger.timbre          :as logger]
            [taoensso.timbre             :as timbre])
  (:use     [org.httpkit.server]
            [compojure.handler :only [site]])
  (:gen-class))

(defonce server (atom nil))

(defn- start-server! []
  (reset! server (run-server (logger/wrap-with-logger app) {:port 8080})))

(defn- stop-server! []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

;;;; Sente event router (our `event-msg-handler` loop)
(defonce router_ (atom nil))

(defn-  stop-router! [] (when-let [stop-f @router_] (stop-f)))

(defn- start-router! []
  (stop-router!)
  (reset! router_
    (sente/start-server-chsk-router!
      socket/ch-chsk socket/event-msg-handler)))

(defn -main
  "Run server"
  [& args]
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (timbre/info "Starting server on port 8080")
  (start-server!)
  (timbre/info "Starting websocket router")
  (start-router!))
