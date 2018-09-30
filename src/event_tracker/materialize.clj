(ns event-tracker.materialize)

(defn image-card
  [title uri description]
  [:div.row
   [:div.col.s12.m3
    [:div.card
     [:div.card-image
      [:img {"src" uri}]
      [:span.card-title title]]
     [:div.card-content
      [:p description]]]]])
