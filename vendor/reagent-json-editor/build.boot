(set-env!
  :source-paths #{"src"}
  :dependencies '[
    [adzerk/boot-cljs "1.7.170-3" :scope "test"]
    [org.clojure/clojurescript "1.7.170"]
    [org.prognostic/cursor "0.0.1-SNAPSHOT"]])

(require '[adzerk.boot-cljs :refer :all])


(task-options!
  pom  {:project     'org.prognostic/reagent-json-editor
        :version     "0.0.1-SNAPSHOT"})
