(ns hello-world.core
  (:require [reagent.core :as reagent]
            [reagent-json-editor.core :as foo]
            [cursor.core :refer [cursor]]))

(defonce store
  (reagent/atom {}))


(defn render [k store old-val new-val]
      (let [cur (cursor store)]
           (reagent/render-component
             [:div.App
              [:div
               "State"
               [:pre (js/JSON.stringify (clj->js @cur) nil 2)]]
              [:div
               "Editor"]
              [:div "Second editor, because we can"]

              ]
             (.getElementById js/document "root"))))

(def initial-state {:a 10
                    :b "20"
                    :c nil
                    :d {:foo {:bar  42
                              :baz  55
                              :buzz "womp"}}
                    :e [{:name "Alice" :id 0}
                        {:name "Bob" :id 1}
                        {:name "Charlie" :id 2}
                        {:name "David" :id 3}]})

(defn main []
      (add-watch store :react-render render)
      (reset! store initial-state)
      )


(defn idempotent-render []
      (render :react-render store @store @store))