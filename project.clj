; (defproject event-tracker "0.1.0-SNAPSHOT"
;   :description "FIXME: write description"
;   :url "http://example.com/FIXME"
;   :license {:name "Eclipse Public License"
;             :url "http://www.eclipse.org/legal/epl-v10.html"}
;   :dependencies [[org.clojure/clojure "1.8.0"]]
;   :main ^:skip-aot event-tracker.core
;   :target-path "target/%s"
;   :profiles {:uberjar {:aot :all}})

(defproject event-tracker "0.1.0-SNAPSHOT"
  :description "FIXME: write description"
  :url "http://example.com/FIXME"
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :dependencies [[org.clojure/clojure "1.8.0"]
                 [ring "1.7.0"]
                 [compojure "1.6.1"]
                 [hiccup "1.0.5"]]
  :plugins [[lein-ring "0.9.7"]]
  :ring {:handler event-tracker.core/main-handler}
  :main ^:skip-aot event-tracker.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all}})