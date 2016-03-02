(ns reagent-json-editor.core
  (:require [cursor.core :refer [cursor]]))


(defn JsonEditor [cur]
      [:pre (js/JSON.stringify (clj->js @cur) nil 2)])
