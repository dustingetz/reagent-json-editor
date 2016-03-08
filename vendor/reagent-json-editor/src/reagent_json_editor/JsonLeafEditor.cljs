(ns reagent-json-editor.JsonLeafEditor
  (:require [reagent.core :as reagent]
            [clojure.string :as string]))

(defn commit! [cur state]
  (swap! cur (constantly (cljs.reader/read-string (:js-value @state))))
  (swap! state update-in [:editing] (constantly false)))

(defn valid-json? [value]
  (try
    (do
      (cljs.reader/read-string value)
      true)
    (catch js/Error e
      false)))

(defn JsonLeafEditor [cur]
  (let [state (reagent/atom {:js-value (pr-str @cur)
                             :editing false})]
    (fn [cur]
      (let [{:keys [:js-value :editing]} @state
            dirty? (not= (pr-str @cur) js-value)
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
           [:code.editButton {:on-click #(swap! state update-in [:editing] (constantly true))} (pr-str @cur)])]))))
