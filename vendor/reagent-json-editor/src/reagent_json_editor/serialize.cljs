(ns reagent-json-editor.serialize)


(defmulti encode (fn [o] :default))
(defmulti decode (fn [s] :default))
(defmulti valid? (fn [s] :default))

(defmethod valid? :default [s]
  (try
    (do
      (decode s)
      true)
    (catch js/Error e
      false)))
