(ns reagent-json-editor.core
  (:require [reagent.core :as reagent]
            [reagent-json-editor.JsonLeafEditor :refer [JsonLeafEditor]]))

(declare JsonEditor render-node)

(def tree-view (reagent/adapt-react-class js/TreeView))

(defn render-node [k cur]
  (let [val @cur]
    (if (or
          (map? val)
          (seq? val)
          (vector? val)
          (array? val))
      [tree-view
       {:nodeLabel (reagent/as-element [:code (str k)]) :key (str k)}
       [JsonEditor cur]]
      [:div {:key (str k)}
       [:code (str k " ")]
       ^{:key @cur} [JsonLeafEditor cur]])))

(defn JsonEditor [cur]
  (let
    [val @cur
     el (cond (map? val) (map (fn [[k _]] (render-node k (cur [k]))) val)
              (or
                (seq? val)
                (vector? val)
                (array? val)) (map-indexed (fn [k _] (render-node k (cur [k]))) val)
              :else (js/console.log "todo"))]

    [:div {:class "wrapper"} el]))
