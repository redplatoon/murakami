(defproject murakami "0.1.0-SNAPSHOT"
  :description "Server for 'Sprattle' (working title)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :plugins [[lein-ring    "0.9.7"]
            [lein-environ "1.0.2"]]
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit            "2.1.18"]
                 [ring-server         "0.4.0"]
                 [ring/ring-core      "1.4.0"]
                 [compojure           "1.4.0"]
                 [com.taoensso/sente  "1.8.1"]
                 [environ             "1.0.2"]
                 [com.taoensso/timbre "4.3.1"]
                 [herolabs/apns       "0.5.0"]]
  :main ^:skip-aot murakami.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})