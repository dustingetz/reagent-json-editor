(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                  [org.clojure/clojurescript "1.7.228"]])

(require '[adzerk.boot-cljs :refer :all])

(task-options!
  pom {:project 'org.prognostic/reagent-json-editor
       :version "0.0.1-SNAPSHOT"})
