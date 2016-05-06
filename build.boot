(set-env!
  :source-paths #{"src"
                  "vendor/reagent-json-editor/src"}
  :resource-paths #{"resources"
                    "generated-resources"
                    "vendor/react-treeview-0.4.3/resources"
                    "vendor/reagent-json-editor/resources"}
  :dependencies '[[adzerk/boot-cljs "1.7.228-1" :scope "test"]
                  [pandeiro/boot-http "0.7.3" :scope "test"]
                  [adzerk/boot-reload "0.4.5" :scope "test"]
                  [org.clojure/clojurescript "1.7.228"]
                  [reagent "0.6.0-alpha" :exclusions [cljsjs/react cljsjs/react-dom cljsjs/react-dom-server]]
                  ;[org.prognostic/reagent-json-editor "0.2.0-SNAPSHOT"]
                  [org.prognostic/cursor "0.1.0-SNAPSHOT"]

                  ;; boot-cljs-repl
                  [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
                  [com.cemerick/piggieback "0.2.1" :scope "test"]
                  [weasel "0.7.0" :scope "test"]
                  [org.clojure/tools.nrepl "0.2.12" :scope "test"]])

(require '[adzerk.boot-cljs :refer :all]
         '[pandeiro.boot-http :refer :all]
         '[adzerk.boot-reload :refer [reload]]
         '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])

(task-options!
  pom {:project 'org.prognostic/reagent-json-editor-example})

(deftask dev []
  (comp (serve :dir "target/")
        (watch)
        (speak)
        (reload :on-jsload 'hello-world.core/idempotent-render)
        (cljs-repl)
        (cljs
          :source-map true
          :optimizations :none)
        (target)))
