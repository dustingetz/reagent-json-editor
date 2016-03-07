(ns hello-world.App
  (:require [reagent-json-editor.core :refer [JsonEditor]]))

(defn App [cur]
  [:div.App
   [:div "State" [:pre (js/JSON.stringify (clj->js @cur) nil 2)]]
   [:div "Editor" [JsonEditor cur]]
   [:div "Second editor, because we can" [JsonEditor cur]]])
