(ns reagent-json-editor.JsonLeafEditor
  (:require [reagent.core :as reagent]
            [clojure.string :as string]
            [reagent-json-editor.serialize :as serialize]))

(defn commit! [cur state]
  (swap! cur (constantly (serialize/decode (:js-value @state))))
  (swap! state update-in [:editing] (constantly false)))


(defn JsonLeafEditor [cur]
  (let [state (reagent/atom {:js-value (serialize/encode @cur)
                             :editing false})]
    (fn [cur]
      (let [{:keys [:js-value :editing]} @state
            dirty? (not= (serialize/encode @cur) js-value)
            valid? (serialize/valid? js-value)
            class (->>
                    ["JsonLeafEditor"
                     (if dirty? "dirty" nil)
                     (if valid? nil "invalid")]
                    (remove nil?)
                    (string/join " "))]
        (if editing
          [:span {:class class}
           [:input {:value js-value
                    :on-change #(swap! state update-in [:js-value] (constantly (-> % .-target .-value)))
                    :style {:background "transparent"}}]
           [:button {:on-click #(commit! cur state)
                     :disabled (not valid?)}
            "commit"]]
          [:span {:class class}
           [:code.editButton {:on-click #(swap! state update-in [:editing] (constantly true))} (pr-str @cur)]])))))
