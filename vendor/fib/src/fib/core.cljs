(ns fib.core
  (:require [cljs-time.core :as cljs-time]))

(defn greet []
  (str
    "Hello from subproject, also use a dependency here "
    (cljs-time/hour (cljs-time/date-time 1986 10 14 22))))
