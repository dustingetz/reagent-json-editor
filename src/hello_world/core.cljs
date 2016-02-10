(ns hello-world.core
  (:require [reagent.core :as reagent]
            [fib.core :as fib]))

(defn idempotent-render []
  (reagent/render-component [:div (fib/greet)] (.getElementById js/document "root")))
