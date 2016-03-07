(ns reagent-json-editor.JsonLeafEditor
  (:require [reagent.core :as reagent]
            [clojure.string :as string]))

(defn commit! [cur state]
  (swap! cur (constantly (js/JSON.parse (:js-value @state))))
  (swap! state update-in [:editing] (constantly false)))

(defn valid-json? [value]
  (try
    (do
      (js/JSON.parse value)
      true)
    (catch js/Error e
      false)))

(defn JsonLeafEditor [cur]
  (let [state (reagent/atom {:js-value (js/JSON.stringify (clj->js @cur) nil 2)
                             :editing false})]
    (fn [cur]
      (let [{:keys [:js-value :editing]} @state
            dirty? (not= (js/JSON.stringify (clj->js @cur)) js-value)
            valid? (valid-json? js-value)
            classes ["JsonLeafEditor"
                     (if dirty? "dirty" nil)
                     (if valid? nil "invalid")]]
        [:span {:class (string/join " " (remove nil? classes))}
         (if editing
           [:div
            [:input {:value js-value
                     :on-change #(swap! state update-in [:js-value] (constantly (-> % .-target .-value)))
                     :style {:background "transparent"}}]
            [:button {:on-click #(commit! cur state)
                      :disabled (not valid?)}
             "commit"]]
           [:code.editButton {:on-click #(swap! state update-in [:editing] (constantly true))} (js/JSON.stringify (clj->js @cur) nil 2)])]))))
