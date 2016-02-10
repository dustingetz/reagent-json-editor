(set-env!
  :source-paths #{"src"}
  :dependencies '[
    [adzerk/boot-cljs "1.7.170-3" :scope "test"]
    [org.clojure/clojurescript "1.7.170"]
    [com.andrewmcveigh/cljs-time "0.3.14"]])

(require '[adzerk.boot-cljs :refer :all])


(task-options!
  pom  {:project     'org.dgetz/fib
        :version     "0.0.1-SNAPSHOT"})
