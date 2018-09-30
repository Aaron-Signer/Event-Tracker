(ns event-tracker.event-rendering
  (:require [event-tracker.common :refer :all]
            [event-tracker.materialize :as mat]
            [ring.util.response :as response]
            [hiccup.form :as form]))


(defn add-event-handler
  "Handle the actual addition of an event.  You get here from
  add-event-form-handler."
  [request database-atom]
  (let
      [params (get request :params)]
    (do
      (swap!
       database-atom
       conj
       (-> {}
           (assoc :title (get params "title"))
           (assoc :description (get params "description"))
           (assoc :image-uri (get params "image-uri"))))
      (response/redirect "/events"))))


(defn add-event-form-handler
  "Display a form to add an event to the database."
  [request]
  (render-standard-page
   "Add new event"
   (form/form-to
    [:post "/add-event"]
    (form/label "title" "Title")
    (form/text-field "title")
    (form/label "image-uri" "Image URI")
    (form/text-field "image-uri")
    (form/label "description" "Description")
    (form/text-area "description")
    (form/submit-button "Add"))))

; for returns a list of vectors, which are cards
(defn render-event-list-partial
  [el]
  (for [event el]
    (mat/image-card (:title event) (:image-uri event) (:description event))))

;; event-list -> html
(defn render-event-list
  [el]
  (render-standard-page
   "Event List"
   [:div
    [:p
     [:a {"href" "/add-event-form"} "Add event"]]
    (render-event-list-partial el)]))
