(ns hello-world.core
  (:require [reagent.core :as reagent]
            [reagent-json-editor.core :as foo]
            [cursor.core :refer [cursor]]))

(def store
  (reagent/atom
    {:a 10
     :b "20"
     :c nil
     :d {:foo {:bar  42
               :baz  55
               :buzz "womp"}}
     :e [{:name "Alice" :id 0}
         {:name "Bob" :id 1}
         {:name "Charlie" :id 2}
         {:name "David" :id 3}]}))


(def rootCursor (cursor store))

(defn idempotent-render []
  (reagent/render-component
    [:div.App "hello"
     "State"
     [:pre (js/JSON.stringify (clj->js @store) nil 2)]]
    (.getElementById js/document "root")))
