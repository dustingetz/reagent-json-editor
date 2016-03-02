(ns reagent-json-editor.core
  (:require [cursor.core :refer [cursor]]
            [reagent-json-editor.JsonLeafEditor :refer [JsonLeafEditor]]))

(defn JsonEditor [cur]
      [:div [:code "a: "] [JsonLeafEditor (cur [:a])]])

