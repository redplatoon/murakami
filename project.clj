(defproject murakami "0.1.0-SNAPSHOT"
  :description "Server for 'Sprattle' (working title)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :source-paths ["src/clj"]
  :plugins [[lein-ring      "0.9.7"]
            [lein-environ   "1.0.2"]
            [lein-figwheel  "0.5.0-6"]
            [lein-cljsbuild "1.1.2"]]
  :dependencies [[org.clojure/clojure       "1.8.0"]
                 [org.clojure/clojurescript "1.7.228"]
                 [http-kit                  "2.1.18"]
                 [ring-server               "0.4.0"]
                 [ring/ring-core            "1.4.0"]
                 [alaisi/postgres.async     "0.6.0"]
                 [lib-noir                  "0.9.9"]
                 [compojure                 "1.4.0"]
                 [com.taoensso/sente        "1.8.1"]
                 [environ                   "1.0.2"]
                 [com.taoensso/timbre       "4.3.1"]
                 [ring-logger-timbre        "0.7.5"]
                 [herolabs/apns             "0.5.0"]
                 [secretary                 "1.2.3"]
                 [venantius/accountant      "0.1.7"]
                 [reagent-utils             "0.1.7"]
                 [hiccup                    "1.0.5"]
                 [reagent                   "0.6.0-alpha"]
                 [org.clojure/core.async    "0.2.374"]
                 [com.taoensso/encore       "2.41.0"]]
  :main ^:skip-aot murakami.core
  :target-path "target/%s"
  :cljsbuild
    {:builds
      [{:id "pynchon"
        :source-paths ["src/cljs"]
        :figwheel true
        :compiler {
          :main "pynchon.core"
          :asset-path "js/out"
          :output-to "resources/public/js/app.js"
          :output-dir "resources/public/js/out"
          :optimizations :none
          :externs ["resources/public/js/helpers.js"]
          :source-map true}}]})
