(ns hello-world.App
  (:require-macros [cljs.core])
  (:require [reagent-json-editor.core :refer [JsonEditor]]
            [cljs.pprint :refer [pprint]]))

(defn App [cur]
  [:div.App
   [:div "State" [:pre (with-out-str (pprint @cur))]]
   [:div "Editor" [JsonEditor cur]]
   [:div "Second editor, because we can" [JsonEditor cur]]])
