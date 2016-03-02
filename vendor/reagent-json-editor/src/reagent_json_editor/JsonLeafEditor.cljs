(ns reagent-json-editor.JsonLeafEditor
  (:require [reagent.core :as reagent]))

(defn onChange [state e]
      (swap! state update-in [:jsValue] (constantly (-> e .-target .-value)))
      (js/console.log (str "changing to" (-> e .-target .-value))))

(defn edit! [state]
      (swap! state update-in [:editing] (constantly true))
      (js/console.log "editing true"))

(defn commit [cur state]
      (swap! cur (constantly (js/JSON.parse (:jsValue @state))))
      (swap! state update-in [:editing] (constantly false)))

(defn isValid [value]
  (try
    (do
      (js/JSON.parse value)
      true)
    (catch js/Error e
      false)))

(defn isDirty [cur state]
      (not= (js/JSON.stringify (clj->js @cur)) (:jsValue @state)))

(defn JsonLeafEditor [cur]
      (let [state (reagent/atom {
                                 :jsValue (js/JSON.stringify (clj->js @cur) nil 2)
                                 :editing false
                                 })]
           (fn [cur]
               [:span {:class (str "JsonLeafEditor" (if (isDirty cur state) " dirty" nil) (if (isValid (:jsValue @state)) nil " invalid"))}
                (if (:editing @state)
                  [:div
                    [:input {:key      "foo"
                             :value    (:jsValue @state)
                             :onChange #(onChange state %)
                             :style    {:background "transparent"}}]
                   [:button {:key      "bar"
                             :onClick  #(commit cur state)
                             :disabled (not (isValid (:jsValue @state)))}
                    "commit"]]
                  [:code.editButton {:onClick #(edit! state)} @cur])])))
