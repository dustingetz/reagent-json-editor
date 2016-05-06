(ns hello-world.App
  (:require-macros [cljs.core])
  (:require [reagent-json-editor.core :refer [JsonEditor]]
            [reagent-json-editor.serialize :as serialize]
            [cljs.reader :as reader]
            [cljs.pprint :refer [pprint]]))

(defn App [cur]
  [:div.App
   [:div "Editor" [JsonEditor cur]]
   [:hr]
   [:div "State" [:pre (with-out-str (pprint @cur))]]])


(defmethod serialize/encode :default [o] (pr-str o))
(defmethod serialize/decode :default [s] (reader/read-string s))
