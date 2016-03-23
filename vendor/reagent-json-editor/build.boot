(set-env!
  :source-paths #{"src"}
  :resource-paths #{"resources"}
  :dependencies '[[adzerk/bootlaces "0.1.13" :scope "test"]
                  [adzerk/boot-cljs "1.7.170-3" :scope "test"]
                  [org.clojure/clojurescript "1.7.170"]
                  [reagent "0.6.0-alpha"]])

(require '[adzerk.boot-cljs :refer :all]
         '[adzerk.bootlaces :refer :all])

(def +version+ "0.0.1-SNAPSHOT")
(bootlaces! +version+)

(task-options!
  pom {:project 'org.prognostic/reagent-json-editor
       :version +version+})
