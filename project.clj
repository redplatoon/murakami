(defproject murakami "0.1.0-SNAPSHOT"
  :description "Server for 'Sprattle' (working title)"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [http-kit            "2.1.18"]]
  :main ^:skip-aot murakami.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})
