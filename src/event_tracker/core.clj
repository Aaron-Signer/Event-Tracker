(ns event-tracker.core
  (:require [ring.adapter.jetty :as jetty]
            [compojure.core :refer [GET ANY POST defroutes]]
            [ring.middleware.cookies :as cookies]
            [hiccup.page :as page]
            [compojure.route :as route]
            [ring.util.response :as resp]
            [ring.middleware.params :as params]
            [event-tracker.event-rendering :as render]
            [event-tracker.common :refer :all])
  (:gen-class))

(defn echo-handler
  [request]
  {:status 200,
   :headers {"Content-Type" "text/plain; charset=us-ascii"}
   :cookies {"session_id" {:value "monkey-banana-12"}}
   :body (str
          (get-in request [:params "name"])
          (str request))})

(def event-list (atom [
                       {:title "kite flying",
                        :description "flying kites",
                        :image-uri "https://dkf0vydrjg1g3.cloudfront.net/wp-content/uploads/bfi_thumb/kite-flying-at-marina-barrage-6iwzm14wjnhgx84dei334165h3xgom7gbt3c5sokbdt.jpg",
                        :location "NC"},
                       {:title "panic at the disco concert",
                        :description "panic rocks the house in Pittsburgh!",
                        :image-uri "http://junkee.com/wp-content/uploads/2018/08/emo-680x454.jpg",
                        :location "PA"}]))

(defn list-handler
  [request]
  {:status 200,
   :headers {"Content-Type" "text/html; charset=us-ascii"}
   :body (render/render-event-list @event-list)})

(defn add-event-handler
  [request]
  (render/add-event-handler request event-list))


(defroutes event-handler
  (GET "/events" [] list-handler)
  (ANY "/echo" [] echo-handler)
  (GET "/add-event-form" [] render/add-event-form-handler)
  (POST "/add-event" [] add-event-handler)
  (ANY "/" [] (resp/redirect "/events")) ;redirects to /events in response to a 404, could call list-handler instead.
  (route/not-found "<h1>Sorry, page not found</h1>"))


(def main-handler
  (-> event-handler
      params/wrap-params))

(defn -main
  "I don't do a whole lot ... yet."
  [& args]
  (jetty/run-jetty main-handler {:port 3000}))
