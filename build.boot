(set-env!
  :source-paths #{"src" "vendor/reagent-json-editor/src"}
  :resource-paths #{"html"}
  :dependencies '[
    [adzerk/boot-cljs "1.7.228-1" :scope "test"]
    [pandeiro/boot-http "0.7.3" :scope "test"]
    [adzerk/boot-reload "0.4.5" :scope "test"]
    [org.clojure/clojurescript "1.7.228"]
    [reagent "0.6.0-alpha"]
    [org.prognostic/reagent-json-editor "0.0.1-SNAPSHOT"]
    [org.prognostic/cursor "0.0.1-SNAPSHOT"]

    ;; boot-cljs-repl
    [adzerk/boot-cljs-repl "0.3.0" :scope "test"]
    [com.cemerick/piggieback "0.2.1"  :scope "test"]
    [weasel                  "0.7.0"  :scope "test"]
    [org.clojure/tools.nrepl "0.2.12" :scope "test"]])

(require '[adzerk.boot-cljs :refer :all])
(require '[pandeiro.boot-http :refer :all])
(require '[adzerk.boot-reload :refer [reload]])
(require '[adzerk.boot-cljs-repl :refer [cljs-repl start-repl]])


(task-options! reload {:on-jsload 'hello-world.core/idempotent-render})
