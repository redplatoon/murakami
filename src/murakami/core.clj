(ns murakami.core
  (:require [murakami.handler   :refer :all]
            [ring.logger.timbre :as logger])
  (:use [org.httpkit.server]
        [compojure.handler :only [site]])
  (:gen-class))

(defonce server (atom nil))

(defn stop-server []
  (when-not (nil? @server)
    ;; graceful shutdown: wait 100ms for existing requests to be finished
    ;; :timeout is optional, when no timeout, stop immediately
    (@server :timeout 100)
    (reset! server nil)))

(defn -main
  "Run server"
  [& args]
  ;; The #' is useful when you want to hot-reload code
  ;; You may want to take a look: https://github.com/clojure/tools.namespace
  ;; and http://http-kit.org/migration.html#reload
  (reset! server (run-server (logger/wrap-with-logger app) {:port 8080})))
