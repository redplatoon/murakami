(ns murakami.routes.home
  (:require [ring.util.response  :as resp]
            [hiccup.page         :refer [include-js include-css html5]]
            [environ.core        :refer [env]]
            [murakami.utils.core :refer :all])
  (:use     [compojure.core]))

(def mount-target
  [:div#app
      [:h3 "ClojureScript has not been compiled!"]
      [:p "please run "
       [:b "lein figwheel"]
       " in order to start the compiler"]])

(def loading-page
  (html5
   [:head
     [:meta {:charset "utf-8"}]
     [:meta {:name "viewport"
             :content "width=device-width, initial-scale=1"}]
     ;;(include-css (if (env :dev) "/css/site.css" "/css/site.min.css"))
     (include-css "/css/site.css")]
    [:body
     mount-target
     (include-js "/js/out/goog/base.js" "/js/app.js")
     [:div#count]
     [:div#total]]))

(defroutes home-routes
  (GET "/" []
    (OK {:message "It's dangerous to go alone. Take this! c=[====>"}))

  (GET "/poo" []
    (OK {:message-break "LIMIT BREAK"}))

  (GET "/app" [] loading-page)
  (GET "/app/about" [] loading-page))
