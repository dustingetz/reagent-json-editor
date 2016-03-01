(ns hello-world.core
  (:require [reagent.core :as reagent]
            [reagent-json-editor.core :as foo]))

(defn idempotent-render []
  (reagent/render-component [:div "hello"] (.getElementById js/document "root")))
