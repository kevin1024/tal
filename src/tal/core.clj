(ns tal.core)
(load "rss")

(defn main []
  (spit "output.rss" (tal.rss/main))
)

