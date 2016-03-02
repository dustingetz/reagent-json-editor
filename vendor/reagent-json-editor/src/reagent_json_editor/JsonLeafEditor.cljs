(ns reagent-json-editor.JsonLeafEditor
  (:require [reagent.core :as reagent]))

(defn commit [cur state]
      (swap! cur (constantly (js/JSON.parse (:jsValue @state))))
      (swap! state update-in [:editing] (constantly false)))

(defn is-valid [value]
  (try
    (do
      (js/JSON.parse value)
      true)
    (catch js/Error e
      false)))

(defn JsonLeafEditor [cur]
      (let [state (reagent/atom {
                                 :jsValue (js/JSON.stringify (clj->js @cur) nil 2)
                                 :editing false
                                 })]
           (fn [cur]
               [:span {:class (str "JsonLeafEditor"
                                   (if (not= (js/JSON.stringify (clj->js @cur)) (:jsValue @state))" dirty" nil)
                                   (if (is-valid (:jsValue @state)) nil " invalid"))}
                (if (:editing @state)
                  [:div
                    [:input {:value     (:jsValue @state)
                             :on-change (fn [e] (swap! state update-in [:jsValue] (constantly (-> e .-target .-value))))
                             :style     {:background "transparent"}}]
                    [:button {:on-click #(commit cur state)
                              :disabled (not (is-valid (:jsValue @state)))}
                    "commit"]]
                  [:code.editButton {:on-click #(swap! state update-in [:editing] (constantly true))} @cur])])))
