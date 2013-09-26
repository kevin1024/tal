(defproject tal "0.0.1"
  :description "Access more TAL podcast episodes"
  :url "https://github.com/kevin1024/tal"
  :main tal.core
  :license {:name "Eclipse Public License"
            :url "http://www.eclipse.org/legal/epl-v10.html"}
  :uberjar-name "tal-standalone.jar"
  :dependencies [
      [org.clojure/clojure "1.5.1"]
      [enlive "1.1.4"]
      [clj-rss "0.1.3"]
      [http-kit "2.1.10"]
  ])
