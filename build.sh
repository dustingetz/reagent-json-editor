#!/usr/bin/env bash

npm install
webpack
pushd vendor/reagent-json-editor; ./build.sh; popd
