(defproject tal "0.0.1"
  :description "A RSS feed of recent This American Life Episodes"
  :url "https://github.com/kevin1024/tal"
  :main tal.core
  :license {:name "The MIT License"
            :url "http://opensource.org/licenses/MIT"}
  :uberjar-name "tal-standalone.jar"
  :min-lein-version "2.0.0"
  :dependencies [
      [org.clojure/clojure "1.5.1"]
      [enlive "1.1.4"]
      [clj-rss "0.1.3"]
      [http-kit "2.1.10"]
  ])
