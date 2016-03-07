(ns murakami.db.core
  (:require [postgres.async :refer :all]
            [environ.core :refer [env]]))

(def db (open-db {:hostname  (env :hostname)
                  :port      (read-string (env :port))
                  :database  (env :database)
                  :username  (env :username)
                  :password  (env :password)
                  :pool-size (read-string (env :pool-size))}))

