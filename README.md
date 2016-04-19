## To use
Must include react-treeview on the page


## To Build

pushd vendor/reagent-json-editor
boot pom jar install
popd

boot serve -d target watch speak reload cljs-repl cljs -s
