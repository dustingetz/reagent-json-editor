#!/usr/bin/env bash

rm -rf generated-resources
rm -rf node_modules
rm -rf target

pushd vendor/reagent-json-editor; ./clean.sh; popd
