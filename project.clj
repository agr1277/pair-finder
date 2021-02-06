(defproject pair-finder "0.1.0-SNAPSHOT"
  :description "A simple utility to play with strings"
  :license {:name "MIT License"
            :url "https://spdx.org/licenses/MIT.html"}
  :dependencies [[org.clojure/clojure "1.10.1"]]
  :main ^:skip-aot pair-finder.core
  :target-path "target/%s"
  :profiles {:uberjar {:aot :all
                       :jvm-opts ["-Dclojure.compiler.direct-linking=true"]}})
