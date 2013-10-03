(ns tal.core)
(load "rss")
(require [org.httpkit.server :as httpkit])

(defn app [req]
  {:status  200
   :headers {"Content-Type" "application/rss+xml"}
   :body    (tal.rss/main)})

(defn -main [port]
  (httpkit/run-server app {:port (Integer. port)}))
