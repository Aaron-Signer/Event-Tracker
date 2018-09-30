(ns event-tracker.common
  (:require [hiccup.page :as page]))

;; hiccup-partial -> string
(defn render-standard-page
  [title body]
  (page/html5
   [:head
    (page/include-css "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/css/materialize.min.css")
    [:meta {"charset" "utf-8"}]
    [:title title]]
   [:body
    [:h1 title]
    body
    (page/include-js "https://cdnjs.cloudflare.com/ajax/libs/materialize/1.0.0/js/materialize.min.js")]))
